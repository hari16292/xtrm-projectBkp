package payloadRequest;

public class CreateWallet {

    public static String request(String PAT, String Currency) {
       String  req = "{\n" +
                "        \"account_number\":\""+PAT+"\",\n" +
                "        \"currency_code\":\""+Currency+"\",\n" +
                "        \"wallet_name\":\"AutoTest "+Currency+"\"  \n" +
                "}  ";
        return req;
    }
}
