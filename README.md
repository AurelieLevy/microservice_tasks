# LozziKiz MicroService - Tasks
## Introduction
With this microservice, you will be able to monitor yours tasks, theirs executions and steps.
This document will explain to you how to install and use it.

## Build and run
The first step is to run the **build-image-server.sh** file. You will need to use a command line:
```
$ sh build-image-server.sh
```

Then, to run the `database` and the `server`, you will need docker. If you don't already have it, you can found some informations here:

<center> <a href="https://www.docker.com/#/get_started"> https://www.docker.com/#/get_started </a> </center>

With your docker, you will need to do a
```
$ docker-compose up --build
```
if it's the first time, or without the ``` --build ``` if not.

## Tests
To run the tests and verify that everything is ok, you will need to use the script **test-image.sh** with the following command:

``` 
$ sh test-image.sh
```
You will see the results just after the run.

## Admin Panel

An admin panel is available at the address `localhost:3000` 

This panel shows the status of your tasks and the details of the executions of each tasks when you click on `details` 

To have more details on this panel please see the readme in the folder `Code/AdminPanel`

## API documentation

The API documentation is available at the address [http://localhost:8080/api](http://localhost:8080/api)

## Use case


The interest of this service is to do that directly in your code, to do so follow this steps :

**1** - You need to add a Task which is generally the same for a long period of time. To do so, send a POST request to `http://localhost:8080/api/tasks` with a `name`, a `description` and a unique `task_id`.

You can use the interactif doc to go faster => [http://localhost:8080/api](http://localhost:8080/api)

**2** - Set the `task_id` as a constant in the class/function you want to monitor. Add an execution for this task by sending a POST request to `http://localhost:8080/api/executions` using the `task_id` when you start a new execution of your task. You can give the execution a `name` if you have the need to identify the differents executions.

Use the api's response to get the unique execution `id` and store it as a temporary variable accessible from all the functions used in the execution.

**3** - Finally use the execution `id` to add the differents step of your execution. Each time you want to mark a step of your code send a POST request to `http://localhost:8080/api/steps` with the actual `execution_id`. You can specify a `status` that can be `Running`, `Error`, `Log` or `Success`. You can also give the step a `name` and a `context` to help you debug the code in case of an error.

