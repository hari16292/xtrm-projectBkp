package payloadRequest;

import pojoClasses.editLinkCard.ReqObject;

public class EditLinkCard {
    public static ReqObject request(String LinkID, String PAT, String Address1, String Address2, String City, String State, String Country, String CountryISO2, String PostalCode, String CardNumber, String CardType, String Name, String Currency, String CVV, String ExpiryMonth, String ExpiryYear, String FirstName, String LastName) {

        ReqObject ro = new ReqObject();
        ro.setLinkedCardId(LinkID);
        ro.setAccount_number(PAT);
        ro.setAddress1(Address1);
        ro.setAddress2(Address2);
        ro.setCity(City);
        ro.setState(State);
        ro.setCountry(Country);
        ro.setPostal_code(PostalCode);
        ro.setCredit_card_number(CardNumber);
        ro.setCredit_card_type(CardType);
        ro.setCvv(CVV);
        ro.setCountry_code_iso2(CountryISO2);
        ro.setCurrency_code(Currency);
        ro.setExpire_month(ExpiryMonth);
        ro.setExpire_year(ExpiryYear);
        ro.setFirst_name(FirstName);
        ro.setLast_name(LastName);
        ro.setName_on_card(Name);

        return ro;
    }
}