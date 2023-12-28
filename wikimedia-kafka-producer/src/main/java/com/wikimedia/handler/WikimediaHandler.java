package com.wikimedia.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
public class WikimediaHandler implements EventHandler {


    private ProducerTemplate producerTemplate;

    public WikimediaHandler(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        log.info("Received new event data: {}", messageEvent.getData());
        // Publishing message to Kafka topic at a break of One second.
        final int sleepDurationMillis = 1000;
        Thread.sleep(sleepDurationMillis);
        producerTemplate.sendBody("direct:start", messageEvent.getData());
        log.info("Message sent to Kafka Topic successfully!");
    }

    @Override
    public void onComment(String comment) throws Exception {
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error in WikimediaHandler: {}", throwable.getMessage());
    }

}
