#!/bin/sh

echo "---- Building tasks-api-server -----"
cd code/spring-server/
mvn clean install

echo "---- Copying  tasks-api-server.jar ----"
cp ./target/tasks-1.0.0.jar  ../../images/tasks/tasks-1.0.0.jar 