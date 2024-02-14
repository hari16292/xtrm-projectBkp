@Send @Regression
Feature: Validate Send API calls

  @FundTransfer
  Scenario Outline: Validate FundTransfer API call with "<Comments>"
    Given Provide FundTransfer valid and invalid request "<PAT>" "<ToAccount>" "<CurrencyCode>" "<ToEmail>"
    When "Post" the request to "fundTransfer" Send API method
    Then Send response got success with status code <StatusCode>
    And deserialize the response with desired FundTransfer pojo class
    And Verify the details in Transaction details

    Examples: Set of request parameters for FundTransfer API call
      | TC_No | PAT           | ToAccount   | CurrencyCode | ToEmail                        | StatusCode | Comments                     |
      | 01    | Global Value  | Valid PAT   | USD          |                                | 200        | Valid request for User       |
      | 02    | Global Value  | Valid SPN   | USD          |                                | 200        | Valid request for Company    |
      | 03    | Global Value  |             | USD          | testapicompany1@mailinator.com | 200        | Valid request for email send |
      | 04    | Global Value  | Invalid PAT | USD          |                                | 400        | Invalid ToAccount            |
      | 05    | Invalid Value | Invalid PAT | USD          |                                | 401        | Invalid PAT                  |
      | 06    | Empty Value   | Invalid PAT | USD          |                                | 401        | Empty PAT                    |

  @SimpleSendSearch
  Scenario Outline: Validate SimpleSendSearch API call "<Comments>"
    Given Provide SimpleSendSearch valid and invalid request "<PAT>" "<SearchKey>"
    When "Post" the request to "simpleSendSearch" Send API method
    Then Send response got success with status code <StatusCode>
    And deserialize the response with desired SimpleSendSearch pojo class

    Examples: Set of request parameters for SimpleSendSearch API call
      | TC_No | PAT           | SearchKey                    | StatusCode | Comments                |
      | 01    | Global Value  | TestAPICompany               | 200        | Valid request for name  |
      | 02    | Invalid Value | TestAPICompany               | 401        | Invalid PAT             |
      | 03    | Empty Value   | TestAPICompany               | 401        | Empty PAT               |
      | 04    | Global Value  | haritestuser1@mailinator.com | 200        | Valid request for email |
      | 05    | Global Value  |                              | 400        | Empty SearchKey         |
      | 06    | Global Value  | ...                          | 400        | Invalid SearchKey       |

  @RecentPayees
  Scenario Outline: Validate RecentPayees API call "<Comments>"
    Given Provide RecentPayees valid and invalid request "<PAT>" "<CurrentPage>" "<ItemsPerPage>"
    When "Post" the request to "recentPayees" Send API method
    Then Send response got success with status code <StatusCode>
      #Added simple send search pojo classes because both has same response body
    And deserialize the response with desired SimpleSendSearch pojo class

    Examples: Set of request parameters for SimpleSendSearch API call
      | TC_No | PAT           | CurrentPage | ItemsPerPage | StatusCode | Comments               |
      | 01    | Global Value  | 1           | 5            | 200        | Valid request          |
      | 02    | Global Value  |             | 5            | 400        | Empty current Page     |
      | 03    | Global Value  | -2          | 5            | 400        | Invalid Current Page   |
      | 04    | Global Value  | 1           |              | 400        | Empty Items Per Page   |
      | 05    | Global Value  | 1           | -6           | 400        | Invalid Items Per Page |
      | 06    | Invalid Value | 1           | 5            | 401        | Invalid PAT            |
      | 07    | Empty Value   | 1           | 5            | 401        | Empty PAT              |

