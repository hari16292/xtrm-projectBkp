package payloadRequest;

public class UpdateWallet {
    public static String request(String PAT, String WalletID, String WalletName) {
        String req = "{\n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"wallet_id\":\""+WalletID+"\",\n" +
                "        \"wallet_name\":\""+WalletName+"\"  \n" +
                "}";
        return req;
    }
}
