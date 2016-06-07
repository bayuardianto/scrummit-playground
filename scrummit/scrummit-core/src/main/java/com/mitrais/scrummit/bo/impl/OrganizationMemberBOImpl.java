package com.mitrais.scrummit.bo.impl;

import java.util.List;

import com.mitrais.scrummit.bo.OrganizationMemberBO;
import com.mitrais.scrummit.dao.OrganizationMemberCustomDAO;
import com.mitrais.scrummit.dao.OrganizationMemberDAO;
import com.mitrais.scrummit.model.OrganizationMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;




@Service
public class OrganizationMemberBOImpl extends BaseBOImpl<OrganizationMember, OrganizationMemberDAO> implements
        OrganizationMemberBO {
    private static final Log log = LogFactory.getLog(ProjectBOImpl.class);

    @Autowired
    OrganizationMemberCustomDAO organizationMemberCustomDAO;

    @Override
    public OrganizationMember getByUserId(String userId) {
        return currentDAO.findByUserId(new ObjectId(userId));
    }

    @Override
    public String testString() {
        return "testetas";
    }

    @Override
    public List<OrganizationMember> getAll() {
        return organizationMemberCustomDAO.getOrgMembers();
    }
}
