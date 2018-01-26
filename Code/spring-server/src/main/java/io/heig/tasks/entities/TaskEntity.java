package io.heig.tasks.entities;

import io.heig.tasks.api.model.Execution;
import io.heig.tasks.api.model.Task;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Document(collection = "tasks")
public class TaskEntity implements Serializable {

    private static final String RELATIVE_PATH = "/tasks/";
    @Id
    private String id;

    private String taskId;
    private long creationDate;
    private String name;
    private String description;
    private ArrayList<ExecutionEntity> executions;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public long getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(long creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ArrayList<ExecutionEntity> getExecutions()
    {
        return executions;
    }

    public void setExecutions(ArrayList<ExecutionEntity> executions)
    {
        this.executions = executions;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Task getDTO(){
        Task t = new Task();
        t.setId(getId());
        t.setTaskId(getTaskId());
        t.setDescription(getDescription());
        t.setName(getName());
        t.setCreationDate(getCreationDate());
        t.setSelf(RELATIVE_PATH + getId());
        ArrayList<Execution> executions=  new ArrayList<>();
        if(getExecutions()!=null)
        {
            t.setExecutions(getExecutions()
                    .stream()
                    .map(ExecutionEntity::getDTO)
                    .collect(Collectors.toList()));
        }
        else
        {
            t.setExecutions(executions);
        }
        return t;
    }
}
