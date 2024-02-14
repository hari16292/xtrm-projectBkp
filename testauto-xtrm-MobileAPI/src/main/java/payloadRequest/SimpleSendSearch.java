package payloadRequest;

public class SimpleSendSearch {

    public static String request(String PAT, String SearchKey) {
        String req = "{\n" +
                "    \"search_param\":\""+SearchKey+"\",\n" +
                "    \"account_number\":\""+PAT+"\"\n" +
                "}  ";
        return req;
    }
}
