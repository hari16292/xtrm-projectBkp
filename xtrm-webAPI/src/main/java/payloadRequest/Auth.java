package payloadRequest;

public class Auth {
	
	public static String request(String clientId, String clientSecret) {
		String request = "grant_type=password&client_id="+clientId+"&client_secret="+clientSecret;
		return request;
	}

}
