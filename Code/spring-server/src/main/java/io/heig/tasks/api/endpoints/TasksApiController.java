package io.heig.tasks.api.endpoints;

import io.heig.tasks.api.TasksApi;
import io.heig.tasks.api.model.Exec;
import io.heig.tasks.api.model.NewTask;
import io.heig.tasks.api.model.Task;

import io.heig.tasks.entities.TaskEntity;
import io.heig.tasks.repositories.TaskRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TasksApiController implements TasksApi {


    @Override
    public ResponseEntity<Task> getTaskById(Long taskId) {

        if(taskId != 1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Task task = new Task();
            task.setTaskId("test");
            task.setDescription("test pour la task");
            task.setName("testeur");
            task.setExecs(new ArrayList<Exec>());

            return new ResponseEntity<>(task, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Task>> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        Task task = new Task();
        task.setTaskId("UUID_1");
        task.setName("testeur");
        task.setDescription("Test pour la task");
        task.setExecs(new ArrayList<Exec>());

        Task task2 = new Task();
        task2.setTaskId("UUID_TOTALLY_UNIQUE");
        task2.setName("HelloWorld");
        task2.setDescription("Task that makes Hello World around the world");
        task2.setExecs(new ArrayList<Exec>());

        tasks.add(task);
        tasks.add(task2);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> postTask(NewTask body) {
        return new ResponseEntity<Task>(HttpStatus.CREATED);
    }
}