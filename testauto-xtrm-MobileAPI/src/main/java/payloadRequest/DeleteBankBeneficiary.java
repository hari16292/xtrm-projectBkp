package payloadRequest;

public class DeleteBankBeneficiary {
    public static String request(String PAT, String BeneficiaryID) {
        String req = "{  \n" +
                "   \"account_number\":\""+PAT+" \",\n" +
                "   \"beneficiary_id\":\""+BeneficiaryID+" \"\n" +
                "}";
        return req;
    }
}
