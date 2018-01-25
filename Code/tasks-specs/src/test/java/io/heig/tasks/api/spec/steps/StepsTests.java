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
        this.api = environment.getStepApi();

    }

    @Given("^there is a Tasks server with an existing Task and an existing Exec$")
    public void there_is_a_Tasks_server_with_an_existing_Task_and_an_existing_Exec() throws Throwable{
        assertNotNull(api);
    }

    @Given("^I have a valid step payload$")
    public void I_have_a_valid_step_payload() {
        newStep = new io.heig.tasks.api.dto.NewStep();
        newStep.setName("Step 1");
        newStep.setContext("Context of the step 1");
    }



    @Given("^I have an JSON step payload with incorrect parameters$")
    public void i_have_an_JSON_step_payload_with_incorrect_parameters() throws Throwable {
        newStep = new io.heig.tasks.api.dto.NewStep();
    }

    @When("^I POST to the (/step) endpoint")
    public void I_POST_to_the_step_endpoint(String endpoint) throws Throwable{
        if(invalidStep != null){
            HttpPost request = new HttpPost(API_URL + endpoint + "s");
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

    @When("^I GET to the /steps/STEP_ID endpoint$")
    public void i_GET_to_the_steps_STEP_ID_endpoint() throws Throwable {
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

    @Given("^I have a step id$")
    public void i_have_a_execution_id() throws Throwable {
        idStep="5a6a53ad6ec5e02c520f0aeb";
    }

    @Then("^I receive for step a (\\d+) status code$")
    public void i_receive_for_step_a_status_code(int status) throws Throwable {
        assertEquals(status, lastStatusCode);
    }

}
