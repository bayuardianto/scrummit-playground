package com.mitrais.scrummit.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Organization;

@Repository
public interface OrganizationDAO extends CommonDAO<Organization, String> {
	@Query("{ 'organization_name' : ?0 }")
    public Organization getOrganizationByName(String name);

}
