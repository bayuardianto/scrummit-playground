package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.User;

public interface UserBO {
    public User findByUsername(String name);
    public User updateUserInfo(User user);

    public User findByActivationKey(String activationKey);

    public User activateUser(String string);
}
