package com.example.password_manager.service;

import com.example.password_manager.model.User;
import com.example.password_manager.model.WebCredentials;
import com.example.password_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    @Override
    public List<WebCredentials> getAllWebCredentials(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        User foundUser = optionalUser.orElse(null);
        if (foundUser != null){
            List<WebCredentials> webCredentialsList = foundUser.getWebCredentials();
            if (webCredentialsList.size() != 0){
//                String credentials = "";
//                for (WebCredentials webCredentials: webCredentialsList){
//                    credentials += webCredentials.getWebsite() +" "+ webCredentials.getWebsiteUsername()
//                            + " " + webCredentials.getWebsitePassword();
//                }
//                return credentials;
                return webCredentialsList;
            }
        }
        return null;
    }

    @Override
    public String addWebCredentials(User user, WebCredentials webCredential) {
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        User foundUser = optionalUser.orElse(null);
        if (foundUser != null) {
            foundUser.getWebCredentials().add(webCredential);
        } else {
            return "User not found!";
        }
        return "Saved successfully";
    }

    @Override
    public String deleteWebCredentials(User user, WebCredentials webCredential) {
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        User foundUser = optionalUser.orElse(null);
        if (foundUser != null){
            foundUser.getWebCredentials().remove(webCredential);
        } else{
            return "User not found!";
        }
        return "Deleted successfully";
    }

    @Override
    public String updateWebCredentials(User user, WebCredentials webCredential) {
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        User foundUser = optionalUser.orElse(null);
        if (foundUser != null){
            if (webCredential.getWebsite() != null){
                int index = foundUser.getWebCredentials().indexOf(webCredential);
                foundUser.getWebCredentials().get(index).setWebsite(webCredential.getWebsite());
            }
            if (webCredential.getWebsiteUsername() != null){
                int index = foundUser.getWebCredentials().indexOf(webCredential);
                foundUser.getWebCredentials().get(index).setWebsiteUsername(webCredential.getWebsiteUsername());
            }
            if (webCredential.getWebsitePassword() != null){
                int index = foundUser.getWebCredentials().indexOf(webCredential);
                foundUser.getWebCredentials().get(index).setWebsitePassword(webCredential.getWebsitePassword());
            }
        } else{
            return "User not found!";
        }
        return "Updated successfully";
    }

    @Override
    public String registerUser(String username, String password) {
        User user = new User(username, password);
        userRepository.insert(user);
        return user.getUsername() + " registered successfully.";
    }

    @Override
    public Boolean loginAuthentication(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        User foundUser = optionalUser.orElse(null);
        if (foundUser != null){
            return user.getPassword().equals(foundUser.getPassword());
        }else{
            return false;
        }
    }
}
