package com.mitrais.scrummit.tester;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mitrais.scrummit.config.ApplicationConfig;
import com.mitrais.scrummit.model.project.Project;
import com.mitrais.scrummit.multitenancy.MultiTenantMongoDbFactory;
public class MongoApp {

    private static final Log log = LogFactory.getLog(MongoApp.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        MultiTenantMongoDbFactory mongoDbFactory = (MultiTenantMongoDbFactory) ctx.getBean("mongoDbFactory");

        MongoOperations mongoOps = new MongoTemplate(mongoDbFactory);

        Project p = new Project();
        p.set_id(new ObjectId());
        p.setName("Paman Sam");

        // Insert is used to initially store the object into the database.
        mongoOps.insert(p);
        log.info("Insert: " + p);

        // Find
        p = mongoOps.findById(p.get_id(), Project.class);
        log.info("Found: " + p);

        // Update
        mongoOps.updateFirst(query(where("name").is("Paman Sam")), update("name", "Joex"), Project.class);
        p = mongoOps.findOne(query(where("name").is("Joex")), Project.class);
        log.info("Updated: " + p);

        // Delete
        mongoOps.remove(p);

        // Check that deletion worked
        List<Project> people = mongoOps.findAll(Project.class);
        log.info("Number of people = : " + people.size());

        // mongoOps.dropCollection(Project.class);
    }
}