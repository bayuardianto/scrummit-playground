package com.mitrais.scrummit.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.User;

@Repository
public interface UserDAO extends CommonDAO<User, String> {
    public User findByUsername(String username);
    
    public User findByEmail(String email);

    public User findByActivationKey(String activationKey);
    
    @Query("{ 'assoc_org_id.$id' : ?0 }")
    public List<User> findByOrgId(ObjectId orgId);
}
