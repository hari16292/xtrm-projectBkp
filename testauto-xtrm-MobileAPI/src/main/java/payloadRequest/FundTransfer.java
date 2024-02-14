package payloadRequest;

public class FundTransfer {

    public static String request(String account_number, String amount, String currencyCode, String toAccount, String eMail, String description) {
        String req = "{\n" +
                "        \"amount\":\""+amount+"\",\n" +
                "        \"currency_code\":\""+currencyCode+"\",\n" +
                "        \"description\":\""+description+"\",\n" +
                "        \"from_account_number\":\""+account_number+"\",\n" +
                "        \"to_account_number\":\""+toAccount+"\",\n" +
                "        \"to_email_id\":\""+eMail+"\"\n" +
                "}";
        return req;
    }
}
