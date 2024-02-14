package payloadRequest;

public class GetBankWithdrawTypes {

	public static String request(String countryCode) {
		String req = "{\r\n"
				+ "   \"country_ISO2\":\""+countryCode+"\"\r\n"
				+ "}";
		return req;
	}
}
