package io.heig.tasks.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.heig.tasks.ApiException;
import io.heig.tasks.ApiResponse;
import io.heig.tasks.api.TaskApi;
import io.heig.tasks.api.dto.NewTask;
import io.heig.tasks.api.dto.Task;
import io.heig.tasks.api.spec.helpers.Environment;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationTasksTests {

    private Environment environment;
    private TaskApi api;

    private int count;

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private final String API_URL = "http://localhost:8080/api";
    private Task task;
    private NewTask newTask;
    private String idTask;
    private Object invalidTask;
    private List<Task> list= new ArrayList<Task>();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;


    public CreationTasksTests(Environment environment)
    {
        this.environment = environment;
        this.api= environment.getApi();
    }

    @Given("^there is a Tasks server$")
    public void there_is_a_Tasks_server() throws Throwable {
        assertNotNull(api);
    }

    @When("^I GET the /tasks endpoint$")
    public void i_GET_the_tasks_endpoint() throws Throwable {
        try {
            lastApiResponse= api.getTasksWithHttpInfo();
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int status) throws Throwable {
        assertEquals(status, lastStatusCode);
    }

    @Given("^I have a valid task payload$")
    public void i_have_a_valid_task_payload() throws Throwable {
        newTask = new io.heig.tasks.api.dto.NewTask();
        newTask.setName("Task 1");
        newTask.setDescription("description of task 1");
    }

    @When("^I POST to the (/task) endpoint$")
    public void i_POST_to_the_task_endpoint(String endpoint) throws Throwable {
        if(invalidTask != null){
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
                lastApiResponse = api.postTaskWithHttpInfo(newTask);
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

    @Given("^I have an invalid  type payload \\(not JSON\\)$")
    public void i_have_an_invalid_type_payload_not_JSON() throws Throwable {
        invalidTask = new Object();
    }

    @Given("^I have an JSON payload with incorrect parameters$")
    public void i_have_an_JSON_payload_with_incorrect_parameters() throws Throwable {
        newTask = new io.heig.tasks.api.dto.NewTask();
    }

    @Given("^I have a task id$")
    public void i_have_a_task_id() throws Throwable {
        idTask="9999999";
    }

    @Given("^I have an incorrect task id$")
    public void i_have_an_incorrect_task_id() throws Throwable {
        idTask="9999999";
    }

    @When("^I GET to the /tasks/TASK_ID endpoint$")
    public void i_GET_to_the_tasks_TASK_ID_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try {
            lastApiResponse= api.getTaskByIdWithHttpInfo(idTask);
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

}
