package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.User;

public interface UserBO extends BaseBO<User> {
    public User findByUsername(String name);
    public User updateUserInfo(User user);

    public User findByActivationKey(String activationKey);

    public User activateUser(String string);
}
