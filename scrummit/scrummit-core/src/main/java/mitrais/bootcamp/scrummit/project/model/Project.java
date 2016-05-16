package mitrais.bootcamp.scrummit.project.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "project")
public class Project {
    @Id
    private ObjectId _id;
    @Field(value="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId objectId) {
        this._id = objectId;
    }

    @Override
    public String toString() {
        
        return "\nProject{_id: '" + this._id + "' , name: '" + this.name + "'}\n";
    }

}
