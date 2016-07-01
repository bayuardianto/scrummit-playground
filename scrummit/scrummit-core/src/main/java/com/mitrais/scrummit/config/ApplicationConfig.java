package com.mitrais.scrummit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mitrais.scrummit.multitenancy.MultiTenantMongoDbFactory;
import com.mitrais.scrummit.util.BOAspect;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.mitrais.scrummit.dao")
@EnableAutoConfiguration
@PropertySources({
    @PropertySource(value = "classpath:mongo-config.properties", ignoreResourceNotFound = true),
    @PropertySource(value = "file:${catalina.home}/conf/mongo-config.properties", ignoreResourceNotFound = true)
})
@EnableAspectJAutoProxy()
public class ApplicationConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.host}")
    private String mongoDBHost;

    @Value("${mongodb.port}")
    private int    mongoDBPort;

    @Value("${mongodb.db.central}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongo() throws Exception {

        return new MongoClient(mongoDBHost, mongoDBPort);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.mitrais.scrummit";
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Override
    @Bean
    public MultiTenantMongoDbFactory mongoDbFactory() throws Exception {
        return new MultiTenantMongoDbFactory(mongo(), databaseName);
    }

    public static void switchDbName(String dbName) {
        MultiTenantMongoDbFactory.setDatabaseNameForCurrentThread(dbName);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BOAspect boAspect() {
        return new BOAspect();
    }
}
