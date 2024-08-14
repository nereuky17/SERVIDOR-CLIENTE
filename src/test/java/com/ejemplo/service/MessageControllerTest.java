package com.ejemplo.service;


import com.ejemplo.controller.MessageController;
import com.ejemplo.controller.Response; // Add this import statement
import com.ejemplo.controller.MessageForm; // Add this import statement
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull; // Add this import statement
import static org.junit.Assert.assertTrue; // Add this import statement
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@SpringBootTest
class MessageControllerTest {

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    void setUp() {
        messageController = new MessageController(kafkaProducer);
    }

    @Test
    void testIndex() {
        Model model = mock(Model.class);
        String viewName = messageController.index(model);
        assertEquals("index", viewName);
    }

    @Test
    void testSendMessage() {
    MessageForm form = new MessageForm();
    form.setMessage("Hello, Kafka!");

    String message = form.getMessage();
    Response response = messageController.sendMessage(message);

    verify(kafkaProducer, times(1)).sendMessage("myTopic", "Hello, Kafka!");
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertEquals(1, response.getId());
}

    @Test
    void testSendScheduledMessage() {
        messageController.sendScheduledMessage();

        verify(kafkaProducer, times(1)).sendMessage(eq("myTopic"), contains("Scheduled message"));
    }
}