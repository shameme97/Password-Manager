package com.example.password_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class WebCredentials {
    @Id
    private String website;
    private String websiteUsername;
    private String websitePassword;
    private String websiteEmail;

}
