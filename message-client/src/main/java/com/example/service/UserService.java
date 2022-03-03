package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.list.url}")
    String userListURL;

    @Value("${user.identity.url}")
    String userIdentityUrl;

    public List<String> fetchUserList() {
        ResponseEntity<List> response = restTemplate.getForEntity(userListURL, List.class);
        return response.getBody();
    }

    public String fetchUserIdentity() {
        ResponseEntity<String> response = restTemplate.getForEntity(userIdentityUrl, String.class);
        return response.getBody();
    }
}
