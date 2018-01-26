package io.heig.tasks.entities;

import io.heig.tasks.api.model.Execution;
import io.heig.tasks.api.model.Step;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class ExecutionEntity implements Serializable {

    private final static String RELATIVE_PATH = "/executions/";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private long creationDate;

    private ArrayList<StepEntity> steps = new ArrayList<>();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(long creationDate)
    {
        this.creationDate = creationDate;
    }


    public ArrayList<StepEntity> getSteps()
    {
        return steps;
    }

    public void setSteps(ArrayList<StepEntity> steps)
    {
        this.steps = steps;
    }

    public Execution getDTO(){
        Execution e = new Execution();
        e.setId(getId());
        e.setName(getName());
        e.setCreationDate(getCreationDate());
        e.setSelf(RELATIVE_PATH + getId());
        ArrayList<Step> steps = new ArrayList<>();
        if(getSteps() != null) {
            e.setSteps(getSteps()
                    .stream()
                    .map(StepEntity::getDTO)
                    .collect(Collectors.toList()));
        } else {
            e.setSteps(steps);
        }
        return e;
    }


}
