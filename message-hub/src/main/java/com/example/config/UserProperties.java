package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("static")
public class UserProperties {

    private final List<String> userList;

    public UserProperties(List<String> userList) {
        this.userList = userList;
    }

    public List<String> getUserList() {
        return userList;
    }
}
