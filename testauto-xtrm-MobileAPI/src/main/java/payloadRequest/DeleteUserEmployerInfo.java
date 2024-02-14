package payloadRequest;

public class DeleteUserEmployerInfo {
    public static String request(String PAT, String companyAccount) {
        String req = "{\n" +
                "\t\"account_number\": \""+PAT+"\",\n" +
                "\t\"company_account_number\": \""+companyAccount+"\"\n" +
                "}";
        return req;
    }
}
