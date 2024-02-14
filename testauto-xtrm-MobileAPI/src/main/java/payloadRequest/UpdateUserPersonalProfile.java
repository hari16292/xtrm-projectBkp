package payloadRequest;

public class UpdateUserPersonalProfile {
    public static String request(String PAT, String firstName, String lastName, String taxNumber, String dob) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"first_name\":\""+firstName+"\",\n" +
                "        \"last_name\":\""+lastName+"\",\n" +
                "        \"tax_number\":\""+taxNumber+"\",\n" +
                "        \"date_of_birth\":\""+dob+"\"    \n" +
                "}  ";
        return req;
    }
}
