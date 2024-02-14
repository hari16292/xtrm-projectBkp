@Claims @Regression
Feature: Validate Claims API calls

  @GetClaims
  Scenario Outline: Validate GetClaims API call
    Given Valid request for GetClaims API call "<PAT>" "<ClaimStatus>" "<CurrentPage>" "<ItemsPerPage>"
    When "Post" the request to "getClaims" Claims API method
    Then Claims response got success with status code <StatusCode>
    And deserialize the response with desired GetClaims Pojo class

    Examples: Set of request parameters for GetClaims API call
      | TC_No | PAT           | ClaimStatus | CurrentPage | ItemsPerPage | StatusCode |
      | 01    | Global Value  | All         | 1           | 5            | 200        |
      | 02    | Invalid Value | All         | 1           | 5            | 401        |
      | 03    | Empty Value   | All         | 1           | 5            | 401        |

  @GetClaimDetails
  Scenario Outline: Validate GetClaimDetails API call
    Given Valid request for GetClaimDetails API call "<PAT>" "<ClaimId>"
    When "Post" the request to "getClaimDetails" Claims API method
    Then Claims response got success with status code <StatusCode>
    And deserialize the response with desired GetClaimDetails Pojo class

    Examples: Set of request parameters for GetClaimDetails API call
      | TC_No | PAT           | ClaimId    | StatusCode |
      | 01    | Global Value  | Valid Id   | 200        |
      | 02    | Invalid Value | Invalid Id | 401        |
      | 03    | Empty Value   | Invalid Id | 401        |

