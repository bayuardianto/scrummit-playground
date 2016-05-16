package mitrais.bootcamp.scrummit.project.service;

import java.util.Arrays;

import mitrais.bootcamp.scrummit.config.ApplicationConfig;
import mitrais.bootcamp.scrummit.multitenancy.MultiTenantMongoDbFactory;
import mitrais.bootcamp.scrummit.project.model.Project;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableAutoConfiguration
public class Applicationx {// implements CommandLineRunner {

    private static final Log log           = LogFactory.getLog(Applicationx.class);
    ApplicationContext       ctx           = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    MongoTemplate            mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");

    private Project createProject(final String name) {
        Project p = new Project();
        p.set_id(new ObjectId());
        p.setName(name);
        return p;
    }

    // @Override
    public void run(final String... args) throws Exception {
        mongoTemplate.save(createProject("Phillipa"), "project");
        mongoTemplate.save(createProject("Phillipb"), "project");
        mongoTemplate.save(createProject("Phillipc"), "project");
        mongoTemplate.save(createProject("Phillipd"), "project");
        System.out.println("data from smcentral: "
                + Arrays.toString(this.mongoTemplate.findAll(Project.class).toArray()));

        ApplicationConfig.switchDbName("smtenant1");

        mongoTemplate.save(createProject("Phillipe"), "project");
        mongoTemplate.save(createProject("Phillipf"), "project");
        mongoTemplate.save(createProject("Phillipg"), "project");
        mongoTemplate.save(createProject("Philliph"), "project");

        System.out.println("data from smtenant1: "
                + Arrays.toString(this.mongoTemplate.findAll(Project.class).toArray()));

        // cleanup
        // this.mongoTemplate.dropCollection(Project.class);

        MultiTenantMongoDbFactory.clearDatabaseNameForCurrentThread();

        // cleanup
        // this.mongoTemplate.dropCollection(Project.class);

    }

    // public static void main(final String[] args) {
    // SpringApplication.run(Applicationx.class, args);
    // }

}