package io.heig.tasks.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import io.heig.tasks.api.model.Step;
import io.heig.tasks.api.model.Step.StatusEnum;

import java.io.Serializable;

@Document(collection = "steps")
public class StepEntity implements Serializable {

    @Id
    private String id;

    private String name;
    private String context;
    private Long creationDate;
    private StatusEnum status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Step getDTO(){
        Step s = new Step();
        s.setName(getName());
        s.setContext(getContext());
        s.setCreationDate(getCreationDate());
        s.setStatus(getStatus());
        s.setStepId(getId());

        return s;
    }
}
