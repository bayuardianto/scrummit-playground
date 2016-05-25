package com.mitrais.scrummit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SuppressWarnings("serial")
public class User extends Common implements Serializable {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value = "username")
    private String   username;

    @Field(value = "password")
    private String   password;

    @Field(value = "firstname")
    private String   firstname;

    @Field(value = "lastname")
    private String   lastname;

    @Field(value = "email")
    private String   email;

    @Field(value = "userType")
    private String   userType;

    @JsonSerialize(using = ToStringSerializer.class)
    @DBRef
    @Field(value = "assocOrgId")
    private Organization assocOrgId;

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
}
