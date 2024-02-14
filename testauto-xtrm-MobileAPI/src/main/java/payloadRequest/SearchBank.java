package payloadRequest;

public class SearchBank {
	
	public static String request(String bankName, String countryCode) {
		String req = "{\r\n"
				+ "        \"bank_name\":\""+bankName+"\",\r\n"
				+ "        \"country_ISO2\":\""+countryCode+"\"\r\n"
				+ "}";
		return req;
	}

}
