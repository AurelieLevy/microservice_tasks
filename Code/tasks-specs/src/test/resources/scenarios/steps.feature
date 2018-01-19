Feature: features for step

  Background:
    Given there is a Tasks server with an existing Task and an existing Exec

  Scenario: create a valid step
    Given I have a valid step payload
    When I POST to the /step endpoint
    Then I receive a 201 status code

  Scenario: failed to create a step
    Given I have an invalid  type payload (not JSON)
    When I POST to the /step endpoint
    Then I receive a 415 status code


  Scenario: failed to create a step
    Given I have an JSON payload with incorrect parameters
    When I POST to the /step endpoint
    Then I receive a 422 status code

#  Background:
#    Given there is a Tasks server
#
#  Scenario: get a list of all existing tasks
#    When I GET the /tasks endpoint
#    Then I receive a 200 status code

#  Scenario: create a valid task
#    Given I have a valid task payload
#    When I POST to the /task endpoint
#    Then I receive a 201 status code

#  Scenario: failed to create a task
#    Given I have an invalid  type payload (not JSON)
#    When I POST to the /task endpoint
#    Then I receive a 415 status code

#  Scenario: failed to create a task
#    Given I have an JSON payload with incorrect parameters
#    When I POST to the /task endpoint
#    Then I receive a 422 status code

#  Scenario:get a precise task
#    Given I have a task id
#    When I GET to the /tasks/TASK_ID endpoint
#    Then I receive a 200 status code

#  Scenario: failed to get a precise task
#    Given I have an incorrect task id
#    When I GET to the /tasks/TASK_ID endpoint
#    Then I receive a 404 status code