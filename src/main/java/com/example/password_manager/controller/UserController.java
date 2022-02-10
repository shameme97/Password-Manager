package com.example.password_manager.controller;

import com.example.password_saver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {

    @Autowired
    public UserService userService;
}
