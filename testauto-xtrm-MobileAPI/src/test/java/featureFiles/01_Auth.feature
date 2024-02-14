@Authorization @Regression
Feature: Validate oAuth token generation API call

  @Token
  Scenario Outline: Validate access token with "<Comments>"
    Given Validate token user credential with "<ClientID>" and "<ClientSecret>"
    When "Post" the request to "accessToken" Authorization API method
    Then Token response got success with status code <StatusCode>
    And Fetch access token after deserialize response with Pojo class

    Examples: Set of request parameters for Token API call
      | TC_No | ClientID      | ClientSecret  | StatusCode | Comments                              |
      | 01    | Global Value  | Global Value  | 200        | Valid credentials                     |
      | 02    | Empty Value   | Global Value  | 400        | Empty client ID                       |
      | 03    | Global Value  | Empty Value   | 400        | Empty secret key                      |
      | 04    | Empty Value   | Empty Value   | 400        | Empty both                            |
      | 05    | Invalid Value | Global Value  | 400        | Invalid credentials                   |
      | 06    | Invalid Value | Invalid Value | 400        | Valid credentials(for other accounts) |

  @RefreshToken
  Scenario Outline: Validate Refresh token
    Given Validate refresh token user credential with "<RefreshToken>" "<ClientID>" and "<ClientSecret>"
    When "Post" the request to "accessToken" Authorization API method
    Then Token response got success with status code <StatusCode>
    And Fetch access token after deserialize response with Pojo class

    Examples: Set of request parameters for Refresh Token API call
      | TC_No | ClientID     | ClientSecret | RefreshToken | StatusCode | Comments      |
      | 01    | Global Value | Global Value | Valid Token  | 200        | Valid request |