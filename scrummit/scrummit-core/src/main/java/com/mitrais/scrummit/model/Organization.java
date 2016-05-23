package com.mitrais.scrummit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


public class Organization extends Common implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId organizationId;

    @Field(value = "organizationName")
    private String   organizationName;
    
    @DBRef(db = "smcentral")
    @Field(value = "owner")
    private User     owner;

    @Field(value = "contacts")
    private Contact  contacts;

    @Field(value = "dbSettings")
    private DBSeting dbSettings;

    public ObjectId getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(ObjectId organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Contact getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    public DBSeting getDbSettings() {
        return dbSettings;
    }

    public void setDbSettings(DBSeting dbSettings) {
        this.dbSettings = dbSettings;
    }

    
}

class Contact {
    @Field(value = "address")
    private String address;

    @Field(value = "phone")
    private String phone;

    @Field(value = "email")
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

class DBSeting {
    @Field(value = "dbName")
    private String dbName;

    @Field(value = "dbUserId")
    private String dbUserId;

    @Field(value = "dbPassword")
    private String dbPassword;

    @Field(value = "dbPath")
    private String dbPath;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUserId() {
        return dbUserId;
    }

    public void setDbUserId(String dbUserId) {
        this.dbUserId = dbUserId;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

}