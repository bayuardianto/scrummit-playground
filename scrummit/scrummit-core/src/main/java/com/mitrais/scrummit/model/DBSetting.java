package com.mitrais.scrummit.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class DBSetting {
    @Field(value = "db_name")
    private String dbName;

    @Field(value = "db_user_id")
    private String dbUserId;

    @Field(value = "db_password")
    private String dbPassword;

    @Field(value = "db_path")
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