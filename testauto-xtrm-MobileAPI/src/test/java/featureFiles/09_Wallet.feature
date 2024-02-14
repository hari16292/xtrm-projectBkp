@Wallet @Regression
Feature: Validate Wallet API calls

  @GetUserWallets
  Scenario Outline: Validate GetUserWallets API call with PAT - "<PAT>"
    Given Provide GetUserWallets valid and invalid user account number "<PAT>"
    When "Post" the request to "getUserWallets" Wallet API method
    Then Wallet response got success with status code <StatusCode>
    And deserialize the response with desired GetUserWallets pojo class

    Examples: Set of request parameters for GetLinkedCards API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Empty Value   | 401        |
      | 03    | Invalid Value | 401        |

  @GetAvailableCurrencies
  Scenario Outline: Validate GetAvailableCurrencies API call with PAT - "<PAT>"
    Given Provide GetAvailableCurrencies valid and invalid user account number "<PAT>"
    When "Post" the request to "getAvailableCurrencies" Wallet API method
    Then Wallet response got success with status code <StatusCode>
    And deserialize the response with desired GetAvailableCurrencies pojo class
    And verify the total number of currency is 28

    Examples: Set of request parameters for GetLinkedCards API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Empty Value   | 401        |
      | 03    | Invalid Value | 401        |

  @CreateWallet
  Scenario Outline: Validate CreateWallet API call with - "<Comments>" - "<CurrencyCode>"
    Given Provide CreateWallet valid and invalid request parameters "<PAT>" "<CurrencyCode>"
    When "Post" the request to "createWallet" Wallet API method
    Then Wallet response got success with status code <StatusCode>
    And deserialize the response with desired CreateWallet pojo class
    And validate the duplicate wallet creation "<CurrencyCode>"

    Examples: Set of request parameters for CreateWallet API call
      | TC_No | PAT           | CurrencyCode | StatusCode | Comments         |
      | 01    | Global Value  | BHD          | 200        | Valid PAT        |
      | 02    | Invalid Value | USD          | 401        | Invalid PAT      |
      | 03    | Empty Value   | USD          | 401        | Empty PAT        |
      | 04    | Global Value  | 123          | 400        | Invalid Currency |
      | 05    | Global Value  |              | 400        | Empty Currency   |

  @GetUserWalletsTransactions
  Scenario Outline: Validate GetUserWalletsTransactions API call with "<Comments>"
    Given Provide GetUserWalletsTransactions valid and invalid request parameters "<PAT>" "<Currency>" "<SearchParam>"
    When  "Post" the request to "getUserWalletsTransactions" Wallet API method
    Then Wallet response got success with status code <StatusCode>
    And deserialize the response with desired GetUserWalletsTransactions pojo class

    Examples: Set of request parameters for GetUserWalletsTransactions API call
      | TC_No | PAT           | Currency | SearchParam | StatusCode | Comments         |
      | 01    | Global Value  | USD      |             | 200        | Valid data       |
      | 02    | Empty Value   | USD      |             | 401        | Empty PAT        |
      | 03    | Invalid Value | USD      |             | 401        | Invalid PAT      |
      | 04    | Global Value  |          |             | 400        | Empty Currency   |
      | 05    | Global Value  | $$$      |             | 400        | Invalid Currency |
      | 06    | Global Value  | acbhf    |             | 400        | Invalid Currency |

  @GetUserWalletTransactionDetails
  Scenario Outline: Validate GetUserWalletTransactionDetails API call with "<Comments>"
    Given Provide GetUserWalletTransactionDetails valid and invalid request parameters "<PAT>" "<TransactionID>"
    When  "Post" the request to "getUserWalletTransactionDetails" Wallet API method
    Then Wallet response got success with status code <StatusCode>
    And deserialize the response with desired GetUserWalletTransactionDetails pojo class

    Examples: Set of request parameters for GetUserWalletTransactionDetails API call
      | TC_No | PAT           | TransactionID | StatusCode | Comments              |
      | 01    | Global Value  | Valid ID      | 200        | Valid data            |
      | 02    | Invalid Value | Invalid ID    | 401        | Invalid PAT           |
      | 03    | Empty Value   | Invalid ID    | 401        | Empty PAT             |
      | 04    | Global Value  | Invalid ID    | 400        | Invalid TransactionID |
      | 05    | Global Value  | Empty ID      | 400        | Empty TransactionID   |

  @UpdateWallet
  Scenario Outline: Validate UpdateWallet API call
    Given Provide UpdateWallet valid and invalid request parameters "<PAT>" "<WalletID>" "<WalletName>"
    When  "Post" the request to "updateWallet" Wallet API method
    Then Wallet response got success with status code <StatusCode>
    And deserialize the response with desired UpdateWallet pojo class

    Examples: Set of request parameters for GetUserWalletTransactionDetails API call
      | TC_No | PAT           | WalletID | WalletName        | StatusCode |
      | 01    | Global Value  | 86340    | USD Wallet        | 200        |
      | 02    | Global Value  | 86340    | USD Wallet Update | 200        |
      | 03    | Invalid Value | 86340    | USD Wallet        | 401        |
      | 04    | Empty Value   | 86340    | USD Wallet        | 401        |
      | 05    | Global Value  |          | USD Wallet        | 400        |
      | 06    | Global Value  | qwet     | USD Wallet        | 400        |
      | 07    | Global Value  | 86340    |                   | 400        |