#!/bin/sh

echo "---- Building tasks-api-server -----"
cd code/spring-server/
mvn clean install

echo "---- Copying  tasks-api-server.jar ----"
cp /target/tasks-api-server-1.0.0.jar  ../../images/tasks-api-server-1.0.0.jar 

cd ../../images/

echo "---- Building the task image -----"
docker build -t lozzikit/tasks .