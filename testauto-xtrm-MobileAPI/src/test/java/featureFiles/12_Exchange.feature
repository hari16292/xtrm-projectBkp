@Exchange @Regression
Feature: Validate Exchange API calls

  @GetExchangeRate
  Scenario Outline: Validate GetExchangeRate API call "<Comments>"
    Given Provide valid and invalid request parameters "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>"
    When "Post" the request to "getExchangeRate" GetExchangeRate API method
    Then Exchange response got success with status code <StatusCode>
    And deserialize the response with desired GetExchangeRate Pojo class

    Examples: Set of request parameters for GetExchangeRate API call
      | TC_No | PAT           | Amount | FromCurrency | ToCurrency | StatusCode | Comments                       |
      | 01    | Global Value  | 10     | USD          | SGD        | 200        | Valid request data             |
      | 02    | Invalid Value | 10     | USD          | AED        | 401        | Invalid PAT                    |
      | 03    | Empty Value   | 15     | USD          | AUD        | 401        | Empty PAT                      |
      | 04    | Global Value  |        | USD          | MYR        | 400        | Empty Amount                   |
      | 05    | Global Value  | 0      | USD          | SGD        | 400        | Invalid Amount                 |
      | 06    | Global Value  | 25     |              | USD        | 400        | Empty From Currency Code       |
      | 07    | Global Value  | 15     | 123          | USD        | 400        | Invalid From Currency Code     |
      | 08    | Global Value  | 10     | USD          |            | 400        | Empty To Currency Code         |
      | 09    | Global Value  | 05     | USD          | 025        | 400        | Invalid To Currency Code       |
      | 10    | Global Value  | 10     | USD          | USD        | 400        | Same From and To Currency Code |

  @GetSellingExchangeRate
  Scenario Outline: Validate GetSellingExchangeRate API call "<Comments>"
    Given Provide valid and invalid request parameters "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>"
    When "Post" the request to "getSellingExchangeRate" GetExchangeRate API method
    Then Exchange response got success with status code <StatusCode>
    And deserialize the response with desired GetExchangeRate Pojo class

    Examples: Set of request parameters for GetSellingExchangeRate API call
      | TC_No | PAT           | Amount | FromCurrency | ToCurrency | StatusCode | Comments                       |
      | 01    | Global Value  | 10     | USD          | SGD        | 200        | Valid request data             |
      | 02    | Invalid Value | 10     | USD          | AED        | 401        | Invalid PAT                    |
      | 03    | Empty Value   | 15     | USD          | AUD        | 401        | Empty PAT                      |
      | 04    | Global Value  |        | USD          | MYR        | 400        | Empty Amount                   |
      | 05    | Global Value  | 0      | USD          | SGD        | 400        | Invalid Amount                 |
      | 06    | Global Value  | 25     |              | USD        | 400        | Empty From Currency Code       |
      | 07    | Global Value  | 15     | 123          | USD        | 400        | Invalid From Currency Code     |
      | 08    | Global Value  | 10     | USD          |            | 400        | Empty To Currency Code         |
      | 09    | Global Value  | 05     | USD          | 025        | 400        | Invalid To Currency Code       |
      | 10    | Global Value  | 10     | USD          | USD        | 400        | Same From and To Currency Code |

  @BookCurrencyExchange
  Scenario Outline: Validate BookCurrencyExchange API call "<Comments>"
    Given Provide valid and invalid request parameters "<PAT>" "<Amount>" "<ExchangeRate>" "<FromCurrency>" "<ToCurrency>"
    When "Post" the request to "bookCurrencyExchange" GetExchangeRate API method
    Then Exchange response got success with status code <StatusCode>
    And deserialize the response with desired BookCurrencyExchange Pojo class

    Examples: Set of request parameters for BookCurrencyExchange API call
      | TC_No | PAT           | Amount | ExchangeRate     | FromCurrency | ToCurrency | StatusCode | Comments                       |
      | 01    | Global Value  | 10     | Valid Exchange   | USD          | SGD        | 200        | Valid request data             |
      | 02    | Invalid Value | 10     | Invalid Exchange | USD          | AED        | 401        | Invalid PAT                    |
      | 03    | Empty Value   | 15     | Invalid Exchange | USD          | AUD        | 401        | Empty PAT                      |
      | 04    | Global Value  |        | Invalid Exchange | USD          | MYR        | 400        | Empty Amount                   |
      | 05    | Global Value  | 0      | Invalid Exchange | USD          | SGD        | 400        | Invalid Amount                 |
      | 06    | Global Value  | 25     | Invalid Exchange |              | USD        | 400        | Empty From Currency Code       |
      | 07    | Global Value  | 15     | Invalid Exchange | 123          | USD        | 400        | Invalid From Currency Code     |
      | 08    | Global Value  | 10     | Invalid Exchange | USD          |            | 400        | Empty To Currency Code         |
      | 09    | Global Value  | 05     | Invalid Exchange | USD          | 025        | 400        | Invalid To Currency Code       |
      | 10    | Global Value  | 10     | Invalid Exchange | USD          | USD        | 400        | Same From and To Currency Code |
      | 11    | Global Value  | 10     | Invalid Exchange | USD          | AED        | 400        | Invalid Exchange Rate          |
      | 12    | Global Value  | 10     | Empty Exchange   | USD          | AED        | 400        | Empty Exchange Rate            |