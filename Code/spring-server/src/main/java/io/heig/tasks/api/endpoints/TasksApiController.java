package io.heig.tasks.api.endpoints;

import io.heig.tasks.api.TasksApi;
import io.heig.tasks.api.model.NewTask;
import io.heig.tasks.api.model.Task;
import io.heig.tasks.entities.TaskEntity;
import io.heig.tasks.repositories.TaskRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TasksApiController implements TasksApi {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<Task> getTaskById(@PathVariable("task_id")String taskId) {

        if(taskId == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        TaskEntity taskEntity = taskRepository.findOne(taskId);
        if(taskEntity == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<Task>(taskEntity.getDTO(), HttpStatus.OK);
        }

        // TODO: populate execution and set self path (preferably auto-generated)

        /*if(taskId.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Task task = new Task();
            task.setTaskId("test");
            task.setDescription("test pour la task");
            task.setName("testeur");
            task.setExecs(new ArrayList<Execution>());

            return new ResponseEntity<>(task, HttpStatus.OK);
        }*/

    }

    @Override
    public ResponseEntity<List<Task>> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        for(TaskEntity t : taskRepository.findAll()){
            tasks.add(t.getDTO());
        }

        /*Task task = new Task();
        task.setTaskId("UUID_1");
        task.setName("testeur");
        task.setDescription("Test pour la task");
        task.setExecs(new ArrayList<Execution>());

        Task task2 = new Task();
        task2.setTaskId("UUID_TOTALLY_UNIQUE");
        task2.setName("HelloWorld");
        task2.setDescription("Task that makes Hello World around the world");
        task2.setExecs(new ArrayList<Execution>());

        tasks.add(task);
        tasks.add(task2);*/
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> postTask(@ApiParam(value = "The task details" ,required=true ) @RequestBody @Valid NewTask body) {
        TaskEntity t = new TaskEntity();
        t.setName(body.getName());
        t.setDescription(body.getDescription());
        t = taskRepository.insert(t);
        return new ResponseEntity<Task>(t.getDTO(), HttpStatus.CREATED);
    }
}
