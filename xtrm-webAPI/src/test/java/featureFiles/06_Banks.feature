Feature: Validate Bank API calls

  Scenario Outline: Validate LinkBankBeneficiary API call
    Given Provide valid and invalid data set in request parameters "IssuerAccountNumber" "UserID" "<InstitutionName>" "<Currency>" "<SWIFTBIC>" "<AccountNumber>" "<RoutingNumber>" "<CountryISO2>" "<ContactName>"
    When Post the request to "linkBankBeneficiary" Bank API method
    Then Bank response got success with status code 200

    Examples: Set of request parameters for LinkBankBeneficiary API call
      | TC_No | InstitutionName                             | Currency | SWIFTBIC    | AccountNumber                 | RoutingNumber | CountryISO2 | ContactName |
      | 43    | HSBC UK Bank Plc                            | GBP      |             | 98987319                      | 404761        | GB          | HariCP43    |
      | 44    | Bank of America Corporation                 | USD      |             | 000074057720                  | 026009593     | US          | HariCP44    |
      | 45    | Intesa San Paolo                            | EUR      | BCITITMMXXX | IT77V0306949110100000066535   |               | IT          | HariCP45    |
      | 46    | Industrial Bank of Korea                    | KPW      | IBKOKRSEXXX | 6603657501021                 |               | KP          | HariCP46    |
      | 47    | Anz New Zealand Investments Limited         | NZD      | ANZBNZ22XXX | 10853005085100                | 010853        | NZ          | HariCP47    |
      | 48    | Canadian Imperial Bank of Commerce          | CAD      |             | 6354386                       | 001005702     | CA          | HariCP48    |
      | 49    | EMIRATES NBD PJSC                           | AED      | EBILAEADXXX | AE100260001014663799401       |               | AE          | HariCP49    |
      | 50    | Banco Santander Rio S.A.                    | ARS      | BSCHARBAXXX | 41300001607                   |               | AR          | HariCP50    |
      | 51    | BBVA                                        | KED      | BCMRMXMMPYM | 1536923026                    |               | KW          | HariCP51    |
      | 52    | Standard Chartered Bank (Hong Kong) Limited | HKD      | SCBLHKHHXXX | 32989730281                   |               | HK          | HariCP52    |
      | 53    | HDFC                                        | INR      |             | 4545454554                    | HDFC0000751   | IN          | HariCP53    |
      | 54    | Banco                                       | BRL      |             | BR9700000000018980000118214C1 |               | BR          | HariCP54    |