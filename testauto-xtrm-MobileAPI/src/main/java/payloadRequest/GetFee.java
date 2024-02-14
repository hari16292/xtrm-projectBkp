package payloadRequest;

public class GetFee {

    public static String request(String PAT, String toAccount, String currency, String amount, String transactionType, String paymentMethod) {
        String req = "{\n" +
                "    \"from_account_number\":\""+PAT+"\",\n" +
                "    \"to_account_number\":\""+toAccount+"\",\n" +
                "    \"currency_code\":\""+currency+"\",\n" +
                "    \"amount\":\""+amount+"\",\n" +
                "    \"transaction_type\":\""+transactionType+"\",\n" +
                "    \"payment_method_type\":\""+paymentMethod+"\"\n" +
                "}";
        return req;
    }
}
