package com.example.password_manager.controller;

import com.example.password_manager.model.User;
import com.example.password_manager.model.WebCredentials;
import com.example.password_manager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@AllArgsConstructor
//@RequestMapping("/api")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(value = "/{username}/all")
    public List<WebCredentials> getAllWebCredentials(@PathVariable("username") User user){
        return userService.getAllWebCredentials(user);
    }

    @PostMapping(value = "/{username}/add")
    public String addWebCredentials(@PathVariable("username") User user,@RequestBody WebCredentials webCredential){
        return userService.addWebCredentials(user, webCredential);
    }

    @DeleteMapping(value = "/{username}/delete")
    public String deleteWebCredentials(@PathVariable("username") User user,@RequestBody WebCredentials webCredential){
        return userService.deleteWebCredentials(user, webCredential);
    }

    @PutMapping(value = "/{username}/update")
    public String updateWebCredentials(@PathVariable("username") User user,@RequestBody WebCredentials webCredential){
        return userService.updateWebCredentials(user, webCredential);
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping(value = "/login")
    public String userLogin(@RequestBody User user){
        Boolean login = userService.loginAuthentication(user);
        return ((login) ? "Login Successful" : "User not found") ;
    }
}
