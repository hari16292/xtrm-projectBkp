package payloadRequest;

public class GetClaimDetails {
    public static String request(String PAT, String claimId) {
        String req = "{\n" +
                "     \"account_number\":\"" + PAT + "\",\n" +
                "     \"claim_id\":\"" + claimId + "\"\n" +
                "} ";
        return req;
    }
}
