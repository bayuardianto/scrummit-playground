package com.mitrais.scrummit.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * An Base common DAO to do database operation using MongoTemplate with common
 * methods
 * 
 * @author Reza_D
 * @param <T>
 *            Document Entity Object Type
 * @param <ID>
 *            String serializeable
 */
@NoRepositoryBean
public abstract class CommonCustomDAO<T, ID extends Serializable> {
    @Autowired
    protected MongoTemplate mongoTemplate;

}
