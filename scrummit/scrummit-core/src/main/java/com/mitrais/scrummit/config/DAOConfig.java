package com.mitrais.scrummit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

@EnableMongoAuditing()
@Configuration
@Import(ApplicationConfig.class)
public class DAOConfig extends MongoRepositoriesAutoConfiguration {
    @Autowired
    private ApplicationConfig mongoConfig;

    @Bean
    public MongoRepositoryFactory getMongoRepositoryFactory() {
        try {
            return new MongoRepositoryFactory(mongoConfig.mongoTemplate());
        } catch (Exception e) {
            throw new RuntimeException("error creating mongo repository factory", e);
        }
    }

}