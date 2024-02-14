@LinkOthers @Regression
Feature: Validate Link Others API calls

  @GetDigitalGiftCards
  Scenario Outline: Validate GetDigitalGiftCards API with - "<Comments>" - "<CurrencyCode>"
    Given Provide valid and invalid currency code in request parameter "<CurrencyCode>"
    When "Post" the request to "getDigitalGiftCards" Link Others API method
    Then Link Others response got success with status code <StatusCode>
    And deserialize the response with desired GetGiftCards Pojo class

    Examples: Set of request parameters for GetDigitalGiftCards API call
      | TC_No | CurrencyCode | StatusCode | Comments         |
      | 01    | USD          | 200        | Valid Currency   |
      | 02    | EUR          | 200        | Valid Currency   |
      | 03    | INR          | 200        | Valid Currency   |
      | 04    | ABC          | 400        | Invalid Currency |
      | 05    |              | 400        | Empty Currency   |
      | 06    | $            | 400        | Invalid Currency |
      | 07    | usd123       | 400        | Invalid Currency |

  @GetPrepaidGiftCards
  Scenario Outline:  Validate GetPrepaidGiftCards API with - "<Comments>" - "<CurrencyCode>"
    Given Provide valid and invalid currency code in request parameter "<CurrencyCode>"
    When "Post" the request to "getPrepaidGiftCards" Link Others API method
    Then Link Others response got success with status code <StatusCode>
    And deserialize the response with desired GetGiftCards Pojo class

    Examples: Set of request parameters for GetPrepaidGiftCards API call
      | TC_No | CurrencyCode | StatusCode | Comments         |
      | 01    | USD          | 200        | Valid Currency   |
      | 02    | EUR          | 200        | Valid Currency   |
      | 03    | INR          | 200        | Valid Currency   |
      | 04    | ABC          | 400        | Invalid Currency |
      | 05    |              | 400        | Empty Currency   |
      | 06    | $            | 400        | Invalid Currency |
      | 07    | usd123       | 400        | Invalid Currency |

  @GetRewardGiftCards
  Scenario Outline: Validate GetRewardGiftCards API with - "<Comments>" - "<CurrencyCode>"
    Given Provide valid and invalid currency code in request parameter "<CurrencyCode>"
    When "Post" the request to "getRewardGiftCards" Link Others API method
    Then Link Others response got success with status code <StatusCode>
    And deserialize the response with desired GetGiftCards Pojo class

    Examples: Set of request parameters for GetPrepaidGiftCards API call
      | TC_No | CurrencyCode | StatusCode | Comments         |
      | 01    | USD          | 200        | Valid Currency   |
      | 02    | EUR          | 200        | Valid Currency   |
      | 03    | INR          | 200        | Valid Currency   |
      | 04    | ABC          | 400        | Invalid Currency |
      | 05    |              | 400        | Empty Currency   |
      | 06    | $            | 400        | Invalid Currency |
      | 07    | usd123       | 400        | Invalid Currency |

  @GetOtherLinkAccounts
  Scenario Outline: Validate GetOtherLinkAccounts API with "<PAT>"
    Given Provide GetOtherLinkAccounts valid and invalid user account number "<PAT>"
    When "Post" the request to "getOtherLinkAccounts" Link Others API method
    Then Link Others response got success with status code <StatusCode>
    And deserialize the response with desired GetOtherLinkAccounts Pojo class

    Examples: Set of request parameters for GetOtherLinkAccounts API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |
