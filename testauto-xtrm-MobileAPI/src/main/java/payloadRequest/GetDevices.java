package payloadRequest;

public class GetDevices {

    public static String request(String PAT) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"current_page\":\"1\",\n" +
                "        \"items_per_page\":\"5\"        \n" +
                "} ";
        return req;
    }
}
