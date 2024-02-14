package payloadRequest;

public class GetUserWalletTransactionDetails {

    public static String request(String PAT, String transactionID) {
        String req = "{\n" +
                "        \"account_number\":\"" + PAT + "\", \n" +
                "        \"transaction_id\":\"" + transactionID + "\"\n" +
                "} ";
        return req;
    }
}
