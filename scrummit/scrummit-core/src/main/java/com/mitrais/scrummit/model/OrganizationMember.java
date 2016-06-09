package com.mitrais.scrummit.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@SuppressWarnings("serial")
@Document(collection = "organization_members")
public class OrganizationMember extends Common implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ToStringSerializer.class)
    @Field(value = "user_id")
    private ObjectId userId;

    @Field(value = "full_name")
    private String fullName;

    @Field(value="access_rights")
    private int accessRights;

    @Field(value = "is_active")
    private boolean isActive;

    public OrganizationMember() {super();}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId objectId) {
        this.userId = objectId;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public int getAccessRights() {return accessRights;}

    public void setAccessRights(int accessRights){this.accessRights = accessRights;}

    public boolean getIsActive() {return  isActive;}

    public void setIsActive(boolean isActive) {this.isActive = isActive;}
}
