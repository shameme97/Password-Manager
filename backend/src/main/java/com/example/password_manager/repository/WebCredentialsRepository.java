package com.example.password_manager.repository;

import com.example.password_manager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebCredentialsRepository extends MongoRepository<User, String> {
}
