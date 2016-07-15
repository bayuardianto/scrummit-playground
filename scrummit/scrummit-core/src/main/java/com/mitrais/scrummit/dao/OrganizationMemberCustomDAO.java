package com.mitrais.scrummit.dao;

import com.mitrais.scrummit.model.OrganizationMember;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class OrganizationMemberCustomDAO extends CommonCustomDAO<OrganizationMember, String> {
    public List<OrganizationMember> getOrgMembers() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "fullname"));

        return mongoTemplate.find(query, OrganizationMember.class);
    }
}
