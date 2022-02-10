package com.example.password_manager.service;

import com.example.password_manager.model.User;
import com.example.password_manager.model.WebCredentials;

import java.util.List;

public interface UserService {
    public List<WebCredentials> getAllWebCredentials(User user);
    public String addWebCredentials(User user, WebCredentials webCredential);
    public String deleteWebCredentials(User user, WebCredentials webCredential);
    public String updateWebCredentials(User user, WebCredentials webCredential);
    public String registerUser(String username, String password);
    public Boolean loginAuthentication(User user);
}
