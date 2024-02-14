@Transfer @Regression
Feature: Validate Transfer API calls

  @UserWithdrawFund @UserWithdrawFund-XTR94500
  Scenario Outline: Validate UserWithdrawFund-XTR94500 API call - "<Comments>"
    Given Provide valid and invalid request parameters for Transfer API "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    When "Post" the request to "userWithdrawFund" Transfer API method
    Then deserialize the response with desired UserWithdrawFund Pojo class
    And Get OTP value from mailinator
    And Proceed transfer fund request above all steps with OTP "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    And Transfer response got success with status code <StatusCode>
    And Verify the amount in Transaction Details API "<Amount>" "<PaymentMethod>" "<ToCurrency>"

    Examples: Set of request parameters for UserWithdrawFund-XTR94500 API call
      | TC_No | Comments                              | PAT           | Amount | FromCurrency | ToCurrency | PaymentMethod | GiftCardEmail | RewardLinkEmail | PrepaidCardEmail | LinkBankID | CardToken | SKU | StatusCode |
      | 01    | Valid data for Bank Transfer          | Global Value  | 10.00  | USD          | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 200        |
      | 02    | Invalid PAT - Bank Transfer           | Invalid Value | 5.00   | USD          | USD        | XTR94500      |               |                 |                  | Invalid ID |           |     | 401        |
      | 03    | Empty PAT - Bank Transfer             | Empty Value   | 5.00   | USD          | USD        | XTR94500      |               |                 |                  | Invalid ID |           |     | 401        |
      | 04    | Empty Amount - Bank Transfer          | Global Value  |        | USD          | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 05    | Zero Amount - Bank Transfer           | Global Value  | 0      | USD          | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 06    | Invalid Amount - Bank Transfer        | Global Value  | hdg    | USD          | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 07    | Negative Amount - Bank Transfer       | Global Value  | -50    | USD          | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 08    | Empty FromCurrency - Bank Transfer    | Global Value  | 5.00   |              | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 09    | Invalid FromCurrency - Bank Transfer  | Global Value  | 5.00   | $            | USD        | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 10    | Empty ToCurrency - Bank Transfer      | Global Value  | 5.00   | USD          |            | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 11    | Invalid ToCurrency - Bank Transfer    | Global Value  | 5.00   | USD          | ₹          | XTR94500      |               |                 |                  | Valid ID   |           |     | 400        |
      | 12    | Empty LinkBankID - Bank Transfer      | Global Value  | 5.00   | USD          | USD        | XTR94500      |               |                 |                  | Empty ID   |           |     | 400        |
      | 13    | Invalid LinkBankID - Bank Transfer    | Global Value  | 5.00   | USD          | USD        | XTR94500      |               |                 |                  | Invalid ID |           |     | 400        |
      | 14    | Invalid PaymentMethod - Bank Transfer | Global Value  | 5.00   | USD          | USD        | XTR94502      |               |                 |                  | Valid ID   |           |     | 400        |
      | 15    | Invalid PaymentMethod - Bank Transfer | Global Value  | 5.00   | USD          | USD        | XTR94506      |               |                 |                  | Valid ID   |           |     | 400        |
      | 16    | Empty PaymentMethod - Bank Transfer   | Global Value  | 5.00   | USD          | USD        |               |               |                 |                  | Valid ID   |           |     | 400        |

  @UserWithdrawFund @UserWithdrawFund-XTR94503
  Scenario Outline: Validate UserWithdrawFund-XTR94503 API call - "<Comments>"
    Given Provide valid and invalid request parameters for Transfer API "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    When "Post" the request to "userWithdrawFund" Transfer API method
    Then deserialize the response with desired UserWithdrawFund Pojo class
    And Get OTP value from mailinator
    And Proceed transfer fund request above all steps with OTP "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    And Transfer response got success with status code <StatusCode>
    And Verify the amount in Transaction Details API "<Amount>" "<PaymentMethod>" "<ToCurrency>"

    Examples: Set of request parameters for UserWithdrawFund-XTR94503 API call
      | TC_No | Comments                                     | PAT           | Amount | FromCurrency | ToCurrency | PaymentMethod | GiftCardEmail | RewardLinkEmail | PrepaidCardEmail | LinkBankID | CardToken | SKU                     | StatusCode |
      | 01    | Valid data for Prepaid Virtual Card          | Global Value  | 10.00  | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 200        |
      | 02    | Invalid PAT - Prepaid Virtual Card           | Invalid Value | 5.00   | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 401        |
      | 03    | Empty PAT - Prepaid Virtual Card             | Empty Value   | 5.00   | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 401        |
      | 04    | Empty Amount - Prepaid Virtual Card          | Global Value  |        | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 05    | Zero Amount - Prepaid Virtual Card           | Global Value  | 0      | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 06    | Invalid Amount - Prepaid Virtual Card        | Global Value  | hdg    | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 07    | Negative Amount - Prepaid Virtual Card       | Global Value  | -50    | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 08    | Empty FromCurrency - Prepaid Virtual Card    | Global Value  | 5.00   |              | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 09    | Invalid FromCurrency - Prepaid Virtual Card  | Global Value  | 5.00   | $            | USD        | XTR94503      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 10    | Empty ToCurrency - Prepaid Virtual Card      | Global Value  | 5.00   | USD          |            | XTR94503      |               |                 | Valid Email      |            |           | Empty PrepaidCard SKU   | 400        |
      | 11    | Invalid ToCurrency - Prepaid Virtual Card    | Global Value  | 5.00   | USD          | ₹          | XTR94503      |               |                 | Valid Email      |            |           | Empty PrepaidCard SKU   | 400        |
      | 12    | Empty Email -  Prepaid Virtual Card          | Global Value  | 5.00   | USD          | USD        | XTR94503      |               |                 | Empty Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 13    | Invalid Email - Prepaid Virtual Card         | Global Value  | 5.00   | USD          | USD        | XTR94503      |               |                 | Invalid Email    |            |           | Valid PrepaidCard SKU   | 400        |
      | 14    | Empty SKU - Prepaid Virtual Card             | Global Value  | 5.00   | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Empty PrepaidCard SKU   | 400        |
      | 15    | Invalid SKU - Prepaid Virtual Card           | Global Value  | 5.00   | USD          | USD        | XTR94503      |               |                 | Valid Email      |            |           | Invalid PrepaidCard SKU | 400        |
      | 16    | Invalid PaymentMethod - Prepaid Virtual Card | Global Value  | 5.00   | USD          | USD        | XTR94506      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 17    | Invalid PaymentMethod - Prepaid Virtual Card | Global Value  | 5.00   | USD          | USD        | XTR94508      |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |
      | 18    | Empty PaymentMethod - Prepaid Virtual Card   | Global Value  | 5.00   | USD          | USD        |               |               |                 | Valid Email      |            |           | Valid PrepaidCard SKU   | 400        |

  @UserWithdrawFund @UserWithdrawFund-XTR94505
  Scenario Outline: Validate UserWithdrawFund-XTR94505 API call - "<Comments>"
    Given Provide valid and invalid request parameters for Transfer API "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    When "Post" the request to "userWithdrawFund" Transfer API method
    Then deserialize the response with desired UserWithdrawFund Pojo class
    And Get OTP value from mailinator
    And Proceed transfer fund request above all steps with OTP "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    And Transfer response got success with status code <StatusCode>
    And Verify the amount in Transaction Details API "<Amount>" "<PaymentMethod>" "<ToCurrency>"

    Examples: Set of request parameters for UserWithdrawFund-XTR94505 API call
      | TC_No | Comments                                  | PAT           | Amount | FromCurrency | ToCurrency | PaymentMethod | GiftCardEmail | RewardLinkEmail | PrepaidCardEmail | LinkBankID | CardToken | SKU                  | StatusCode |
      | 01    | Valid data for Digital Gift Card          | Global Value  | 10.00  | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 200        |
      | 02    | Invalid PAT - Digital Gift Card           | Invalid Value | 5.00   | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 401        |
      | 03    | Empty PAT - Digital Gift Card             | Empty Value   | 5.00   | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 401        |
      | 04    | Empty Amount - Digital Gift Card          | Global Value  |        | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 05    | Zero Amount - Digital Gift Card           | Global Value  | 0      | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 06    | Invalid Amount - Digital Gift Card        | Global Value  | hdg    | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 07    | Negative Amount - Digital Gift Card       | Global Value  | -50    | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 08    | Empty FromCurrency - Digital Gift Card    | Global Value  | 5.00   |              | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 09    | Invalid FromCurrency - Digital Gift Card  | Global Value  | 5.00   | $            | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 10    | Empty ToCurrency - Digital Gift Card      | Global Value  | 5.00   | USD          |            | XTR94505      | Valid Email   |                 |                  |            |           | Empty GiftCard SKU   | 400        |
      | 11    | Invalid ToCurrency - Digital Gift Card    | Global Value  | 5.00   | USD          | ₹          | XTR94505      | Valid Email   |                 |                  |            |           | Empty GiftCard SKU   | 400        |
      | 12    | Empty Email - Digital Gift Card           | Global Value  | 5.00   | USD          | USD        | XTR94505      | Empty Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 13    | Invalid Email - Digital Gift Card         | Global Value  | 5.00   | USD          | USD        | XTR94505      | Invalid Email |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 14    | Empty SKU - Digital Gift Card             | Global Value  | 5.00   | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Empty GiftCard SKU   | 400        |
      | 15    | Invalid SKU - Digital Gift Card           | Global Value  | 5.00   | USD          | USD        | XTR94505      | Valid Email   |                 |                  |            |           | Invalid GiftCard SKU | 400        |
      | 16    | Invalid PaymentMethod - Digital Gift Card | Global Value  | 8.00   | USD          | USD        | 055AB         | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 17    | Invalid PaymentMethod - Digital Gift Card | Global Value  | 8.00   | USD          | USD        | XTR94508      | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |
      | 18    | Empty PaymentMethod - Digital Gift Card   | Global Value  | 8.00   | USD          | USD        |               | Valid Email   |                 |                  |            |           | Valid GiftCard SKU   | 400        |

  @UserWithdrawFund @UserWithdrawFund-XTR94506
  Scenario Outline: Validate UserWithdrawFund-XTR94506 API call - "<Comments>"
    Given Provide valid and invalid request parameters for Transfer API "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    When "Post" the request to "userWithdrawFund" Transfer API method
    Then deserialize the response with desired UserWithdrawFund Pojo class
    And Get OTP value from mailinator
    And Proceed transfer fund request above all steps with OTP "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    And Transfer response got success with status code <StatusCode>
    And Verify the amount in Transaction Details API "<Amount>" "<PaymentMethod>" "<ToCurrency>"

    Examples: Set of request parameters for UserWithdrawFund-XTR94506 API call
      | TC_No | Comments                                 | PAT           | Amount | FromCurrency | ToCurrency | PaymentMethod | GiftCardEmail | RewardLinkEmail | PrepaidCardEmail | LinkBankID | CardToken | SKU                    | StatusCode |
      | 01    | Valid data for Reward Link Card          | Global Value  | 5.00   | USD          | GBP        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 200        |
      | 02    | Invalid PAT - Reward Link Card           | Invalid Value | 5.00   | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 401        |
      | 03    | Empty PAT - Reward Link Card             | Empty Value   | 5.00   | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 401        |
      | 04    | Empty Amount - Reward Link Card          | Global Value  |        | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 05    | Zero Amount - Reward Link Card           | Global Value  | 0      | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 06    | Invalid Amount - Reward Link Card        | Global Value  | hdg    | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 07    | Negative Amount - Reward Link Card       | Global Value  | -50    | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 08    | Empty FromCurrency - Reward Link Card    | Global Value  | 5.00   |              | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 09    | Invalid FromCurrency - Reward Link Card  | Global Value  | 5.00   | $            | USD        | XTR94506      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 10    | Empty ToCurrency - Reward Link Card      | Global Value  | 5.00   | USD          |            | XTR94506      |               | Valid Email     |                  |            |           | Empty RewardLink SKU   | 400        |
      | 11    | Invalid ToCurrency - Reward Link Card    | Global Value  | 5.00   | USD          | ₹          | XTR94506      |               | Valid Email     |                  |            |           | Empty RewardLink SKU   | 400        |
      | 12    | Empty Email - Reward Link Card           | Global Value  | 5.00   | USD          | USD        | XTR94506      |               | Empty Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 13    | Invalid Email - Reward Link Card         | Global Value  | 5.00   | USD          | USD        | XTR94506      |               | Invalid Email   |                  |            |           | Valid RewardLink SKU   | 400        |
      | 14    | Empty SKU - Reward Link Card             | Global Value  | 5.00   | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Empty RewardLink SKU   | 400        |
      | 15    | Invalid SKU - Reward Link Card           | Global Value  | 5.00   | USD          | USD        | XTR94506      |               | Valid Email     |                  |            |           | Invalid RewardLink SKU | 400        |
      | 16    | Invalid PaymentMethod - Reward Link Card | Global Value  | 7.00   | USD          | USD        | XTR94500      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 17    | Invalid PaymentMethod - Reward Link Card | Global Value  | 7.00   | USD          | USD        | XTR94503      |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |
      | 18    | Empty PaymentMethod - Reward Link Card   | Global Value  | 7.00   | USD          | USD        |               |               | Valid Email     |                  |            |           | Valid RewardLink SKU   | 400        |

  @UserWithdrawFund @UserWithdrawFund-XTR94508
  Scenario Outline: Validate UserWithdrawFund-XTR94508 API call - "<Comments>"
    Given Provide valid and invalid request parameters for Transfer API "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    When "Post" the request to "userWithdrawFund" Transfer API method
    Then deserialize the response with desired UserWithdrawFund Pojo class
    And Get OTP value from mailinator
    And Proceed transfer fund request above all steps with OTP "<PAT>" "<Amount>" "<FromCurrency>" "<ToCurrency>" "<PaymentMethod>" "<GiftCardEmail>" "<RewardLinkEmail>" "<PrepaidCardEmail>" "<LinkBankID>" "<CardToken>" "<SKU>"
    And Transfer response got success with status code <StatusCode>
    And Verify the amount in Transaction Details API "<Amount>" "<PaymentMethod>" "<ToCurrency>"

    Examples: Set of request parameters for UserWithdrawFund-XTR94508 API call
      | TC_No | Comments                              | PAT           | Amount | FromCurrency | ToCurrency | PaymentMethod | GiftCardEmail | RewardLinkEmail | PrepaidCardEmail | LinkBankID | CardToken     | SKU | StatusCode |
      | 01    | Valid data for Rapid Transfer         | Global Value  | 10.00  | USD          | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 200        |
      | 02    | Invalid PAT - Rapid Transfer          | Invalid Value | 5.00   | USD          | USD        | XTR94508      |               |                 |                  |            | Invalid Token |     | 401        |
      | 03    | Empty PAT - Rapid Transfer            | Empty Value   | 5.00   | USD          | USD        | XTR94508      |               |                 |                  |            | Invalid Token |     | 401        |
      | 04    | Empty Amount - Rapid Transfer         | Global Value  |        | USD          | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 05    | Zero Amount - Rapid Transfer          | Global Value  | 0      | USD          | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 06    | Invalid Amount - Rapid Transfer       | Global Value  | hdg    | USD          | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 07    | Negative Amount - Rapid Transfer      | Global Value  | -50    | USD          | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 08    | Empty FromCurrency - Rapid Transfer   | Global Value  | 5.00   |              | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 09    | Invalid FromCurrency - Rapid Transfer | Global Value  | 5.00   | $            | USD        | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 10    | Empty ToCurrency - Rapid Transfer     | Global Value  | 5.00   | USD          |            | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 11    | Invalid ToCurrency - Rapid Transfer   | Global Value  | 5.00   | USD          | ₹          | XTR94508      |               |                 |                  |            | Valid Token   |     | 400        |
      | 12    | Empty CardToken - Rapid Transfer      | Global Value  | 5.00   | USD          | USD        | XTR94508      |               |                 |                  |            | Empty Token   |     | 400        |
      | 13    | Invalid CardToken - Rapid Transfer    | Global Value  | 5.00   | USD          | USD        | XTR94508      |               |                 |                  |            | Invalid Token |     | 400        |
      | 14    | Invalid PaymentMethod - Bank Transfer | Global Value  | 1.25   | USD          | USD        | XTR94500      |               |                 |                  |            | Valid Token   |     | 400        |
      | 15    | Invalid PaymentMethod - Bank Transfer | Global Value  | 1.25   | USD          | USD        | XTR94504      |               |                 |                  |            | Valid Token   |     | 400        |
      | 16    | Empty PaymentMethod - Bank Transfer   | Global Value  | 1.25   | USD          | USD        |               |               |                 |                  |            | Valid Token   |     | 400        |

  @GetUserPaymentMethods
  Scenario: Validate GetUserPaymentMethods API call
    Given Valid request for GetUserPaymentMethods API call
    When "Post" the request to "getUserPaymentMethods" Transfer API method
    Then Transfer response got success with status code 200
    And deserialize the response with desired GetUserPaymentMethods Pojo class

  @GetPrepaidDebitCardAvailableCurrencies
  Scenario: Validate GetPrepaidDebitCardAvailableCurrencies API call
    Given Valid request for GetUserPaymentMethods API call
    When "Post" the request to "getPrepaidDebitCardAvailableCurrencies" Transfer API method
    Then Transfer response got success with status code 200
    And deserialize the response with desired GetPrepaidDebitCardAvailableCurrencies Pojo class

  @GetDigitalGiftCardAvailableCurrencies
  Scenario: Validate GetDigitalGiftCardAvailableCurrencies API call
    Given Valid request for GetUserPaymentMethods API call
    When "Post" the request to "getDigitalGiftCardAvailableCurrencies" Transfer API method
    Then Transfer response got success with status code 200
    And deserialize the response with desired GetPrepaidDebitCardAvailableCurrencies Pojo class