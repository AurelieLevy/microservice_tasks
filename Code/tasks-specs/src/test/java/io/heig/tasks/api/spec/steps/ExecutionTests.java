package io.heig.tasks.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.heig.tasks.ApiException;
import io.heig.tasks.ApiResponse;
import io.heig.tasks.api.ExecutionApi;
import io.heig.tasks.api.TaskApi;
import io.heig.tasks.api.dto.NewExecution;
import io.heig.tasks.api.spec.helpers.Environment;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExecutionTests
{

    private Environment environment;
    private ExecutionApi api;
    private final String API_URL = "http://localhost:8080/api";


    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private NewExecution newExecution;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String idExecution;
    private Object invalidExecution;



    public ExecutionTests(Environment environment)
    {
        this.environment = environment;
        this.api= new ExecutionApi();
    }

    @Given("^there is a Execution server$")
    public void there_is_a_Execution_server() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull(api);;
    }

    @Given("^I have a valid execution payload$")
    public void i_have_a_valid_execution_payload() throws Throwable {
        newExecution = new NewExecution();
        newExecution.setName("Execution 1");
    }

    @Given("^I have an JSON execution payload with incorrect parameters$")
    public void i_have_an_JSON_execution_payload_with_incorrect_parameters() throws Throwable {
        newExecution = new NewExecution();
    }

    @When("^I GET to the /executions/EXECUTION_ID endpoint$")
    public void i_GET_to_the_executions_EXECUTION_ID_endpoint() throws Throwable {
        try {
            lastApiResponse= api.getExecutionByIdWithHttpInfo(idExecution);
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

    @When("^I POST to the (/execution) endpoint$")
    public void i_POST_to_the_execution_endpoint(String endpoint) throws Throwable {
        if(invalidExecution != null){
            HttpPost request = new HttpPost(API_URL + endpoint + "s");
            StringEntity value = new StringEntity("my String that is not good");
            request.addHeader("content-type", "text/plain");
            request.setEntity(value);
            HttpResponse response = httpClient.execute(request);

            lastApiResponse = null;
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = response.getStatusLine().getStatusCode();
        } else
        {
            try
            {
                lastApiResponse = api.postExecutionWithHttpInfo(newExecution);
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

    @Given("^I have a execution id$")
    public void i_have_a_execution_id() throws Throwable {
        idExecution="5a6a52e96ec5e02c520f0aea";
    }

    @Given("^I have an incorrect execution id$")
    public void i_have_an_incorrect_execution_id() throws Throwable {
        idExecution="9999999";
    }

    @Then("^I receive for execution a (\\d+) status code$")
    public void i_receive_a_status_code(int status) throws Throwable {
        assertEquals(status, lastStatusCode);
    }


}
