@Bank @Regression
Feature: Validate Bank API calls

  @CreateBankBeneficiary
  Scenario Outline: Validate CreateBankBeneficiary API call with "<Comments>"
    Given Provide valid and invalid data set in request parameter "<PAT>" "<beneficiary_contact_name>" "<beneficiary_phone_number>" "<address_line1>" "<address_line2>" "<city>" "<region>" "<postalcode>" "<beneficiary_information_country_ISO2>" "<institution_name>" "<currency>" "<SWIFTBIC>" "<beneficiary_account_number>" "<routing_number>" "<remittance_line3>" "<remittance_line4>" "<country_ISO2>" <StatusCode>
    When "Post" the request to "createBankBeneficiary" Bank API method
    Then Bank response got success with status code <StatusCode>
    And deserialize the response with desired CreateBankBeneficiary Pojo class

    Examples: Set of request parameters for CreateBankBeneficiary API call
      | TC_No | PAT           | beneficiary_contact_name | beneficiary_phone_number | address_line1 | address_line2 | city    | region | postalcode | beneficiary_information_country_ISO2 | institution_name                            | currency | SWIFTBIC    | beneficiary_account_number    | routing_number | remittance_line3 | remittance_line4 | country_ISO2 | StatusCode | Comments                  |
      | 01    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      |             | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 200        | Valid request body for US |
      | 02    | Global Value  |                          | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Beneficiary Name    |
      | 03    | Global Value  | .                        | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Invalid Beneficiary Name  |
      | 04    | Global Value  | Hari Test                |                          |               | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Phone Number        |
      | 05    | Global Value  | Hari Test                | 778899554420             |               | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Address1            |
      | 06    | Global Value  | Hari Test                | 778899554420             | Addr1         |               | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 200        | Empty Address2            |
      | 07    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         |         | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty City                |
      | 08    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida |        | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Region              |
      | 09    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     |            | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Postal Code         |
      | 10    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     |                                      | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Country             |
      | 11    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   |                                             | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Bank Name           |
      | 12    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 |          | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Currency            |
      | 13    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      |             | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 200        | Empty Swift code          |
      | 14    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX |                               | 121000248      | Test Line 3      | Test Line 4      | US           | 400        | Empty Account number      |
      | 15    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | IN                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      |                | Test Line 3      | Test Line 4      | US           | 200        | Empty Routing code        |
      | 16    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      |                  | Test Line 4      | US           | 200        | Empty Remittance 3        |
      | 17    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      |                  | US           | 200        | Empty Remittance 4        |
      | 18    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      |              | 400        | Empty Country ISO2        |
      | 19    | Global Value  |                          |                          |               |               |         |        |            |                                      |                                             |          |             |                               |                |                  |                  |              | 400        | Empty request body        |
      | 20    | Invalid Value | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Wells Fargo                                 | USD      | WFBIUS6SXXX | 11223344                      | 121000248      | Test Line 3      | Test Line 4      | US           | 401        | Invalid PAT               |
      | 21    | Empty Value   |                          |                          |               |               |         |        |            |                                      |                                             |          |             |                               |                |                  |                  |              | 401        | Empty request body & PAT  |
      | 22    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | BANCO DO BRASIL S.A.                        | BRL      |             | BR9700000000018980000118214C1 |                | Test Line 3      | Test Line 4      | BR           | 200        | New Corpay valid bank BR  |
      | 23    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | HSBC Bank Plc                               | GBP      |             | 98987319                      | 404761         | Test Line 3      | Test Line 4      | GB           | 200        | New Corpay valid bank GB  |
      | 24    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Bank of America Corporation                 | USD      |             | 000074057720                  | 026009593      | Test Line 3      | Test Line 4      | US           | 200        | New Corpay valid bank US  |
      | 25    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Intesa San Paolo                            | EUR      | BCITITMMXXX | IT77V0306949 110100 000066535 |                | Test Line 3      | Test Line 4      | IT           | 200        | New Corpay valid bank IT  |
      | 26    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Anz New Zealand Investments Limited         | NZD      | ANZBNZ22XXX | 10853005085100                | 010853         | Test Line 3      | Test Line 4      | NZ           | 200        | New Corpay valid bank NZ  |
      | 27    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Industrial Bank of Korea                    | KRW      |             | 06603657501021                |                | Test Line 3      | Test Line 4      | KR           | 200        | New Corpay valid bank KR  |
      | 28    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Banco Santander Rio S.A.                    | ARS      |             | 41300001607                   |                | Test Line 3      | Test Line 4      | AR           | 200        | New Corpay valid bank AR  |
      | 29    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Canadian Imperial Bank of Commerce          | CAD      |             | 6354386                       | 001005702      | Test Line 3      | Test Line 4      | CA           | 200        | New Corpay valid bank CA  |
      | 30    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | EMIRATES NBD PJSC                           | AED      | EBILAEADXXX | AE100260001014663799401       |                | Test Line 3      | Test Line 4      | AE           | 200        | New Corpay valid bank AE  |
      | 31    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | Standard Chartered Bank (Hong Kong) Limited | HKD      | SCBLHKHHXXX | 32989730281                   | 3329           | Test Line 3      | Test Line 4      | HK           | 200        | New Corpay valid bank HK  |
      | 32    | Global Value  | Hari Test                | 778899554420             | Addr1         | Addr2         | Florida | WA     | 879456     | US                                   | HDFC Bank Ltd                               | INR      |             | 4545454554                    | HDFC0000751    | Test Line 3      | Test Line 4      | IN           | 200        | New Corpay valid bank IN  |


  @GetLinkedBankAccounts
  Scenario Outline: Validate GetLinkedBankAccounts API call with PAT - "<PAT>"
    Given Provide valid and invalid user account number "<PAT>" in request parameters
    When "Post" the request to "getLinkedBankAccounts" Bank API method
    Then Bank response got success with status code <StatusCode>
    And deserialize the response with desired GetLinkedBankAccounts Pojo class

    Examples: Set of request parameters for GetLinkedBankAccounts API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |

  @GetBeneficiaryDetails
  Scenario Outline: Validate GetBeneficiaryDetails API call with "<BeneficiaryID>"
    Given Provide valid and invalid "<BeneficiaryID>" in request parameter "<PAT>"
    When "Post" the request to "getBeneficiaryDetails" Bank API method
    Then Bank response got success with status code <StatusCode>
    And deserialize the response with desired GetBeneficiaryDetails Pojo class
    And Fetch "<Message>" value from failed response

    Examples: Set of request parameters for GetBeneficiaryDetails API call
      | TC_No | BeneficiaryID | StatusCode | Message                      | PAT           |
      | 01    | Valid ID      | 200        |                              | Global Value  |
      | 02    | Empty ID      | 400        |                              | Global Value  |
      | 03    | Invalid ID    | 400        | Beneficiary ID is not found. | Global Value  |
      | 04    | Empty ID      | 401        |                              | Invalid Value |
      | 05    | Invalid ID    | 401        |                              | Invalid Value |
      | 06    | Empty ID      | 401        |                              | Empty Value   |
      | 07    | Invalid ID    | 401        |                              | Empty Value   |

  @DeleteBankBeneficiary
  Scenario Outline: Validate DeleteBankBeneficiary API call with "<Comments>"
    Given Provide valid and invalid data in DeleteBankBeneficiary "<PAT>" "<BeneficiaryID>"
    When "Post" the request to "deleteBankBeneficiary" Bank API method
    Then Bank response got success with status code <StatusCode>
    And deserialize the response with desired DeleteBankBeneficiary Pojo class

    Examples: Set of request parameters for DeleteBankBeneficiary API call
      | TC_No | PAT           | BeneficiaryID                    | StatusCode | Comments               |
      | 01    | Global Value  | Fetch from GetLinkedBankAccounts | 200        | Valid request body     |
      | 02    | Global Value  | Invalid ID                       | 400        | Invalid Beneficiary ID |
      | 03    | Global Value  | Empty ID                         | 400        | Empty Beneficiary ID   |
      | 04    | Invalid Value | Empty ID                         | 401        | Invalid request        |
      | 05    | Empty Value   | Invalid ID                       | 401        | Invalid request        |

  @SearchBank
  Scenario Outline: Validate SearchBank API call with "<Comments>"
    Given Provide valid and invalid data set in request parameter "<BankName>" "<CountryCode>"
    When "Post" the request to "searchBank" Bank API method
    Then Bank response got success with status code <StatusCode>
    And deserialize the response with desired SearchBank Pojo class

    Examples: Set of request parameters for SearchBank API call
      | TC_No | BankName | CountryCode | StatusCode | Comments             |
      | 01    | Wells    | US          | 200        | Valid request for US |
      | 02    | Kota     | IN          | 200        | Valid request for IN |
      | 03    |          |             | 400        | Empty request`       |
      | 04    | 123456   | USA         | 400        | Invalid request      |

  @GetBankWithdrawTypes
  Scenario Outline: Validate GetBankWithdrawTypes API call with "<Comments>"
    Given Provide valid and invalid data set in request parameter "<country_ISO2>"
    When "Post" the request to "getBankWithdrawTypes" Bank API method
    Then Bank response got success with status code <StatusCode>
    And deserialize the response with desired GetBankWithdrawTypes Pojo class

    Examples: Set of request parameters for GetBankWithdrawTypes API call
      | TC_No | country_ISO2 | StatusCode | Comments             |
      | 01    | US           | 200        | Valid request for US |
      | 02    | IN           | 200        | Valid request for IN |
      | 03    | USD          | 400        | Invalid request      |
      | 04    | 123          | 400        | Invalid request      |
      | 05    |              | 400        | Empty request body   |
      | 06    |              | 400        | Empty Country ISO2   |

  @GetCurrencyList
  Scenario: Validate GetCurrencyList API call
    Given Provide valid and invalid request body
    When "Post" the request to "getCurrencyList" Bank API method
    Then Bank response got success with status code 200
    And deserialize the response with desired GetCurrencyList Pojo class
    And Validate total number of currency in response is equal to 140

