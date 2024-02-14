package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import payloadRequest.UserWithdrawFund;
import resources.APIPath;
import resources.Utility;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Transfer extends Utility {
    String token;
    String account_number, cardToken, prepaidEmail, digitalEmail, rewardEmail, OTP;
    String bankID = null;
    String skuID = null;
    int bankCount = 0;
    Random rand = new Random();
    RequestSpecification req = null;
    APIPath apiPath;
    Response res;
    Wallet wal = new Wallet();
    Bank ban = new Bank();
    LinkCard linc = new LinkCard();
    LinkOthers lino = new LinkOthers();
    pojoClasses.userWithdrawFund.ResFailedObject failedUserWithdrawFundRes;
    pojoClasses.userWithdrawFund.ResObject successUserWithdrawFundRes;
    pojoClasses.getUserPaymentMethods.ResObject successGetUserPaymentMethods;
    pojoClasses.getPrepaidDebitCardAvailableCurrencies.ResObject successGetPrepaidDebitCardAvailableCurrencies;
    pojoClasses.corpayTxnDetails.ResPaymentDetails successCorpayPaymentDetailsRes;


    @Given("Provide valid and invalid request parameters for Transfer API {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provide_valid_and_invalid_user_account_number_in_request_parameters_for_transfer(String PAT, String Amount, String FromCurrency, String ToCurrency, String PaymentMethod, String GiftCardEmail, String RewardLinkEmail, String PrepaidCardEmail, String LinkBankID, String CardToken, String SKU) throws Exception {
        token = AccessToken.token;
        if (OTP == null) {
            if (token == null) {
                getToken();
                token = AccessToken.token;
            }
            account_number = getPAT(PAT);
            switch (LinkBankID) {
                case "Valid ID":
                    if (bankCount == 0) {
                        getLinkedBank(PAT);
                        bankCount = ban.successGetLinkedBankAccounts.getData().size();
                    }
                    if (bankCount > 0) {
                        bankID = ban.successGetLinkedBankAccounts.getData().get(0).getBeneficiary_id();
                    } else {
                        ban.provide_valid_and_invalid_data_set_in_request_parameter(PAT, "Hari Test", "778899554420", "Addr1", "Addr2", "Florida", "WA", "879456", "US", "Wells Fargo", "USD", "", "11223344", "121000248", "", "", "US", 200);
                        ban.the_request_to_bank_api_method("post", "createBankBeneficiary");
                        ban.deserialize_the_response_with_desired_create_bank_beneficiary_pojo_class();
                        getLinkedBank(PAT);
                    }
                    break;
                case "Invalid ID":
                    bankID = "4568";
                    break;
                case "Empty ID":
                    bankID = "";
                    break;
            }
            switch (CardToken) {
                case "Valid Token":
                    getLinkedCard(PAT);
                    int count = linc.successGetlinkedCardsRes.getData().size();
                    if (count > 0) {
                        for (int i = 0; i <= linc.successGetlinkedCardsRes.getData().size() - 1; i++) {
                            String cardLinkedNo = linc.successGetlinkedCardsRes.getData().get(i).getiCreditCardID();
                            linc.provide_DeleteLinkCard_valid_and_invalid_user_account_number(PAT, cardLinkedNo);
                            linc.the_request_to_link_card_api_method("post", "deleteLinkCard");
                        }
                    }
                    linc.provide_LinkCard_valid_and_invalid_request_details(account_number, "Addr1", "Addr2", "Florida", "WA", "United States", "US", "456231", "9401111999999999", "Visa Card", "transfer", "Hari Transfer", "USD", "123", "06", "2026", "Hari", "Transfer");
                    linc.the_request_to_link_card_api_method("post", "linkCard");
                    getLinkedCard(PAT);
                    cardToken = linc.successGetlinkedCardsRes.getData().get(0).getCardToken();
                    break;
                case "Invalid Token":
                    cardToken = "uihffuihs*&^*&^564";
                    break;
                case "Empty Token":
                    cardToken = "";
                    break;
            }
            if (GiftCardEmail.equals("Valid Email") || PrepaidCardEmail.equals("Valid Email") || RewardLinkEmail.equals("Valid Email")) {
                switch (PaymentMethod) {
                    case ("XTR94503"):
                        if (env.equals("Development")) prepaidEmail = getGlobalValue("devGiftCardEmail");
                        else if (env.equals("SBox")) prepaidEmail = getGlobalValue("sboxGiftCardEmail");
                        break;
                    case ("XTR94505"):
                        if (env.equals("Development")) digitalEmail = getGlobalValue("devGiftCardEmail");
                        else if (env.equals("SBox")) digitalEmail = getGlobalValue("sboxGiftCardEmail");
                        break;
                    case ("XTR94506"):
                        if (env.equals("Development")) rewardEmail = getGlobalValue("devGiftCardEmail");
                        else if (env.equals("SBox")) rewardEmail = getGlobalValue("sboxGiftCardEmail");
                        break;
                }
            } else if (GiftCardEmail.equals("Invalid Email") || PrepaidCardEmail.equals("Invalid Email") || RewardLinkEmail.equals("Invalid Email")) {
                switch (PaymentMethod) {
                    case ("XTR94503"):
                        prepaidEmail = "ABC";
                        break;
                    case ("XTR94505"):
                        digitalEmail = "ABC";
                        break;
                    case ("XTR94506"):
                        rewardEmail = "ABC";
                        break;
                }
            } else if (GiftCardEmail.equals("Empty Email") || PrepaidCardEmail.equals("Empty Email") || RewardLinkEmail.equals("Empty Email")) {
                switch (PaymentMethod) {
                    case ("XTR94503"):
                        prepaidEmail = "";
                        break;
                    case ("XTR94505"):
                        digitalEmail = "";
                        break;
                    case ("XTR94506"):
                        rewardEmail = "";
                        break;
                }
            }
            if (skuID == null) {
                if (SKU.contains("Valid")) {
                    getSKUID(ToCurrency, SKU);
                    int count = lino.successGetGiftCardsRes.getData().size();
                    if (count > 1) {
                        skuID = lino.successGetGiftCardsRes.getData().get(rand.nextInt(count - 1)).getItems().get(0).getUtid();
                    } else if (count == 1) {
                        skuID = lino.successGetGiftCardsRes.getData().get(rand.nextInt(count)).getItems().get(0).getUtid();
                    }

                } else if (SKU.contains("Empty")) {
                    skuID = "";
                } else if (SKU.contains("Invalid")) {
                    skuID = "AB45789";
                }
            }
        }
        req = postGiven(token).body(UserWithdrawFund.request(account_number, Amount, FromCurrency, ToCurrency, PaymentMethod, digitalEmail, rewardEmail, bankID, prepaidEmail, cardToken, skuID, OTP));
    }

    @When("{string} the request to {string} Transfer API method")
    public void the_request_to_transfer_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }


    @Then("deserialize the response with desired UserWithdrawFund Pojo class")
    public void deserialize_the_response_with_desired_user_withdraw_fund_pojo_class() {
        if (res.statusCode() == 400) {
            failedUserWithdrawFundRes = res.as(pojoClasses.userWithdrawFund.ResFailedObject.class);
        } else if (res.statusCode() == 200) {
            successUserWithdrawFundRes = res.as(pojoClasses.userWithdrawFund.ResObject.class);
        }
    }

    @Then("Get OTP value from mailinator")
    public void get_otp_value_from_mailinator() throws IOException {
        if (res.statusCode() == 400) {
            String message = failedUserWithdrawFundRes.getStatus().getMessage();
            if (message.equals("Security code sent. Please enter code below.")) {
                if (env.equals("Development"))
                    OTP = getEmailOTP(getGlobalValue("devEmail"), "Your fund transfer verification code");
                else if (env.equals("SBox"))
                    OTP = getEmailOTP(getGlobalValue("sboxEmail"), "Your fund transfer verification code");
            }
        }
    }

    @Then("Proceed transfer fund request above all steps with OTP {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void proceed_transfer_fund_request_above_all_steps_with_otp(String PAT, String Amount, String FromCurrency, String ToCurrency, String PaymentMethod, String GiftCardEmail, String RewardLinkEmail, String PrepaidCardEmail, String LinkBankID, String CardToken, String SKU) throws Exception {
        if (OTP != null) {
            provide_valid_and_invalid_user_account_number_in_request_parameters_for_transfer(PAT, Amount, FromCurrency, ToCurrency, PaymentMethod, GiftCardEmail, RewardLinkEmail, PrepaidCardEmail, LinkBankID, CardToken, SKU);
            the_request_to_transfer_api_method("post", "userWithdrawFund");
            deserialize_the_response_with_desired_user_withdraw_fund_pojo_class();
        } else {
            Assert.fail("OTP not received");
        }
    }

    @Then("Transfer response got success with status code {int}")
    public void transfer_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.getStatusCode());
    }

    @Then("Verify the amount in Transaction Details API {string} {string} {string}")
    public void verify_the_amount_in_transaction_details_api(String Amount, String PaymentMethod, String Currency) throws Exception {
        if (res.statusCode() == 200) {
            String transactionDetails = successUserWithdrawFundRes.getData().getTransactionID();
            wal.provide_get_user_wallet_transaction_details_valid_and_invalid_request_parameters("Global Value", transactionDetails);
            wal.the_request_to_wallet_api_method("post", "getUserWalletTransactionDetails");
            wal.wallet_response_got_success_with_status_code(200);
            wal.deserialize_the_response_with_desired_get_user_wallet_transaction_details_pojo_class();
            Object transferMethod, status, amount;
            switch (PaymentMethod) {
                case "XTR94500":
                    /*transferMethod = wal.successGetUserWalletTransactionDetailsBankRes.getData().getOrderDetails().getAdditionalProperties().get("TransferMethod");
                    assertEquals("LV", transferMethod);
                    status = wal.successGetUserWalletTransactionDetailsBankRes.getData().getOrderDetails().getAdditionalProperties().get("TransferStatus");
                    String description = wal.successGetUserWalletsTransactionsRes.getData().get(0).getDescription();
                    String orderID[] = description.split("Ref: ");
                    String orderNumber = wal.successGetUserWalletTransactionDetailsBankRes.getData().getOrderNumber();
                    Response corpayRes = RestAssured.given().header("API-Key",getGlobalValue("corpayDemoAPIKey")).queryParam("ID", orderID[1]).when().log().all().get(getGlobalValue("corpayDemoURL"));
                    corpayRes.then().log().all();
                    successCorpayPaymentDetailsRes = corpayRes.as(pojoClasses.corpayTxnDetails.ResPaymentDetails.class);
                    Object corpayStatus = successCorpayPaymentDetailsRes.getAdditionalProperties().get("PaymentStatus");
                    if(corpayStatus != null) {
                        Assert.assertEquals(corpayStatus, status);
                    } else {
                        String checkBookURL = getGlobalValue("checkBookURL");
                        Response checkBookRes = RestAssured.given().header("Authorization", getGlobalValue("checkBookAuth")).when().log().all().get(checkBookURL+orderNumber);
                        checkBookRes.then().log().all();
                        successCorpayPaymentDetailsRes = checkBookRes.as(pojoClasses.corpayTxnDetails.ResPaymentDetails.class);
                        Object checkBookStatus = successCorpayPaymentDetailsRes.getAdditionalProperties().get("status");
                        Assert.assertEquals(checkBookStatus, status);
                    }*/
                    break;

                case "XTR94507":
                    transferMethod = wal.successGetUserWalletTransactionDetailsCheckRes.getData().getOrderDetails().getTransferMethod();
                    status = wal.successGetUserWalletTransactionDetailsCheckRes.getData().getOrderDetails().getTransferStatus();
                    assertEquals("Check", transferMethod);
                    //assertEquals("Released", status);
                    break;

                case "XTR94508":
                    transferMethod = wal.successGetUserWalletTransactionDetailsRapidRes.getData().getTransferMethod();
                    status = wal.successGetUserWalletTransactionDetailsRapidRes.getData().getTransferStatus();
                    assertEquals("Bank Transfer - Rapid", transferMethod);
                    assertEquals("COMPLETED", status);
                    break;
                case "XTR94503":
                case "XTR94505":
                case "XTR94506":
                    amount = wal.successGetUserWalletTransactionDetailsGiftCardsRes.getData().getAmount();
                    transferMethod = wal.successGetUserWalletTransactionDetailsGiftCardsRes.getData().getMethod();
                    status = wal.successGetUserWalletTransactionDetailsGiftCardsRes.getData().getTransactionStatus();
                    assertEquals("$" + Amount + " " + Currency, amount);
                    assertEquals("Success", status);
                    switch (PaymentMethod) {
                        case "XTR94503":
                            assertEquals("Prepaid Virtual Visa or Mastercard", transferMethod);
                            break;
                        case "XTR94505":
                            assertEquals("Digital Gift Card", transferMethod);
                            break;
                        case "XTR94506":
                            assertEquals("Reward Link", transferMethod);
                            break;
                    }
                    break;
            }
        }
    }

    @Given("Valid request for GetUserPaymentMethods API call")
    public void valid_request_for_get_user_payment_methods_api_call() throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
            req = postGiven(token).body("{}");
        }
    }

    @Then("deserialize the response with desired GetUserPaymentMethods Pojo class")
    public void deserialize_the_response_with_desired_get_user_payment_methods_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserPaymentMethods = res.as(pojoClasses.getUserPaymentMethods.ResObject.class);
        }
    }

    @Then("deserialize the response with desired GetPrepaidDebitCardAvailableCurrencies Pojo class")
    public void deserialize_the_response_with_desired_get_prepaid_debit_card_available_currencies_pojo_class() {
        if (res.statusCode() == 200) {
            successGetPrepaidDebitCardAvailableCurrencies = res.as(pojoClasses.getPrepaidDebitCardAvailableCurrencies.ResObject.class);
        }
    }


/**********************************************************************************************************************/
    private void getLinkedCard(String PAT) throws Exception {
        linc.provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
        linc.the_request_to_link_card_api_method("post", "getLinkedCards");
        linc.deserialize_the_response_with_desired_get_linked_cards_pojo_class();
    }

    private void getLinkedBank(String PAT) throws Exception {
        ban.provide_valid_and_invalid_user_account_number_in_request_parameters(PAT);
        ban.the_request_to_bank_api_method("post", "getLinkedBankAccounts");
        ban.deserialize_the_response_with_desired_get_linked_bank_accounts_pojo_class();
    }

    private void getSKUID(String currency, @NotNull String sku) throws Exception {
        lino.provide_valid_and_invalid_currency_code_in_request_parameter(currency);
        switch (sku) {
            case "Valid PrepaidCard SKU":
                lino.the_request_to_link_others_api_method("post", "getPrepaidGiftCards");
                break;
            case "Valid GiftCard SKU":
                lino.the_request_to_link_others_api_method("post", "getDigitalGiftCards");
                break;
            case "Valid RewardLink SKU":
                lino.the_request_to_link_others_api_method("post", "getRewardGiftCards");
                break;
        }
        lino.deserialize_the_response_with_desired_get_gift_cards_pojo_class();
    }
}
