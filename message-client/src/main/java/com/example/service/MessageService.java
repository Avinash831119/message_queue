package com.example.service;

import com.example.model.CustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${message.publish.url}")
    String messagePublishURL;


    public String publishMessage(CustomMessage message) throws UnsupportedEncodingException {
        final byte[] utf8Bytes = message.getMessage().getBytes("UTF-8");
        if (utf8Bytes.length <= 1024) {
            ResponseEntity<String> response = restTemplate.postForEntity(messagePublishURL, message, String.class);
            return response.getBody();
        }
        return "Message length should be less then 1024 kilobytes";
    }
}
