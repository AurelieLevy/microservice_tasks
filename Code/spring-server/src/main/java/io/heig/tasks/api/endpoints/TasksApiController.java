package io.heig.tasks.api.endpoints;

import io.heig.tasks.api.TasksApi;
import io.heig.tasks.api.model.NewTask;
import io.heig.tasks.api.model.Task;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
        return null;
    }

    @Override
    public ResponseEntity<List<Task>> getTasks() {
        return null;
    }

    @Override
    public ResponseEntity<Task> postTask(NewTask body) {
        return null;
    }
}