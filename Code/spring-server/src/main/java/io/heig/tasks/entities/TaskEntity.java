package io.heig.tasks.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Document(collection = "tasks")
public class TaskEntity implements Serializable {

    @Id
    private long id;

    private String name;
    private String description;
    private ExecEntity[] exec;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExecEntity[] getExec() {
        return exec;
    }

    public void setExec(ExecEntity[] exec) {
        this.exec = exec;
    }
}
