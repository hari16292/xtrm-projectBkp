package payloadRequest;

public class GetClaims {
    public static String request(String PAT, String status, String currentPage, String itemsPerPage) {
        String req = "{\n" +
                "     \"account_number\":\"" + PAT + "\",\n" +
                "     \"claim_status\":\"" + status + "\",\n" +
                "     \"current_page\":\"" + currentPage + "\",\n" +
                "     \"items_per_page\":\"" + itemsPerPage + "\"\n" +
                "}   ";
        return req;
    }
}
