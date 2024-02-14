@Settings @Regression
Feature: Validate Settings API calls

  @GetUserAccountInfo
  Scenario Outline: Validate GetUserAccountInfo API call
    Given Valid request for GetUserAccountInfo API call "<PAT>"
    When "Post" the request to "getUserAccountInfo" Settings API method
    Then Settings response got success with status code <StatusCode>
    And deserialize the response with desired GetUserAccountInfo Pojo class

    Examples: Set of request parameters for GetClaims API call
      | TC_No | PAT           | StatusCode |
      | 01    | Global Value  | 200        |
      | 02    | Invalid Value | 401        |
      | 03    | Empty Value   | 401        |

  @ChangePassword
  Scenario Outline: Validate ChangePassword API call - "<Comments>"
    Given Valid request for ChangePassword API call "<PAT>" "<CurrentPassword>" "<NewPassword>"
    When "Post" the request to "changePassword" Settings API method
    Then Settings response got success with status code <StatusCode>
    And deserialize the response with desired ChangePassword Pojo class

    Examples: Set of request parameters for GetClaims API call
      | TC_No | PAT           | CurrentPassword | NewPassword  | StatusCode | Comments                                 |
      | 01    | Global Value  | Hari@123        | Hari@1234    | 200        | Valid request                            |
      | 02    | Global Value  | Hari@1234       | Hari@123     | 400        | Not allowed to set last 3 passwords used |
      | 03    | Global Value  | Hari@1234       | Hari@12345   | 200        | Valid request to reset password back     |
      | 04    | Global Value  | Hari@12345      | Hari@123456  | 200        | Valid request to reset password back     |
      | 05    | Global Value  | Hari@123456     | Hari@1234567 | 200        | Valid request to reset password back     |
      | 06    | Global Value  | Hari@1234567    | Hari@123     | 200        | Valid request to reset password back     |
      | 07    | Invalid Value | Hari@123        | Hari@1234    | 401        | Invalid PAT                              |
      | 08    | Empty Value   | Hari@123        | Hari@1234    | 401        | Empty PAT                                |
      | 09    | Global Value  | Hari@1234       | Hari@1234    | 400        | Invalid Current Password                 |
      | 10    | Global Value  |                 | Hari@1234    | 400        | Empty Current Password                   |
      | 11    | Global Value  | Hari@123        | ...          | 400        | Invalid new password                     |
      | 12    | Global Value  | Hari@123        |              | 400        | Empty new password                       |
      | 13    | Global Value  | Hari@123        | Hari@123     | 400        | Same Current and New password            |

  @GetOTPForgotPassword
  Scenario Outline: Validate GetOTPForgotPassword API call
    Given Valid request for GetOTPForgotPassword API call "<PAT>" "<Email>"
    When "Post" the request to "getOTPForgotPassword" Settings API method
    Then Settings response got success with status code <StatusCode>
    And deserialize the response with desired GetOTPForgotPassword Pojo class
    And Validate OTP triggered successfully "<Email>"

    Examples: Set of request parameters for GetOTPForgotPassword API call
      | TC_No | PAT           | Email         | StatusCode |
      | 01    | Global Value  | Valid Email   | 200        |
      | 02    | Invalid Value | Invalid Email | 401        |
      | 03    | Empty Value   | Invalid Email | 401        |
      | 04    | Global Value  | Invalid Email | 400        |
      | 05    | Global Value  | Empty Email   | 400        |

  @ValidateOTPForgotPassword
  Scenario Outline: Validate ValidateOTPForgotPassword API call
    Given Valid request for ValidateOTPForgotPassword API call "<PAT>" "<OTP>"
    When "Post" the request to "validateOTPForgotPassword" Settings API method
    Then Settings response got success with status code <StatusCode>
    And deserialize the response with desired ValidateOTPForgotPassword Pojo class

    Examples: Set of request parameters for GetOTPForgotPassword API call
      | TC_No | PAT           | OTP         | StatusCode |
      | 01    | Global Value  | Valid OTP   | 200        |
      | 02    | Invalid Value | Invalid OTP | 401        |
      | 03    | Empty Value   | Invalid OTP | 401        |
      | 04    | Global Value  | Invalid OTP | 400        |
      | 05    | Global Value  | Empty OTP   | 400        |

