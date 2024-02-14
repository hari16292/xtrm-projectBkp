package payloadRequest;

public class SaveUserEmployerInfo {
    public static String request(String PAT, String compAccNo, String designation) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"company_account_number\":\""+compAccNo+"\",\n" +
                "        \"designation\":\""+designation+"\"\n" +
                "}";
        return req;
    }
}
