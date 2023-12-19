package com.wikimedia.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class WikimediaHandler implements EventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopic;

    public WikimediaHandler(KafkaTemplate<String, String> kafkaTemplate, String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
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
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaTopic, messageEvent.getData());
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.addCallback(handleSendResult());
    }

    @Override
    public void onComment(String comment) throws Exception {
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error in WikimediaHandler", throwable);
    }

    private ListenableFutureCallback<SendResult<String, String>> handleSendResult() {
        return new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Message sent successfully: {}", result.getRecordMetadata());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Error sending message: {}", ex.getMessage());
            }
        };
    }
}
