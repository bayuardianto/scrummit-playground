package com.mitrais.scrummit.bo.impl;

import com.mitrais.scrummit.bo.ActionLogBO;
import com.mitrais.scrummit.dao.ActionLogDAO;
import com.mitrais.scrummit.model.ActionLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionLogBOImpl implements ActionLogBO {
	
	@Autowired
	ActionLogDAO actionLogDAO;
	
	private static final Log log = LogFactory.getLog(ActionLogBOImpl.class);


    @Override
    public List<ActionLog> listAllLogs() {
        List<ActionLog> actionLogs = actionLogDAO.findAll();

        return actionLogs;
    }

    @Override
    public ActionLog create(String logItem, String logType, String userComment) {
        ActionLog actionLog = new ActionLog();
        actionLog.setLogType(logType);
        actionLog.setLogItem(logItem);
        actionLog.setUserComment(userComment);
        return actionLogDAO.insert(actionLog);
    }
}
