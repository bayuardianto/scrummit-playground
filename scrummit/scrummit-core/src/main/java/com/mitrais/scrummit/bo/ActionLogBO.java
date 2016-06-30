package com.mitrais.scrummit.bo;

import org.bson.types.ObjectId;
import com.mitrais.scrummit.model.ActionLog;

import java.util.List;

public interface ActionLogBO extends BaseBO<ActionLog> {
	List<ActionLog> listAllLogs();

    ActionLog create(String logItem, String logType, ObjectId refId, String userComment);
}
