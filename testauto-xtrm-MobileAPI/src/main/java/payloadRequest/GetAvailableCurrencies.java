package payloadRequest;

public class GetAvailableCurrencies {
    public static String request(String PAT) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\"\n" +
                "} ";
        return req;
    }
}
