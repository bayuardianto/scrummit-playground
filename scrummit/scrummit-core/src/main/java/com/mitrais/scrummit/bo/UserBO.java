package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.User;

public interface UserBO {
    public User findByUsername(String name);
}
