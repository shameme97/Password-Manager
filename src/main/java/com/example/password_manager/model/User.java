package com.example.password_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private ArrayList<WebCredentials> webCredentials = new ArrayList<WebCredentials>();

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
