package com.mitrais.scrummit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SuppressWarnings("serial")
@Document(collection = "user")
public class User extends Common implements Serializable {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value = "username")
    private String   username;

    @Field(value = "password")
    private String   password;

    @Field(value = "first_name")
    private String   firstName;

    @Field(value = "last_name")
    private String   lastName;

    @Field(value = "email")
    private String   email;

    @Field(value = "user_type")
    private String   userType;

    @Field(value = "is_actived")
    private Boolean      isActivated;

    @JsonIgnore
    @Field(value = "activation_key")
    private String       activationKey;

    //non persisted field
    @Transient
    private String newPassword;
    
    @Transient
    private String fullname;

    @DBRef
    @Field(value = "assoc_org_id")
    private Organization assocOrgId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated != null ? isActivated : false;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
    }

    public Organization getAssocOrgId() {
        return assocOrgId;
    }

    public void setAssocOrgId(Organization assocOrgId) {
        this.assocOrgId = assocOrgId;
    }

    public String getFullname() {
    	return firstName + " " + lastName;
    }
}
