package com.mitrais.scrummit.dao.common;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class CommonDAOImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements CommonDAO<T, ID> {
    private MongoEntityInformation<T, ID> metadata;
    private MongoOperations               mongoOperations;

    public MongoEntityInformation<T, ID> getMetadata() {
        return metadata;
    }

    public MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    public CommonDAOImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.metadata = metadata;
        this.mongoOperations = mongoOperations;
    }

}
