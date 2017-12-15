package io.heig.tasks.api.endpoints;

import io.heig.tasks.api.TasksApi;
import io.heig.tasks.api.model.Execution;
import io.heig.tasks.api.model.NewTask;
import io.heig.tasks.api.model.Task;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TasksApiController implements TasksApi {


    @Override
    public ResponseEntity<Task> getTaskById(String taskId)
    {
            Task task = new Task();
            task.setTaskId("test");
            task.setDescription("test pour la task");
            task.setName("testeur");
            task.setExecs(new ArrayList<Execution>());

            return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Task>> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        Task task = new Task();
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
        tasks.add(task2);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> postTask(@ApiParam(value = "The task details" ,required=true ) @RequestBody @Valid NewTask body) {
        return new ResponseEntity<Task>(HttpStatus.CREATED);
    }

}