package com.mitrais.scrummit.dao.common;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonDAO<T, ID extends Serializable> extends MongoRepository<T, ID> {

}
