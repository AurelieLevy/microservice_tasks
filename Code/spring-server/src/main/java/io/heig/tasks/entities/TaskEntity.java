package io.heig.tasks.entities;

import io.heig.tasks.api.model.Task;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Document(collection = "tasks")
public class TaskEntity implements Serializable {

    @Id
    private String id;

    private long creationDate;
    private String name;
    private String description;
    private ArrayList<ExecEntity> exec;

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
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

    public ArrayList<ExecEntity> getExec() {
        return exec;
    }

    public void setExec(ArrayList<ExecEntity> exec) {
        this.exec = exec;
    }

    public Task getDTO(){
        Task t = new Task();
        t.setTaskId(getId());
        t.setDescription(getDescription());
        t.setName(getName());
        t.setCreationDate(getCreationDate());
        return t;
    }
}
