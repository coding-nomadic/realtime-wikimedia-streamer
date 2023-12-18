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
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaProducer(String topicName, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikimediaHandler(kafkaTemplate, topicName);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(wikimediaUrl));
        EventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
