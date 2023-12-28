//package com.wikimedia.integration;
//
//import com.launchdarkly.eventsource.MessageEvent;
//import com.wikimedia.handler.WikimediaHandler;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.test.EmbeddedKafkaBroker;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.kafka.test.utils.KafkaTestUtils;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Properties;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@EmbeddedKafka(topics = "testTopic", partitions = 1)
//class WikimediaHandlerIntegrationTest {
//
//    @Autowired
//    private WikimediaHandler wikimediaHandler;
//
//    @Autowired
//    private static EmbeddedKafkaBroker embeddedKafkaBroker;
//
//    private static Consumer<String, String> kafkaConsumer;
//
//    @BeforeAll
//    static void setUp() {
//        // Set up a Kafka consumer to listen to the embedded Kafka broker
//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group-id", "true", embeddedKafkaBroker);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        kafkaConsumer = new KafkaConsumer<>(consumerProps);
//    }
//
//    @AfterAll
//    static void tearDown() {
//        if (kafkaConsumer != null) {
//            kafkaConsumer.close();
//        }
//    }
//
//    @Test
//    void testOnMessage() throws Exception {
//        // Call the onMessage method (simulate an event)
//        wikimediaHandler.onMessage("testEvent", new MessageEvent("eventType"));
//
//        // Poll for the message in the Kafka topic
//        ConsumerRecords<String, String> records = KafkaTestUtils.getRecords(kafkaConsumer);
//
//        // Assert that the message is present in the Kafka topic
//        //assertEquals(1, records.count());
//        //assertEquals("testEventData", records.iterator().next().value());
//    }
//}
