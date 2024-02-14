package payloadRequest;

public class GetBeneficiaryDetails {
	
	public static String request(String PAT, String BeneficiaryID) {
		String req = "{\r\n"
				+ "    \"account_number\":  \""+PAT+"\",\r\n"
				+ "    \"benificiary_id\":  \""+BeneficiaryID+"\"\r\n"
				+ "}  ";
		return req;
	}
}
