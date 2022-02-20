package com.example.password_manager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

//import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;

@Data
//@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String username;
    private String password;
//    @Email
    private String email;
    private ArrayList<WebCredentials> webCredentials = new ArrayList<WebCredentials>();

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;

    }

}
