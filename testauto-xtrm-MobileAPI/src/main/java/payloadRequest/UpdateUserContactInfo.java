package payloadRequest;

public class UpdateUserContactInfo {
    public static String request(String PAT, String addr1, String addr2, String aptNo, String city, String country, String state, String zipcode) {
        String req = "{\n" +
                "        \"account_number\":\"" + PAT + "\",\n" +
                "        \"address_line_1\":\"" + addr1 + "\",\n" +
                "        \"address_line_2\":\"" + addr2 + "\",\n" +
                "        \"appartment_number\":\"" + aptNo + "\",\n" +
                "        \"city\":\"" + city + "\",\n" +
                "        \"country\":\"" + country + "\",\n" +
                "        \"state\":\"" + state + "\",\n" +
                "        \"zipcode\":\"" + zipcode + "\"        \n" +
                "}";
        return req;
    }
}
