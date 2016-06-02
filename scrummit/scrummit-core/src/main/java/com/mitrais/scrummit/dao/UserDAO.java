package com.mitrais.scrummit.dao;

import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.User;

@Repository
public interface UserDAO extends CommonDAO<User, String> {
    public User findByUsername(String username);
}
