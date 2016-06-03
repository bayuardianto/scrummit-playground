package com.mitrais.scrummit.bo.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.EpicBO;
import com.mitrais.scrummit.dao.EpicDAO;
import com.mitrais.scrummit.model.Epic;

/**
 * Created by Fathoni on 16/06/01.
 */
@Service
public class EpicBOImpl extends BaseBOImpl<Epic, EpicDAO> implements EpicBO {
    private static final Log log = LogFactory.getLog(CardBOImpl.class);

    @Override
    public List<Epic> listAll() {
        log.info("find all epics");
        return currentDAO.findAll();
    }

    @Override
    public Epic getById(String id) {
        log.info("find epic by id");
        return currentDAO.findOne(id);
    }

    @Override
    public List<Epic> getByName(String name) {
        log.info("find epics by name");
        return currentDAO.findByName(name);
    }

    @Override
    public Epic create(Epic epic) {
        log.info("save epic");
        return insert(epic);
    }

    @Override
    public Epic update(Epic epic) {
        log.info("update epic");
        Epic updateEpic = currentDAO.findOne(String.valueOf(epic.getId()));
        if (updateEpic != null){
            updateEpic.setName(epic.getName());
            updateEpic.setDescription(epic.getDescription());
        }
        return save(updateEpic);
    }

    @Override
    public Epic delete(String id) {
        log.info("delete epic");
        Epic epic = currentDAO.findOne(id);
        return epic != null ? delete(epic) : null;
    }
}
