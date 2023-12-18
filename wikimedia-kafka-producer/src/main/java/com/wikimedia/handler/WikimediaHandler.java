package com.wikimedia.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class WikimediaHandler implements EventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public WikimediaHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        // No action needed on open
    }

    @Override
    public void onClosed() throws Exception {
        // No action needed on closed
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        log.info("New event data -> {}", messageEvent.getData());
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String comment) throws Exception {
        // No action needed on comment
    }

    @Override
    public void onError(Throwable throwable) {
        // Log or handle the error as needed
        log.error("Error in WikimediaHandler", throwable);
    }
}
