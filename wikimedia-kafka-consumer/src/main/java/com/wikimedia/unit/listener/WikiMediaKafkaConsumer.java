package com.wikimedia.unit.listener;

import com.wikimedia.unit.entities.WikiMediaData;
import com.wikimedia.unit.models.WikiMedia;
import com.wikimedia.unit.service.WikiMediaDataService;
import com.wikimedia.unit.utils.JsonUtils;
import com.wikimedia.unit.utils.TransformerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WikiMediaKafkaConsumer {

    @Autowired
    private WikiMediaDataService wikiMediaDataService;

    /**
     * @param eventMessage
     */
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage) {
        log.info(String.format("Event message received -> %s", eventMessage));
        try {
            final WikiMedia wikiMedia = JsonUtils.convertWithClass(eventMessage, WikiMedia.class);
            final WikiMediaData wikiMediaData = TransformerUtils.transformToWikiMediaData(wikiMedia);
            wikiMediaDataService.insertData(wikiMediaData);
        } catch (Exception e) {
            log.error(String.format("Error occurred with this message -> %s", e.getMessage()));
        }
    }
}
