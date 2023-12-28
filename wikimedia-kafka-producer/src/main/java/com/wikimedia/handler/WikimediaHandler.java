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
        log.info("New event data -> {}", messageEvent.getData());
        Thread.sleep(1000);
        producerTemplate.sendBody("direct:start", messageEvent.getData());
        log.info("Sent message to Kafka Topic successfully !");
    }

    @Override
    public void onComment(String comment) throws Exception {
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error in WikimediaHandler", throwable);
        throwable.getStackTrace();
    }

}
