package com.wikimedia.unit;

import com.launchdarkly.eventsource.MessageEvent;
import com.wikimedia.handler.WikimediaHandler;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.concurrent.ListenableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WikimediaHandlerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplateMock;

    @InjectMocks
    private WikimediaHandler wikimediaHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(wikimediaHandler, "kafkaTopic", "lorem");
    }

    @Test
    void testOnMessage() throws Exception {
        // Arrange
        MessageEvent messageEvent = new MessageEvent("eventType");
        // Mock the behavior of KafkaTemplate
        ListenableFuture<SendResult<String, String>> futureMock = Mockito.mock(ListenableFuture.class);
        when(kafkaTemplateMock.send(any(ProducerRecord.class))).thenReturn(futureMock);
        // Mock the behavior of the ListenableFuture
        SendResult<String, String> sendResultMock = Mockito.mock(SendResult.class);
        when(sendResultMock.getRecordMetadata()).thenReturn(null);
        when(futureMock.get()).thenReturn(sendResultMock);
        // Act
        wikimediaHandler.onMessage("yourEventType", messageEvent);
        // Assert
        verify(kafkaTemplateMock).send(any(ProducerRecord.class));
    }
}
