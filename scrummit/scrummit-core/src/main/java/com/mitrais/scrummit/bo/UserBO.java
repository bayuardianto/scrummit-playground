package com.mitrais.scrummit.bo;

import java.util.List;

import org.bson.types.ObjectId;

import com.mitrais.scrummit.model.User;

public interface UserBO extends BaseBO<User> {
    public User findByUsername(String name);
    public User findById(String id);
    public User updateUserInfo(User user);

    public User findByActivationKey(String activationKey);

    public User activateUser(String string);
    public User findByEmail(String email);
    public List<User> findByOrgId(ObjectId orgId);
}
