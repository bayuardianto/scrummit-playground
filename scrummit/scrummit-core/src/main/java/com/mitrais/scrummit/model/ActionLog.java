package com.mitrais.scrummit.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Document(collection = "actionlogs")
public class ActionLog implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value="log_item")
    private String logItem;

    @Field(value="log_type")
    private String logType;

    @Field(value="ref_id")
    private ObjectId refId;
    
    @Field(value="user_comment")
    private String userComment;
    
    @Field(value="user_id")
    private ObjectId userId;

    @CreatedDate
    @Field(value="log_date")
    private Date logDate;
    
    public ActionLog() { super(); }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    public String getLogItem() {return logItem;}

    public void setLogItem(String logItem) { this.logItem = logItem; }

    public String getLogType() { return logType; }

    public void setLogType(String logType) { this.logType = logType; }

    public ObjectId getRefId() {
        return refId;
    }

    public void setRefId(ObjectId refId) {
        this.refId = refId;
    }
    
    public String getUserComment() { return userComment; }

    public void setUserComment(String userComment) { this.userComment = userComment; }
    
    public ObjectId getUserId() {
        return id;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }
    
    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}
