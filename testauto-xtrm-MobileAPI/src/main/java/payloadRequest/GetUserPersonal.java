package payloadRequest;

public class GetUserPersonal {

    public static String request(String PAT) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\"        \n" +
                "}  ";
        return req;
    }
}
