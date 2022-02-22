package com.example.password_manager.service;

import com.example.password_manager.model.User;
import com.example.password_manager.model.WebCredentials;
import com.example.password_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
//                log.info("Web Credentials list: {}", webCredentialsList);
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
            userRepository.save(foundUser);
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
            log.info("Found: {}", webCredential);
            foundUser.getWebCredentials().remove(webCredential);
            userRepository.save(foundUser);
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

    @SneakyThrows
    @Override
    public String registerUser(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        userRepository.insert(user);
        return user.getUsername() + " registered successfully.";
    }


    @SneakyThrows
    @Override
    public Boolean loginAuthentication(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        User foundUser = optionalUser.orElse(null);

        if (foundUser != null){
            String decrypt = decryptPassword(foundUser.getPassword());
            log.info("password - {}, got password - {} - {}", user.getPassword(), foundUser.getPassword());
            return user.getPassword().equals(decrypt);
        }else{
            return false;
        }
    }




    public String encryptPassword(String password){

        return password;
    }

    public String decryptPassword(String password){
        return password;
    }


}
