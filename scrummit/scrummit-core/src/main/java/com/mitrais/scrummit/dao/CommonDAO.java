package com.mitrais.scrummit.dao;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * A Base common DAO based on MongoRepository with common methods
 * 
 * @author Reza_D
 * @param <T>
 *            Document Entity Object Type
 * @param <ID>
 *            String serializeable
 */
@NoRepositoryBean
public interface CommonDAO<T, ID extends Serializable> extends MongoRepository<T, ID> {
    List<T> findByModifiedBy(ObjectId id);

    List<T> findByCreatedBy(ObjectId id);
}
