package com.example.controller;

import com.example.model.CustomMessage;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message) throws UnsupportedEncodingException {
        return messageService.publishMessage(message);
    }
}
