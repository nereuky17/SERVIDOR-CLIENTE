package com.ejemplo.service;

import com.ejemplo.controller.MessageController.MessageForm;
import com.ejemplo.controller.MessageController.Response;
import com.ejemplo.service.KafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

        Response response = messageController.sendMessage(form);

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