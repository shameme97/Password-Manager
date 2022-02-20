package com.example.password_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@AllArgsConstructor
public class WebCredentials {
    @Id
    private String website;
    private String websiteUsername;
    private String websitePassword;
    private String websiteEmail;

}
