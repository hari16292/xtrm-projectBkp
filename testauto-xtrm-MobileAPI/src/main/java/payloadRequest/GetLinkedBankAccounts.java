package payloadRequest;

public class GetLinkedBankAccounts {
	
	public static String request(String PAT) {
		String req  = "{\r\n"
				+ "	\"account_number\": \""+PAT+"\"\r\n"
				+ "}";
		return req;
	}

}
