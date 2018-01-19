Feature: features for executions

  Background:
    Given there is a Tasks server

  Scenario:get a precise execution
    Given I have a execution id
    When I GET to the /executions/EXECUTION_ID endpoint
    Then I receive a 200 status code

  Scenario: failed to get a precise execution
    Given I have an incorrect execution id
    When I GET to the /executions/EXECUTION_ID endpoint
    Then I receive a 404 status code

  Scenario: create a valid execution
    Given I have a valid task payload
    When I POST to the /execution endpoint
    Then I receive a 201 status code

  Scenario: failed to create a execution
    Given I have an invalid  type payload (not JSON)
    When I POST to the /execution endpoint
    Then I receive a 415 status code

  Scenario: failed to create a execution
    Given I have an JSON execution payload with incorrect parameters
    When I POST to the /execution endpoint
    Then I receive a 422 status code




