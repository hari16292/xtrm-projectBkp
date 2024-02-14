package payloadRequest;

public class BookCurrencyExchange {
    public static String request(String PAT, String amount, String exchangeRate, String fromCurrency, String toCurrency) {
        return "{\n" +
                "     \"account_number\":\""+PAT+"\",\n" +
                "     \"amount\":\""+amount+"\",\n" +
                "     \"exchange_rate\":\""+exchangeRate+"\",\n" +
                "     \"from_currency\":\""+fromCurrency+"\",\n" +
                "     \"to_currency\":\""+toCurrency+"\"\n" +
                "} ";
    }
}
