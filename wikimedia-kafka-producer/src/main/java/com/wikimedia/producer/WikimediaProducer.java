package com.wikimedia.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.wikimedia.handler.WikimediaHandler;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaProducer {

    @Value("${wikimedia.url}")
    private String wikimediaUrl;


    private ProducerTemplate producerTemplate;

    public WikimediaProducer(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikimediaHandler(producerTemplate);
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
