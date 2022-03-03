package com.example.service;

import com.example.config.UserProperties;
import com.example.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserProperties userProperties;

    public List<String> fetchUserList() {
        return userProperties.getUserList();
    }

    public String fetchUserIdentity() {
        String userId = String.valueOf(Util.getUnique64BitNumber());
        userProperties.getUserList().add(userId);
        return userId;
    }
}
