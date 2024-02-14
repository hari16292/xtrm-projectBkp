package payloadRequest;

public class Auth {
	
	public static String request(String clientId, String clientSecret) {
		return "grant_type=password&client_id="+clientId+"&client_secret="+clientSecret;
	}

	public static String refreshTokenRequest(String clientId, String clientSecret, String refreshToken) {
		return "grant_type=refresh_token&refresh_token="+refreshToken+"&client_id="+clientId+"&client_secret="+clientSecret;
	}
}
