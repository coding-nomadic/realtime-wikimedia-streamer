package com.wikimedia.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${kafka.broker.url}")
    private String brokerUrl;

    @Override
    public void configure() throws Exception {
        from("direct:start").routeId("wikimedia-route")
                .log("Received message for Kafka Topic -> ${body}")
                .to("kafka:" + topicName + "?brokers=" + brokerUrl);
    }
}