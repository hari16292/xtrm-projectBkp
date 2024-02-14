package payloadRequest;

public class ChangePassword {
    public static String request(String PAT, String currentPassword, String newPassword) {
        String req = "{\n" +
                "     \"account_number\":\""+PAT+"\",\n" +
                "     \"current_password\":\""+currentPassword+"\",\n" +
                "     \"new_password\":\""+newPassword+"\"\n" +
                "}  ";
        return req;
    }
}
