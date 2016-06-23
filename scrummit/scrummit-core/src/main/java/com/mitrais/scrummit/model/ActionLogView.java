package com.mitrais.scrummit.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class ActionLogView implements Serializable {
	@JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String logItem;

    private String logType;

    private String userComment;

    private String logDate;

    private String userName;

    public ActionLogView() { super(); }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    public String getLogItem() { return logItem; }

    public void setLogItem(String logItem) { this.logItem = logItem; }

    public String getLogType() { return logType; }

    public void setLogType(String logType) { this.logType = logType; }

    public String getUserComment() { return userComment; }

    public void setUserComment(String userComment) { this.userComment = userComment; }

    public String getLogDate() { return logDate; }

    public void setLogDate(String logDate) { this.logDate = logDate; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }
}
