package com.wikimedia.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {

    public static final String KAFKA_TOPIC_NAME = "${kafka.topic.name}";
    public static final String KAFKA_BROKER_URL = "${kafka.broker.url}";

    @Value(KAFKA_TOPIC_NAME)
    private String topicName;

    @Value(KAFKA_BROKER_URL)
    private String brokerUrl;

    @Override
    public void configure() throws Exception {
        from("direct:start").routeId("wikimedia-route")
                .onException(Exception.class)
                .log("Exception caught: ${exception.message}")
                .handled(true)
                .end()
                .log(String.format("Received message for Kafka Topic -> %s", "${body}"))
                .to(String.format("kafka:%s?brokers=%s", topicName, brokerUrl));
    }
}
