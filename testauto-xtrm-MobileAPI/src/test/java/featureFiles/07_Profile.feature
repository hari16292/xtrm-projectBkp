@Profile @Regression
Feature: Validate Profile API calls

  Background: Get valid Token value to access all API's
    Given get access token

  @GetUserPersonal
  Scenario Outline: Validate GetUserPersonal API call with PAT - "<PAT>"
    Given Provide GetUserPersonal valid and invalid request "<PAT>"
    When "Post" the request to "getUserPersonal" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired GetUserPersonal pojo class

    Examples: Set of request parameters for GetUserPersonal API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |

  @UpdateUserPersonalProfile
  Scenario Outline: Validate UpdateUserPersonalProfile API call "<Comments>"
    Given Provide UpdateUserPersonalProfile valid and invalid request "<PAT>" "<FirstName>" "<LastName>" "<TaxNumber>" "<DOB>"
    When "Post" the request to "updateUserPersonalProfile" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired UpdateUserPersonalProfile pojo class
    And Validate updated data available in GetUserPersonal

    Examples: Set of request parameters for UpdateUserPersonalProfile API call
      | TC_No | PAT          | FirstName | LastName | TaxNumber    | DOB        | StatusCode | Comments      |
      | 01    | Global Value | Hari      | Test     | Valid Number | 1995-05-15 | 200        | Valid request |

  @GetUserContact
  Scenario Outline: Validate GetUserContact API call with PAT - "<PAT>"
    Given Provide GetUserPersonal valid and invalid request "<PAT>"
    When "Post" the request to "getUserContact" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired GetUserContact pojo class

    Examples: Set of request parameters for GetUserContact API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |

  @UpdateUserContactInfo
  Scenario Outline: Validate UpdateUserContactInfo API call "<Comments>"
    Given Provide UpdateUserContactInfo valid and invalid request "<PAT>" "<Address1>" "<Address2>" "<ApartmentNumber>" "<City>" "<Country>" "<State>" "<ZipCode>"
    When "Post" the request to "updateUserContactInfo" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired UpdateUserPersonalProfile pojo class
    And Validate updated data available in GetUserContact

    Examples: Set of request parameters for UpdateUserContactInfo API call
      | TC_No | PAT          | Address1 | Address2      | ApartmentNumber | City       | Country | State      | ZipCode | StatusCode | Comments      |
      | 01    | Global Value | Address1 | Valid Address | 16              | Coimbatore | India   | Tamil Nadu | 641041  | 200        | Valid request |

  @GetUserEmployer
  Scenario Outline: Validate GetUserEmployer API call with PAT - "<PAT>"
    Given Provide GetUserPersonal valid and invalid request "<PAT>"
    When "Post" the request to "getUserEmployer" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired GetUserEmployer pojo class

    Examples: Set of request parameters for GetUserContact API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |

  @GetEmployers
  Scenario Outline: Validate GetEmployers API call "<Comments>"
    Given Provide GetEmployers valid and invalid request "<SearchKey>"
    When "Post" the request to "getEmployers" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired GetEmployers pojo class
    And validate company details available for search details <StatusCode>

    Examples: Set of request parameters for GetEmployers API call
      | TC_No | SearchKey                      | StatusCode | Comments                  |
      | 01    | TestAPICompany                 | 200        | Search with Company name  |
      | 02    | Hari Test                      | 200        | Search with name          |
      | 03    | testapicompany1@mailinator.com | 200        | Search with email         |
      | 04    | 987456310                      | 200        | Search with mobile number |
      | 05    |                                | 400        | Empty key word            |
      | 06    | ^&^*&                          | 400        | Invalid Key word          |

  @DeleteUserEmployerInfo
  Scenario Outline: Validate DeleteUserEmployerInfo API call with PAT - "<Comments>"
    Given Provide DeleteUserEmployerInfo valid and invalid request "<PAT>" "<CompanyAccount>"
    When "Post" the request to "deleteUserEmployerInfo" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired UpdateUserPersonalProfile pojo class
    And Verify employer has deleted from the user

    Examples: Set of request parameters for DeleteUserEmployerInfo API call
      | TC_No | PAT          | CompanyAccount  | StatusCode | Comments        |
      | 01    | Global Value | Valid Company   | 200        | Valid request   |
      | 02    | Global Value | Invalid Company | 400        | Invalid request |
      | 03    | Global Value | Empty Value     | 400        | Empty request   |

  @SaveUserEmployerInfo
  Scenario Outline: Validate SaveUserEmployerInfo API call with PAT - "<Comments>"
    Given Provide SaveUserEmployerInfo valid and invalid request "<PAT>" "<CompanyAccount>" "<Designation>"
    When "Post" the request to "saveUserEmployerInfo" Profile API method
    Then Profile response got success with status code <StatusCode>
    And deserialize the response with desired SaveUserEmployerInfo pojo class
    And verify added employer details with GetUserEmployer

    Examples: Set of request parameters for SaveUserEmployerInfo API call
      | TC_No | PAT          | CompanyAccount | Designation | StatusCode | Comments      |
      | 01    | Global Value | Valid Company  | Engineer    | 200        | valid request |
