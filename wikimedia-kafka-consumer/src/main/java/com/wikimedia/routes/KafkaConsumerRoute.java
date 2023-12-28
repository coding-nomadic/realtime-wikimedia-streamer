package com.wikimedia.routes;


import com.wikimedia.entities.WikiMediaData;
import com.wikimedia.models.WikiMedia;
import com.wikimedia.utils.JsonUtils;
import com.wikimedia.utils.TransformerUtils;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerRoute extends RouteBuilder {


    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${kafka.broker.url}")
    private String brokerUrl;

    @Value("${mongodb.database}")
    private String database;

    @Value("${mongodb.collection}")
    private String collection;

    @Override
    public void configure() throws Exception {
        from("kafka:" + topicName + "?brokers=" + brokerUrl).routeId("wikimedia-route").log("Received message from Kafka Topic : ${body}").process(exchange -> {
            exchange.getMessage().setBody(exchange.getIn().getBody());
        }).toF("mongodb:%s?database=%s&collection=%s&operation=insert", database, database, collection);
    }
}