Feature: features for tasks

  Background:
    Given there is a Tasks server

  Scenario: create a valid task
    Given I have a valid task payload
    When I POST it to the /task endpoint
    Then I receive a 201 status code

  Scenario: failed to create a task
    Given I have an invalid task payload
    When I POST it to the /task endpoint
    Then I receive a 422 status code

  Scenario: get a list of tasks existing
    Given: I have nothing
    When I GET it to the /task endpoint
    Then I receive a 200 status code

  Scenario:get a precise task
    Given: I have a task id
    When I GET it to the /tasks/{task_id} endpoint
    Then I receive a 200 status code