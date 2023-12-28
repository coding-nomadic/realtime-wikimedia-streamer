package com.wikimedia.routes;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerRoute extends RouteBuilder {


    @Value("${kafka.topic.name}")
    private final String topicName;

    @Value("${kafka.broker.url}")
    private final String brokerUrl;

    @Value("${mongodb.database}")
    private final String database;

    @Value("${mongodb.collection}")
    private final String collection;

    public KafkaConsumerRoute(
            @Value("${kafka.topic.name}") final String topicName,
            @Value("${kafka.broker.url}") final String brokerUrl,
            @Value("${mongodb.database}") final String database,
            @Value("${mongodb.collection}") final String collection) {
        this.topicName = topicName;
        this.brokerUrl = brokerUrl;
        this.database = database;
        this.collection = collection;
    }

    @Override
    public void configure() throws Exception {
        from(String.format("kafka:%s?brokers=%s", topicName, brokerUrl))
                .routeId("wikimedia-route")
                .log("Received message from Kafka Topic: ${body}")
                .process(exchange -> exchange.getMessage().setBody(exchange.getIn().getBody()))
                .toF("mongodb:%s?database=%s&collection=%s&operation=insert", collection, database, collection);
    }
}
