package io.heig.tasks.api.endpoints;

import io.heig.tasks.repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StepsApiController {

    @Autowired
    private StepRepository stepRepository;


}
