package com.mitrais.scrummit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


@SuppressWarnings("serial")
@Document(collection = "organization")
public class Organization extends Common implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId organizationId;

    @Field(value = "organization_name")
    private String   organizationName;
    
    @JsonIgnore
    @DBRef(db = "smcentral")
    @Field(value = "owner")
    private User     owner;

    @Field(value = "contacts")
    private Contact  contacts;

    @Field(value = "db_settings")
    private DBSetting dbSettings;

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

    public DBSetting getDbSettings() {
        return dbSettings;
    }

    public void setDbSettings(DBSetting dbSettings) {
        this.dbSettings = dbSettings;
    }

    public void initializeDbSettings(String dbName, String dbUserId, String dbPassword, String dbPath){
        this.dbSettings = new DBSetting();
        this.dbSettings.setDbName(dbName);
        this.dbSettings.setDbUserId(dbUserId);
        this.dbSettings.setDbPassword(dbPassword);
        this.dbSettings.setDbPath(dbPath);
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
