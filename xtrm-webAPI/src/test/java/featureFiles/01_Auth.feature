@Authorization @Regression
Feature: Validate oAuth token generation API call

  @token
  Scenario Outline: Validate access token with "<Comments>"
    Given Validate user credential with "<Client_ID>" and "<Client_Secret>"
    When "Post" the request to "accessToken" token API method
    Then Token response got success with status code <Status_Code>
    And Fetch access token after deserialize response with Pojo class

    Examples:
      | Client_ID     | Client_Secret | Status_Code | Comments                              |
      | Global Value  | Global Value  | 200         | Valid credentials                     |
      | Empty Value   | Global Value  | 400         | Empty client ID                       |
      | Global Value  | Empty Value   | 400         | Empty secret key                      |
      | Empty Value   | Empty Value   | 400         | Empty both                            |
      | Invalid Value | Global Value  | 400         | Invalid credentials                   |
      | Invalid Value | Invalid Value | 400         | Valid credentials(for other accounts) |