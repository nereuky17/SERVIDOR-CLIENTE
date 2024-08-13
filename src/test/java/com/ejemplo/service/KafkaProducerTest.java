package com.ejemplo.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducerTest {

    @Test
    public void testSendMessage() {
        KafkaTemplate<String, String> kafkaTemplate = Mockito.mock(KafkaTemplate.class);
        KafkaProducer producer = new KafkaProducer(kafkaTemplate);

        producer.sendMessage("myTopic", "Test Message");

        Mockito.verify(kafkaTemplate, Mockito.times(1)).send("myTopic", "Test Message");
    }
}
