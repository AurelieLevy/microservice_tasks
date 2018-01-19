package io.heig.tasks.api.endpoints;

import io.heig.tasks.api.ExecutionsApi;
import io.heig.tasks.api.model.Execution;
import io.heig.tasks.api.model.NewExecution;
import io.heig.tasks.api.model.NewTask;
import io.heig.tasks.api.model.Task;
import io.heig.tasks.entities.ExecutionEntity;
import io.heig.tasks.entities.TaskEntity;
import io.heig.tasks.repositories.ExecutionRepository;
import io.heig.tasks.repositories.TaskRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ExecutionApiController implements ExecutionsApi {

    @Autowired
    private ExecutionRepository ExecutionRepository;
    private TaskRepository taskRepository ;


    @Override
    public ResponseEntity<Execution> getExecutionById(@PathVariable("execution_id")String ExecutionId) {

        if(ExecutionId == null){
            return new ResponseEntity<Execution>(HttpStatus.NOT_FOUND);
        }

        ExecutionEntity executionEntity = ExecutionRepository.findOne(ExecutionId);
        if(executionEntity == null){
            return new ResponseEntity<Execution>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<Execution>(executionEntity.getDTO(), HttpStatus.OK);
        }

        // TODO: populate execution and set self path (preferably auto-generated)
    }


    @Override
    public ResponseEntity<Execution> postExecution(@ApiParam(value = "The execution details" ,required=true ) @RequestBody @Valid NewExecution body) {
        ExecutionEntity e = new ExecutionEntity();
        e.setCreationDate(System.currentTimeMillis());
        e.setName(body.getName());
        e = ExecutionRepository.insert(e);
        TaskEntity taskEntity =  taskRepository.findOne(body.getTaskId());
        taskEntity.getExecution().add(e);
        taskEntity = taskRepository.save(taskEntity);
        return new ResponseEntity<Execution>(e.getDTO(), HttpStatus.CREATED);
    }
}
