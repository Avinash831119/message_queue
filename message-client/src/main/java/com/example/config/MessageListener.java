package com.example.config;

import com.example.model.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfiguration.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println(message);
    }
}