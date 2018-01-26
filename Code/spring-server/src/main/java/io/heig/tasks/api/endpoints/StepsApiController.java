package io.heig.tasks.api.endpoints;

import io.heig.tasks.api.StepsApi;
import io.heig.tasks.api.model.NewStep;
import io.heig.tasks.api.model.Step;
import io.heig.tasks.entities.ExecutionEntity;
import io.heig.tasks.entities.StepEntity;
import io.heig.tasks.repositories.ExecutionRepository;
import io.heig.tasks.repositories.StepRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Controller
public class StepsApiController implements StepsApi{

    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private ExecutionRepository executionRepository;

    @Override
    public ResponseEntity<Step> getStepById(@PathVariable("step_id") String stepId) {
       if(stepId == null){
           return new ResponseEntity<Step>(HttpStatus.NOT_FOUND);
       }
        StepEntity stepEntity = stepRepository.findOne(stepId);
       if(stepEntity == null){
           return new ResponseEntity<Step>(HttpStatus.NOT_FOUND);
       }
       else{
           return new ResponseEntity<Step>(stepEntity.getDTO(), HttpStatus.OK);
       }
    }

    @Override
    public ResponseEntity<Step> postStep(@ApiParam(value = "The step details", required = true) @RequestBody NewStep body) {
        StepEntity s = new StepEntity();
        s.setStatus(body.getStatus());
        s.setCreationDate(System.currentTimeMillis());
        s.setContext(body.getContext());
        s.setName(body.getName());
        s = stepRepository.insert(s);

        ExecutionEntity executionEntity;
        if(executionRepository != null)
        {
            executionEntity = executionRepository.findOne(body.getExecutionId());
        }
        else
        {
            return new ResponseEntity<Step>(s.getDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(executionEntity != null)
        {
            ArrayList<StepEntity> steps;
            if (executionEntity.getSteps() == null)
            {
                steps = new ArrayList<>();
            }
            else
            {
                steps = executionEntity.getSteps();
            }

            steps.add(s);
            executionEntity.setSteps(steps);

            executionRepository.save(executionEntity);
            return new ResponseEntity<Step>(s.getDTO(), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<Step>(s.getDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
