package com.mitrais.scrummit.dao;

import com.mitrais.scrummit.model.OrganizationMember;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationMemberDAO extends CommonDAO<OrganizationMember, String> {
    @Query("{ 'user_id' : ?0 }")
    OrganizationMember findByUserId(ObjectId objectId);
}
