package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<String> fetchUserList(){
        return userService.fetchUserList();
    }

    @GetMapping("/identity")
    public String fetchUserIdentity() {
        return userService.fetchUserIdentity();
    }
}
