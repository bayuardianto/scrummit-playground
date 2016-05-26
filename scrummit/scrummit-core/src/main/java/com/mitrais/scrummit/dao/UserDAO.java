package com.mitrais.scrummit.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mitrais.scrummit.model.User;


public interface UserDAO extends MongoRepository<User, String> {
    public User findByUsername(String username);
}
