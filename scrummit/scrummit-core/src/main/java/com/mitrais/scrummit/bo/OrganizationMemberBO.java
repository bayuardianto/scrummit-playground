package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.OrganizationMember;

import java.util.List;

/**
 * Created by Andreanus_P on 5/27/2016.
 */
public interface OrganizationMemberBO {
    OrganizationMember getByUserId(String userId);
    String testString();
    List<OrganizationMember> getAll();
}
