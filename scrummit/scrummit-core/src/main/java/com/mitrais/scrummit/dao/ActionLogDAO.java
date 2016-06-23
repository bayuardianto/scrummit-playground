package com.mitrais.scrummit.dao;

import com.mitrais.scrummit.model.ActionLog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionLogDAO extends MongoRepository<ActionLog, String> {

}
