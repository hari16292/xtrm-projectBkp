package payloadRequest;

public class DeleteLinkCard {

    public static String request(String PAT, String linkedCardID) {
        String req = "{       \n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"linkedCardId\":\""+linkedCardID+"\"        \n" +
                "}  ";
        return req;
    }
}
