package com.example.controller;

import com.example.model.CustomMessage;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/receive")
    public String receiveMessage(@RequestBody CustomMessage message) {
        return messageService.receiveMessage(message);
    }
}
