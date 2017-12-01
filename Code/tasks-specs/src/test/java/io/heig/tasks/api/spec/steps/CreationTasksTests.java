package io.heig.tasks.api.spec.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.heig.tasks.ApiException;
import io.heig.tasks.ApiResponse;
import io.heig.tasks.api.TaskApi;
import io.heig.tasks.api.dto.NewTask;
import io.heig.tasks.api.dto.Task;
import io.heig.tasks.api.spec.helpers.Environment;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationTasksTests {

    private Environment environment;
    private TaskApi api;

    private int count;

    Task task;
    NewTask newTask;
    private List<Task> list= new ArrayList<Task>();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String invalidPayload;


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

    @When("^I POST to the /task endpoint$")
    public void i_POST_to_the_task_endpoint() throws Throwable {
        try {
            lastApiResponse = api.postTaskWithHttpInfo(newTask);
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

    @Given("^I have an invalid  type payload \\(not JSON\\)$")
    public void i_have_an_invalid_type_payload_not_JSON() throws Throwable {

        invalidPayload = new String("invalidPayload");
        /*NewTask newTask = new NewTask();
        newTask.setName("fdsf");
        newTask.setDescription("lolilol");
        api.postTaskWithHttpInfo(newTask);

        lastApiResponse = api.getTasksWithHttpInfo();
        assertEquals(406, lastApiResponse.getStatusCode());*/

        /*URL url = new URL(api.getApiClient().getBasePath());
        String badPayload = "aaaaaaaa";

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

        connection.setRequestProperty("Content-length", String.valueOf((Integer)badPayload.getBytes().length));
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(badPayload);
        wr.close();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while((line = rd.readLine()) != null){
            response.append(line);
        }

        rd.close();*/

        //TODO
    }

    @When("^I POST the /tasks endpoint$")
    public void i_POST_the_tasks_endpoint() throws Throwable {
        URL url = new URL(api.getApiClient().getBasePath());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

        connection.setRequestProperty("Content-length", String.valueOf((Integer)invalidPayload.getBytes().length));
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(invalidPayload);
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response;
        String reponseSrv = null;
        while ((response = in.readLine()) != null){
            reponseSrv += response;
        }
        in.close();
        assertTrue(false);
        lastStatusCode = Integer.parseInt(reponseSrv.substring(0, 3));

        /*try {
            lastApiResponse = api.postTaskWithHttpInfo(invalidPayload);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }*/
    }

    @Given("^I have an JSON payload with incorrect parameters$")
    public void i_have_an_JSON_payload_with_incorrect_parameters() throws Throwable {
        newTask = new io.heig.tasks.api.dto.NewTask();
    }

    @When("^I GET to the /tasks/TASK_ID endpoint$")
    public void i_GET_to_the_tasks_TASK_ID_endpoint() throws Throwable {
        try {
            lastApiResponse= api.getTaskByIdWithHttpInfo("1");
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            assertNotNull(lastStatusCode);
            assertEquals(200, lastStatusCode);
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

}
