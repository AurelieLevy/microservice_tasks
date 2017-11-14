package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.NewTask;
import io.swagger.model.Task;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T13:49:00.734Z")

@Controller
public class TasksApiController implements TasksApi {



    public ResponseEntity<Task> getTaskById(@ApiParam(value = "Task ID",required=true ) @PathVariable("task_id") Long taskId) {
        // do some magic!
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

    public ResponseEntity<List<Task>> getTasks() {
        // do some magic!
        return new ResponseEntity<List<Task>>(HttpStatus.OK);
    }

    public ResponseEntity<Task> postTask(@ApiParam(value = "The task details" ,required=true )  @Valid @RequestBody NewTask body) {
        // do some magic!
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

}
