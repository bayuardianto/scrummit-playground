package mitrais.bootcamp.scrummit.repositories;

import mitrais.bootcamp.scrummit.project.model.Project;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDAO extends MongoRepository<Project, String> {

}
