package com.example.password_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebCredentials {
    private String website;
    private String websiteUsername;
    private String websitePassword;

}
