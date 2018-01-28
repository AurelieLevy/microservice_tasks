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

