package payloadRequest;

public class GetDigitalGiftCards {

    public static String request(String currencyCode) {
        String req = "{\n" +
                "\t\"Currency\":\""+currencyCode+"\"\n" +
                "}  ";
        return req;
    }
}
