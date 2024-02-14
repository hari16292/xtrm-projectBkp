package payloadRequest;

public class ValidateOTPForgotPassword {
    public static String request(String PAT, String OTP) {
        String req = "{\n" +
                "     \"account_number\":\""+PAT+"\",\n" +
                "     \"verification_code\":\""+OTP+"\"\n" +
                "} ";
        return req;
    }
}
