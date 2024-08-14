package com.ejemplo.controller;

import com.ejemplo.service.KafkaProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MessageController {

    private final KafkaProducer kafkaProducer;
    private final AtomicInteger counter = new AtomicInteger();

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // Renderiza la página index.html cuando se accede a la URL raíz "/"
    @GetMapping("/")
    public String index(Model model) {
        return "index"; // "index" corresponde al archivo index.html en la carpeta templates
    }

    // Método para manejar las solicitudes POST desde el formulario
    @PostMapping("/sendMessage")
    @ResponseBody
    public Response sendMessage(@RequestBody MessageForm form) {
        kafkaProducer.sendMessage("myTopic", form.getMessage());
        int id = counter.incrementAndGet();
        return new Response(id, true);
    }

    // Tarea programada para enviar un mensaje cada 10 segundos
    @Scheduled(fixedRate = 10000) // 10000 milisegundos = 10 segundos
    public void sendScheduledMessage() {
        String message = "Scheduled message " + counter.incrementAndGet();
        kafkaProducer.sendMessage("myTopic", message);
        System.out.println("Sent: " + message);
    }

    // Clases internas para manejar el formulario y la respuesta
    static class MessageForm {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    static class Response {
        private int id;
        private boolean success;

        public Response(int id, boolean success) {
            this.id = id;
            this.success = success;
        }

        public int getId() {
            return id;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}