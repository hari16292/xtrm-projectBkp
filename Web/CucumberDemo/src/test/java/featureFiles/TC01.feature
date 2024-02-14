Feature: Validate Google maps api

  Scenario: Validate add api
    Given Validate add new location
    When Post the request to "/api/users"
    Then status code should be 200
    But status code should not be 201