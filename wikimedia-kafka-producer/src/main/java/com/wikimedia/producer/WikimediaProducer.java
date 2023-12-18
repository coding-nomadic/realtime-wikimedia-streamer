package com.wikimedia.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.wikimedia.handler.WikimediaHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${wikimedia.url}")
    private String wikimediaUrl;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikimediaHandler(kafkaTemplate, topicName);
        EventSource eventSource = buildEventSource(eventHandler);
        eventSource.start();
        waitTenMinutes();
    }

    private EventSource buildEventSource(EventHandler eventHandler) {
        return new EventSource.Builder(eventHandler, URI.create(wikimediaUrl)).build();
    }

    private void waitTenMinutes() throws InterruptedException {
        TimeUnit.MINUTES.sleep(10);
    }
}
