@General @Regression
Feature: Validate General API calls

  @GetBuyingCurrencyList
  Scenario: Validate GetBuyingCurrencyList API call
    Given Provide GetBuyingCurrencyList valid and invalid request
    When "Post" the request to "getBuyingCurrencyList" General API method
    Then General response got success with status code 200
    And deserialize the response with desired GetBuyingCurrencyList pojo class

  @GetCountryList
  Scenario: Validate GetCountryList API call
    Given Provide GetCountryList valid and invalid request
    When "Post" the request to "getCountryList" General API method
    Then General response got success with status code 200
    And deserialize the response with desired GetCountryList pojo class

  @GetStateList
  Scenario: Validate GetStateList API call
    Given Provide GetStateList valid and invalid request
    When "Post" the request to "getStateList" General API method
    Then General response got success with status code 200
    And deserialize the response with desired GetStateList pojo class

  @GetPaymentMethods
  Scenario: Validate GetPaymentMethods API call
    Given Provide GetPaymentMethods valid and invalid request
    When "Post" the request to "getPaymentMethods" General API method
    Then General response got success with status code 200
    And deserialize the response with desired GetPaymentMethods pojo class

  @GetFee
  Scenario Outline: Validate GetFee API call "<Comments>"
    Given Provide GetFee valid and invalid request "<PAT>" "<ToAccount>" "<Currency>" "<Amount>" "<TransactionType>" "<PaymentMethod>"
    When "Post" the request to "getFee" General API method
    Then General response got success with status code <StatusCode>
    And deserialize the response with desired GetFee pojo class

    Examples: Set of request parameters for GetFee API call
      | TC_No | PAT           | ToAccount   | Currency | Amount | TransactionType | PaymentMethod | StatusCode | Comments                                          |
      | 01    | Global Value  | Valid PAT   | USD      | 10     | Send            |               | 200        | Valid Send request                                |
      | 02    | Invalid Value | Valid PAT   | USD      | 10     | Send            |               | 401        | Invalid PAT                                       |
      | 03    | Empty Value   | Valid PAT   | USD      | 10     | Send            |               | 401        | Empty PAT                                         |
      | 04    | Global Value  | Invalid PAT | USD      | 10     | Send            | XTR94508      | 400        | Invalid To Account                                |
      | 05    | Global Value  | Empty PAT   | USD      | 10     | Send            | XTR94508      | 400        | Empty To Account                                  |
      | 06    | Global Value  | Valid PAT   | $$$      | 10     | Send            |               | 400        | Invalid Currency                                  |
      | 07    | Global Value  | Valid PAT   |          | 10     | Send            |               | 400        | Empty Currency                                    |
      | 08    | Global Value  | Valid PAT   | USD      |        | Send            |               | 400        | Empty Amount                                      |
      | 09    | Global Value  | Valid PAT   | USD      | -1     | Send            |               | 400        | Invalid Currency                                  |
      | 10    | Global Value  | Valid PAT   | USD      | 10     |                 |               | 400        | Empty Transaction Type                            |
      | 11    | Global Value  | Valid PAT   | USD      | 10     | abck7           |               | 400        | Invalid Transaction Type                          |
      | 12    | Global Value  | Valid PAT   | USD      | 10     | Transfer        | XTR94500      | 200        | Valid Bank Transfer request                       |
      | 13    | Global Value  | Valid PAT   | USD      | 10     | Transfer        | XTR94503      | 200        | Valid Prepaid Virtual Debit Card Transfer request |
      | 14    | Global Value  | Valid PAT   | USD      | 10     | Transfer        | XTR94505      | 200        | Valid Digital Gift Card Transfer request          |
      | 15    | Global Value  | Valid PAT   | USD      | 10     | Transfer        | XTR94506      | 200        | Valid Reward Link Transfer request                |
      | 16    | Global Value  | Valid PAT   | USD      | 10     | Transfer        | XTR94508      | 200        | Valid Rapid Transfer request                      |

