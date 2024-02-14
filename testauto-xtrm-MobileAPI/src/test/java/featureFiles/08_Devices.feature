@Devices @Regression
Feature: Validate Devices API calls

  @GetDevices
  Scenario Outline: Validate GetDevices API call
    Given Provide GetDevices valid and invalid request "<PAT>"
    When "Post" the request to "getDevices" Devices API method
    Then Devices response got success with status code <StatusCode>
    And deserialize the response with desired GetDevices pojo class

    Examples: Set of request parameters for GetDevices API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |
