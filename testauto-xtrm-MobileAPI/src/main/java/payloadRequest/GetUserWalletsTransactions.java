package payloadRequest;

public class GetUserWalletsTransactions {

    public static String request(String PAT, String currencyCode, String searchParam) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"currency\":\""+currencyCode+"\",\n" +
                "        \"current_page\":\"1\" , \n" +
                "        \"items_per_page\":\"5\",  \n" +
                "        \"transaction_type\":\"All\",\n" +
                "        \"search_param\":\""+searchParam+"\" \n" +
                "}";
        return req;
    }
}
