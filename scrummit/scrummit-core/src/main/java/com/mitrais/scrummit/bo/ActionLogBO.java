package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.ActionLog;

import java.util.List;

public interface ActionLogBO {
	List<ActionLog> listAllLogs();

    ActionLog create(String logItem, String logType, String userComment);
}
