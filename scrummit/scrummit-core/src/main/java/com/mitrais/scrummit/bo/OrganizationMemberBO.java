package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.OrganizationMember;

import java.util.List;

import com.mitrais.scrummit.model.OrganizationMember;

/**
 * Created by Andreanus_P on 5/27/2016.
 */
public interface OrganizationMemberBO extends BaseBO<OrganizationMember> {
    OrganizationMember getByUserId(String userId);
    String testString();
    List<OrganizationMember> getAll();
}
