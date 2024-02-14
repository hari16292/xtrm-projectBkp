package payloadRequest;

public class GetExchangeRate {
    public static String request(String PAT, String amount, String fromCurrency, String toCurrency) {
        String req = "{\n" +
                "     \"account_number\":\""+PAT+"\",\n" +
                "     \"amount\":\""+amount+"\",\n" +
                "     \"from_currency\":\""+fromCurrency+"\",\n" +
                "     \"to_currency\":\""+toCurrency+"\"\n" +
                "} ";
        return req;
    }
}
