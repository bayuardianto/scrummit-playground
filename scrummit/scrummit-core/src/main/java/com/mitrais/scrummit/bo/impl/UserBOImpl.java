package com.mitrais.scrummit.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.dao.UserDAO;
import com.mitrais.scrummit.model.User;

@Service
public class UserBOImpl implements UserBO {
    @Autowired
    UserDAO userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
