package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.NewStep;
import io.swagger.model.Step;
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
public class StepsApiController implements StepsApi {



    public ResponseEntity<Task> getStepById(@ApiParam(value = "Task ID",required=true ) @PathVariable("step_id") Long stepId) {
        // do some magic!
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

    public ResponseEntity<Step> postStep(@ApiParam(value = "The step details" ,required=true )  @Valid @RequestBody NewStep body) {
        // do some magic!
        return new ResponseEntity<Step>(HttpStatus.OK);
    }

}
