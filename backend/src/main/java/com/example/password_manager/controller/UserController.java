package com.example.password_manager.controller;

import com.example.password_manager.model.User;
import com.example.password_manager.model.WebCredentials;
import com.example.password_manager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor


public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(value = "/all/{username}/{password}/{email}")
    public List<WebCredentials> getAllWebCredentials(@PathVariable("username") String username,
                                                     @PathVariable("password") String password,
                                                     @PathVariable("email") String email){
        User user = new User(username,password,email);
        return userService.getAllWebCredentials(user);
    }

    @PostMapping(value = "/add/{username}/{password}/{email}")
    public String addWebCredentials(@PathVariable("username") String username,
                                    @PathVariable("password") String password,
                                    @PathVariable("password") String email,
                                    @RequestBody WebCredentials webCredential){
        User user = new User(username,password,email);
        return userService.addWebCredentials(user, webCredential);
    }

    @DeleteMapping(value = "/delete/{username}/{password}/{email}")
    public String deleteWebCredentials(@PathVariable("username") String username,
                                       @PathVariable("password") String password,
                                       @PathVariable("password") String email,
                                       @RequestBody WebCredentials webCredential){
        User user = new User(username,password,email);
        return userService.deleteWebCredentials(user, webCredential);
    }

    @PutMapping(value = "/update/{username}/{password}/{email}")
    public String updateWebCredentials(@PathVariable("username") String username,
                                       @PathVariable("password") String password,
                                       @PathVariable("password") String email,
                                       @RequestBody WebCredentials webCredential){
        User user = new User(username,password,email);
        return userService.updateWebCredentials(user, webCredential);
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public Boolean userLogin(@RequestBody User user){
        Boolean login = userService.loginAuthentication(user);
//        log.info("Login status - {}", login);
//        return ((login) ? "Login Successful" : "User not found") ;
        return login;
    }

    @GetMapping(value = "/search/{username}/{password}/{email}/{string}")
    public List<WebCredentials> searchWebCredentials(@PathVariable("username") String username,
                                                     @PathVariable("password") String password,
                                                     @PathVariable("email") String email,
                                                     @PathVariable("string") String string){
        User user = new User(username,password,email);
        return userService.searchWebCredentials(user, string);
    }
}
