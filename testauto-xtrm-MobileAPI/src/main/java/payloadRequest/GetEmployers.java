package payloadRequest;

public class GetEmployers {
    public static String request(String searchKey) {
        String req = "{\n" +
                "        \"search_param\":\""+searchKey+"\"        \n" +
                "} ";
        return req;
    }
}
