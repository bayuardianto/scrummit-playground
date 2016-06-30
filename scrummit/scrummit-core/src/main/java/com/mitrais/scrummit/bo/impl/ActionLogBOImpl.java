package com.mitrais.scrummit.bo.impl;

import com.mitrais.scrummit.bo.ActionLogBO;
import com.mitrais.scrummit.dao.ActionLogDAO;
import com.mitrais.scrummit.model.ActionLog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.List;

@Service
public class ActionLogBOImpl extends BaseBOImpl<ActionLog, ActionLogDAO> implements ActionLogBO {
	
	private static final Log log = LogFactory.getLog(ActionLogBOImpl.class);


    @Override
    public List<ActionLog> listAllLogs() {
        List<ActionLog> actionLogs = currentDAO.findAll();
        return actionLogs;
    }

    @Override
    public ActionLog create(String logItem, String logType, ObjectId refId, String userComment) {
        ActionLog actionLog = new ActionLog();
        actionLog.setLogType(logType);
        actionLog.setLogItem(logItem);
        actionLog.setRefId(refId);
        actionLog.setUserComment(userComment);
        return insert(actionLog);
    }
}
