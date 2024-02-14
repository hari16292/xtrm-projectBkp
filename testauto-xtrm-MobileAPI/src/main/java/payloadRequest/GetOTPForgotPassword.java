package payloadRequest;

public class GetOTPForgotPassword {
    public static String request(String PAT, String email) {
        String req = "{\n" +
                "\t\"account_number\": \""+PAT+"\",\n" +
                "\t\"email_id\": \""+email+"\"\n" +
                "}";
        return req;
    }
}
