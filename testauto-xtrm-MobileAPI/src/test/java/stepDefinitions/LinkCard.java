package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadRequest.DeleteLinkCard;
import payloadRequest.EditLinkCard;
import payloadRequest.GetLinkedCards;
import resources.APIPath;
import resources.Utility;

import static org.junit.Assert.assertEquals;

public class LinkCard extends Utility {

    String token;
    RequestSpecification req;
    APIPath apiPath;
    Response res;
    String cardLinkedNo = "";
    String account_number;
    pojoClasses.commonClasses.ResFailedAuth failedAuthRes;
    pojoClasses.getLinkedCards.ResObject successGetlinkedCardsRes;
    pojoClasses.getLinkedBankAccounts.ResFailed failedGetLinkedCardsRes;
    pojoClasses.linkCard.ResObject successLinkCardRes;
    pojoClasses.linkCard.ResFailedObject failedLinkCardRes;
    pojoClasses.deleteLinkCard.ResObject successDeleteCardRes;


    @Given("Provide valid and invalid user account number {string} in request parameters for GetLinkedCards")
    public void provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(String PAT) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        req = postGiven(token).body(GetLinkedCards.request(account_number));
    }

    @When("{string} the request to {string} Link Card API method")
    public void the_request_to_link_card_api_method(String httpMethod, String apiMethodPath) {
        apiPath = APIPath.valueOf(apiMethodPath);
        res = req.when().post(apiPath.getAPIPath());
    }

    @Then("Link Card response got success with status code {int}")
    public void link_card_response_got_success_with_status_code(Object StatusCode) {
        assertEquals(StatusCode, res.getStatusCode());
    }

    @Then("deserialize the response with desired GetLinkedCards Pojo class")
    public void deserialize_the_response_with_desired_get_linked_cards_pojo_class() {
        if (res.getStatusCode() == 200) {
            successGetlinkedCardsRes = res.as(pojoClasses.getLinkedCards.ResObject.class);
        } else {
            failedGetLinkedCardsRes = res.as(pojoClasses.getLinkedBankAccounts.ResFailed.class);
        }
    }

    @Given("Provide LinkCard valid and invalid request details {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provide_LinkCard_valid_and_invalid_request_details(String PAT, String Address1, String Address2, String City, String State, String Country, String CountryISO2, String PostalCode, String CardNumber, String CardType, String LinkType, String Name, String Currency, String CVV, String ExpiryMonth, String ExpiryYear, String FirstName, String LastName) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        if(PAT.equals("Global Value")) {
            provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
            the_request_to_link_card_api_method("post", "getLinkedCards");
            deserialize_the_response_with_desired_get_linked_cards_pojo_class();
            if (successGetlinkedCardsRes.getData().size() > 0) {
                for (int i = 0; i <= successGetlinkedCardsRes.getData().size() - 1; i++) {
                    cardLinkedNo = successGetlinkedCardsRes.getData().get(i).getiCreditCardID();
                    provide_DeleteLinkCard_valid_and_invalid_user_account_number(PAT, cardLinkedNo);
                    the_request_to_link_card_api_method("post", "deleteLinkCard");
                }
            }
        }

        req = postGiven(token).body(payloadRequest.LinkCard.request(account_number, Address1, Address2, City, State, Country, CountryISO2, PostalCode, CardNumber, CardType, LinkType, Name, Currency, CVV, ExpiryMonth, ExpiryYear, FirstName, LastName));
    }

    @Then("deserialize the response with desired LinkCards Pojo class")
    public void deserialize_the_response_with_desired_link_cards_pojo_class() {
        if (res.statusCode() == 200) {
            successLinkCardRes = res.as(pojoClasses.linkCard.ResObject.class);
        } else if (res.statusCode() == 400) {
            failedLinkCardRes = res.as(pojoClasses.linkCard.ResFailedObject.class);
        }
    }

    @Given("Provide DeleteLinkCard valid and invalid user account number {string} {string}")
    public void provide_DeleteLinkCard_valid_and_invalid_user_account_number(String PAT, String linkedCardId) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        account_number = getPAT(PAT);
        if (linkedCardId.equals("Valid ID")) {
            if (!PAT.equals("Empty Value")) {
                provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
                the_request_to_link_card_api_method("post", "getLinkedCards");
                deserialize_the_response_with_desired_get_linked_cards_pojo_class();
                int cards = successGetlinkedCardsRes.getData().size();
                if (cards <= 0) {
                    provide_LinkCard_valid_and_invalid_request_details(PAT, "Addr1", "Addr2", "Florida", "WA", "United States", "US", "564987", "9401211999999997", "Visa Card", "transfer", "Hari Transfer", "USD", "123", "06", "2026", "Hari", "Transfer");
                    the_request_to_link_card_api_method("post", "linkCard");
                    provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
                    the_request_to_link_card_api_method("post", "getLinkedCards");
                    deserialize_the_response_with_desired_get_linked_cards_pojo_class();
                }
                cardLinkedNo = successGetlinkedCardsRes.getData().get(0).getiCreditCardID();
            }
            req = postGiven(token).body(DeleteLinkCard.request(account_number, cardLinkedNo));
        } else if (linkedCardId.equals("Empty ID")) {
            req = postGiven(token).body(DeleteLinkCard.request(account_number, ""));
        } else {
            if(!linkedCardId.isEmpty())
                cardLinkedNo = linkedCardId;
            req = postGiven(token).body(DeleteLinkCard.request(account_number, cardLinkedNo));
        }
    }

    @Then("deserialize the response with desired DeleteLinkCard pojo class")
    public void deserialize_the_response_with_desired_DeleteLinkCard_pojo_class() {
        if (res.statusCode() == 200) {
            successDeleteCardRes = res.as(pojoClasses.deleteLinkCard.ResObject.class);
        } else if (res.statusCode() == 401) {
            failedAuthRes = res.as(pojoClasses.commonClasses.ResFailedAuth.class);
        } else {
            failedLinkCardRes = res.as(pojoClasses.linkCard.ResFailedObject.class);
        }
    }

    @Given("Provide LinkCard valid and invalid user account number {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provide_link_card_valid_and_invalid_user_account_number(String LinkID, String PAT, String Address1, String Address2, String City, String State, String Country, String CountryISO2, String PostalCode, String CardNumber, String CardType, String Name, String Currency, String CVV, String ExpiryMonth, String ExpiryYear, String FirstName, String LastName) throws Exception {
        token = AccessToken.token;
        if (token == null) {
            getToken();
            token = AccessToken.token;
        }
        if (LinkID.equals("Valid Transfer LinkID")) {
            provide_LinkCard_valid_and_invalid_request_details(PAT, "Addr1", "Addr2", "Florida", "WA", "United States", "US", "564987", "9401211999999997", "Visa Card", "transfer", "Hari Transfer", "USD", "123", "06", "2026", "Hari", "Transfer");
            the_request_to_link_card_api_method("post", "linkCard");
            deserialize_the_response_with_desired_link_cards_pojo_class();
            provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
            the_request_to_link_card_api_method("post", "getLinkedCards");
            deserialize_the_response_with_desired_get_linked_cards_pojo_class();
            int cards = successGetlinkedCardsRes.getData().size();
            if (cards > 0) {
                for (int i = 0; i <= cards - 1; i++) {
                    if (successGetlinkedCardsRes.getData().get(i).getLinkCardType().equals("transfer")) {
                        cardLinkedNo = successGetlinkedCardsRes.getData().get(i).getiCreditCardID();
                    }
                }
            }

        } else if (LinkID.equals("Valid Identity LinkID") && !PAT.equals("Invalid Value") && !PAT.equals("Empty Value")) {
            provide_LinkCard_valid_and_invalid_request_details(PAT, "Addr1", "Addr2", "Florida", "WA", "United States", "US", "564987", "4111111111111111", "Visa Card", "identity", "Hari Identity", "USD", "123", "06", "2026", "Hari", "Identity");
            the_request_to_link_card_api_method("post", "linkCard");
            deserialize_the_response_with_desired_link_cards_pojo_class();
            provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
            the_request_to_link_card_api_method("post", "getLinkedCards");
            deserialize_the_response_with_desired_get_linked_cards_pojo_class();
            int cards = successGetlinkedCardsRes.getData().size();
            if (cards > 0) {
                for (int i = 0; i <= cards - 1; i++) {
                    if (successGetlinkedCardsRes.getData().get(i).getLinkCardType().equals("identity")) {
                        cardLinkedNo = successGetlinkedCardsRes.getData().get(i).getiCreditCardID();
                    }
                }
            }
        } else if(LinkID.equals("Empty Identity LinkID")) {
            cardLinkedNo = "";
        } else if(LinkID.equals("Invalid Identity LinkID")) {
            cardLinkedNo = "12340";
        }

        account_number = getPAT(PAT);
        req = postGiven(token).body(EditLinkCard.request(cardLinkedNo, account_number, Address1, Address2, City, State, Country, CountryISO2, PostalCode, CardNumber, CardType, Name, Currency, CVV, ExpiryMonth, ExpiryYear, FirstName, LastName));
    }

    @Then("deserialize the response with desired EditLinkCard pojo class")
    public void deserialize_the_response_with_desired_edit_link_card_pojo_class() {
        if (res.statusCode() == 200) {
            successLinkCardRes = res.as(pojoClasses.linkCard.ResObject.class);
        }
    }

    @Then("Verify the updated field in fetch card response {string} {string}")
    public void verify_the_updated_field_in_fetch_card_response(String PAT, String Address2) throws Exception {
        if (res.statusCode() == 200) {
            provide_valid_and_invalid_user_account_number_in_request_parameters_for_get_linked_cards(PAT);
            the_request_to_link_card_api_method("post", "getLinkedCards");
            deserialize_the_response_with_desired_get_linked_cards_pojo_class();
            int cards = successGetlinkedCardsRes.getData().size();
            if (cards > 0) {
                for (int i = 0; i <= cards - 1; i++) {
                    assertEquals(Address2, successGetlinkedCardsRes.getData().get(i).getvAddress2());

                }
            }
        }
    }
}
