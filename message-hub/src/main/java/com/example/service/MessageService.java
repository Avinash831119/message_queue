package com.example.service;

import com.example.config.MQConfiguration;
import com.example.config.UserProperties;
import com.example.model.CustomMessage;
import com.example.util.Util;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private UserProperties userProperties;

    public String receiveMessage(CustomMessage message) {

        try {
            template.convertAndSend(MQConfiguration.EXCHANGE,
                    MQConfiguration.ROUTING_KEY, message);
        } catch (Exception ex) {
            System.out.println("Unable to process message, caused by " + ex.getMessage());
            return "Unable to process message, caused by " + ex.getMessage();
        }
        return "Message delivered successfully";
    }
}
