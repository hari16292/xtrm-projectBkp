package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.*;
import pojoClasses.commonClasses.ResFailedAuth;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class Wallet extends Utility {

    String token;
    String account_number;
    static String paymentMethod, transType;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    ResFailedAuth failedAuthRes;
    pojoClasses.getUserWallets.ResObject successGetUserWalletRes;
    pojoClasses.getAvailableCurrencies.ResObject successGetAvailableCurrenciesRes;
    pojoClasses.createWallet.ResObject successCreateWalletRes;
    pojoClasses.getUserWalletsTransactions.ResObject successGetUserWalletsTransactionsRes;
    pojoClasses.getUserWalletTransactionDetails.send.ResObject successGetUserWalletTransactionDetailsSendRes;
    pojoClasses.getUserWalletTransactionDetails.currencyExchange.ResObject successGetUserWalletTransactionDetailsExchangeRes;
    pojoClasses.getUserWalletTransactionDetails.giftCards.ResObject successGetUserWalletTransactionDetailsGiftCardsRes;
    pojoClasses.getUserWalletTransactionDetails.xtr94500.ResObject successGetUserWalletTransactionDetailsBankRes;
    pojoClasses.getUserWalletTransactionDetails.xtr94507.ResObject successGetUserWalletTransactionDetailsCheckRes;
    pojoClasses.getUserWalletTransactionDetails.xtr94508.ResObject successGetUserWalletTransactionDetailsRapidRes;
    pojoClasses.updateWallet.ResObject successUpdateWallet;
    pojoClasses.updateWallet.ResFailedObject failedUpdateWallet;

    @Given("Provide GetUserWallets valid and invalid user account number {string}")
    public void provide_get_user_wallets_valid_and_invalid_user_account_number(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetUserWallets.request(account_number));
    }

    @When("{string} the request to {string} Wallet API method")
    public void the_request_to_wallet_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Wallet response got success with status code {int}")
    public void wallet_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.statusCode());
    }

    @Then("deserialize the response with desired GetUserWallets pojo class")
    public void deserialize_the_response_with_desired_get_user_wallets_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserWalletRes = res.as(pojoClasses.getUserWallets.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Given("Provide GetAvailableCurrencies valid and invalid user account number {string}")
    public void provide_get_available_currencies_valid_and_invalid_user_account_number(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetAvailableCurrencies.request(account_number));
    }

    @Then("deserialize the response with desired GetAvailableCurrencies pojo class")
    public void deserialize_the_response_with_desired_get_available_currencies_pojo_class() {
        if (res.statusCode() == 200) {
            successGetAvailableCurrenciesRes = res.as(pojoClasses.getAvailableCurrencies.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }

    @Then("verify the total number of currency is {int}")
    public void verify_the_total_number_of_currency_is(Object totalCurrency) {
        if (res.statusCode() == 200) {
            assertEquals(totalCurrency, successGetAvailableCurrenciesRes.getData().size());
        }
    }

    @Given("Provide CreateWallet valid and invalid request parameters {string} {string}")
    public void provide_create_wallet_valid_and_invalid_request_parameters(String PAT, String CurrencyCode) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(CreateWallet.request(account_number, CurrencyCode));
    }

    @Then("deserialize the response with desired CreateWallet pojo class")
    public void deserialize_the_response_with_desired_create_wallet_pojo_class() {
        if (res.statusCode() == 200)
            successCreateWalletRes = res.as(pojoClasses.createWallet.ResObject.class);
        else if (res.statusCode() == 401)
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
    }

    @Then("validate the duplicate wallet creation {string}")
    public void validate_the_duplicate_wallet_creation(String Currency) {
        if (res.statusCode() == 200) {
            if (successCreateWalletRes.getStatus().getCode().equals("604")) {
                assertEquals("Wallet already exists.", successCreateWalletRes.getStatus().getMessage());
            }
        }
    }

    @Given("Provide GetUserWalletsTransactions valid and invalid request parameters {string} {string} {string}")
    public void provide_get_user_wallets_transactions_valid_and_invalid_request_parameters(String PAT, String currencyCode, String searchParam) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetUserWalletsTransactions.request(account_number, currencyCode, searchParam));
    }

    @Then("deserialize the response with desired GetUserWalletsTransactions pojo class")
    public void deserialize_the_response_with_desired_get_user_wallets_transactions_pojo_class() {
        if (res.statusCode() == 200) {
            successGetUserWalletsTransactionsRes = res.as(pojoClasses.getUserWalletsTransactions.ResObject.class);
        }
    }

    @Given("Provide GetUserWalletTransactionDetails valid and invalid request parameters {string} {string}")
    public void provide_get_user_wallet_transaction_details_valid_and_invalid_request_parameters(String PAT, String transactionID) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        if (!transactionID.equals("Invalid ID") && !transactionID.equals("Empty ID")) {
            provide_get_user_wallets_transactions_valid_and_invalid_request_parameters(PAT, "USD", "");
            the_request_to_wallet_api_method("post", "getUserWalletsTransactions");
            deserialize_the_response_with_desired_get_user_wallets_transactions_pojo_class();
            if (successGetUserWalletsTransactionsRes.getData().size() > 0) {
                transactionID = successGetUserWalletsTransactionsRes.getData().get(0).getTransaction_id();
                transType = successGetUserWalletsTransactionsRes.getData().get(0).getTransaction_icon();
                paymentMethod = successGetUserWalletsTransactionsRes.getData().get(0).getEmail();
            }
        } else if (transactionID.equals("Invalid ID")) {
            transactionID = "123456";
        } else if (transactionID.equals("Empty ID")) {
            transactionID = "";
        }
        req = postGiven(token).body(GetUserWalletTransactionDetails.request(account_number, transactionID));
    }

    @Then("deserialize the response with desired GetUserWalletTransactionDetails pojo class")
    public void deserialize_the_response_with_desired_get_user_wallet_transaction_details_pojo_class() {
        if (res.statusCode() == 200) {
            if ((paymentMethod.contains("Company") || paymentMethod.contains("Person")) && transType.equals("Payment Sent")) {
                successGetUserWalletTransactionDetailsSendRes = res.as(pojoClasses.getUserWalletTransactionDetails.send.ResObject.class);
            } else if (paymentMethod.equals("ACH") || paymentMethod.equalsIgnoreCase("Wire")) {
                successGetUserWalletTransactionDetailsBankRes = res.as(pojoClasses.getUserWalletTransactionDetails.xtr94500.ResObject.class);
            } else if ((paymentMethod.contains("Company") || paymentMethod.contains("Person")) && transType.equals("Currency Exchange")) {
                successGetUserWalletTransactionDetailsExchangeRes = res.as(pojoClasses.getUserWalletTransactionDetails.currencyExchange.ResObject.class);
            } else if (paymentMethod.equals("Digital Gift Card") || paymentMethod.equals("Prepaid Virtual Visa")) {
                successGetUserWalletTransactionDetailsGiftCardsRes = res.as(pojoClasses.getUserWalletTransactionDetails.giftCards.ResObject.class);
            } else if (paymentMethod.equals("Check Transfer")) {
                successGetUserWalletTransactionDetailsCheckRes = res.as(pojoClasses.getUserWalletTransactionDetails.xtr94507.ResObject.class);
            } else if (paymentMethod.equals("Bank Transfer - Rapid")) {
                successGetUserWalletTransactionDetailsRapidRes = res.as(pojoClasses.getUserWalletTransactionDetails.xtr94508.ResObject.class);
            }
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        }
    }


    @Given("Provide UpdateWallet valid and invalid request parameters {string} {string} {string}")
    public void provide_update_wallet_valid_and_invalid_request_parameters(String PAT, String WalletID, String WalletName) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(UpdateWallet.request(account_number, WalletID, WalletName));
    }

    @Then("deserialize the response with desired UpdateWallet pojo class")
    public void deserialize_the_response_with_desired_update_wallet_pojo_class() {
        if(res.statusCode() == 200) {
            successUpdateWallet = res.as(pojoClasses.updateWallet.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else if(res.statusCode() == 400) {
            failedUpdateWallet = res.as(pojoClasses.updateWallet.ResFailedObject.class);
        }
    }

}
