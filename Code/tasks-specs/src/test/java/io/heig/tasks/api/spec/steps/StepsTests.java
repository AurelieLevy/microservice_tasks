package io.heig.tasks.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.heig.tasks.ApiException;
import io.heig.tasks.ApiResponse;
import io.heig.tasks.api.StepApi;
import io.heig.tasks.api.dto.NewStep;
import io.heig.tasks.api.dto.Step;
import io.heig.tasks.api.spec.helpers.Environment;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by aurel on 19.01.2018.
 */
public class StepsTests {

    private Environment environment;
    private StepApi api;
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private final String API_URL = "http://localhost:8080/api";
    private Step step;
    private NewStep newStep;
    private Object invalidStep;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String idStep;



    public StepsTests(Environment environment){
        this.environment = environment;
        this.api = new StepApi();

    }

    @Given("^there is a Tasks server with an existing Task and an existing Exec and a step$")
    public void there_is_a_Tasks_server_with_an_existing_Task_and_an_existing_Exec_and_a_step() throws Throwable{
        assertNotNull(api);
    }

    @Given("^I have a valid step payload$")
    public void I_have_a_valid_step_payload() {
        newStep = new io.heig.tasks.api.dto.NewStep();
        newStep.setName("Step 1");
        newStep.setContext("Context of the step 1");
        newStep.setExecutionId("5a6a47cb6ec5e01f7a9fecb8");
        newStep.setStatus(NewStep.StatusEnum.RUNNING);
    }



    @Given("^I have an JSON step payload with incorrect parameters$")
    public void i_have_an_JSON_step_payload_with_incorrect_parameters() throws Throwable {
        newStep = new io.heig.tasks.api.dto.NewStep();
    }

    @Given("^I have an invalid step type payload \\(not JSON\\)$")
    public void i_have_an_invalid_step_type_payload_not_JSON() throws Throwable {
        invalidStep = new Object();
    }

    @When("^I POST to the (/steps) endpoint")
    public void I_POST_to_the_steps_endpoint(String endpoint) throws Throwable{
        if(invalidStep != null){
            HttpPost request = new HttpPost(API_URL + endpoint);
            StringEntity value = new StringEntity("My string that is not good");
            request.addHeader("content-type", "text/plain");
            request.setEntity(value);
            HttpResponse response = httpClient.execute(request);

            lastApiResponse = null;
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = response.getStatusLine().getStatusCode();
        }
        else{
            try
            {
                lastApiResponse = api.postStepWithHttpInfo(newStep);
                lastApiCallThrewException = false;
                lastApiException = null;
                lastStatusCode = lastApiResponse.getStatusCode();
            }
            catch (ApiException e)
            {
                lastApiCallThrewException = true;
                lastApiResponse = null;
                lastApiException = e;
                lastStatusCode = lastApiException.getCode();
            }
        }
    }


    @Given("^I have a step id$")
    public void i_have_a_step_id() throws Throwable {
        idStep="5a6a53ad6ec5e02c520f0aeb";
    }


    @Given("^I have an incorrect step id$")
    public void i_have_an_incorrect_step_id() throws Throwable {
        idStep="9999999";
    }


    @When("^I GET to the /step/STEP_ID endpoint$")
    public void i_GET_to_the_step_STEP_ID_endpoint() throws Throwable {
        try {
            lastApiResponse= api.getStepByIdWithHttpInfo(idStep);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^I receive for step a (\\d+) status code$")
    public void i_receive_for_step_a_status_code(int status) throws Throwable {
        assertEquals(status, lastStatusCode);
    }

}
