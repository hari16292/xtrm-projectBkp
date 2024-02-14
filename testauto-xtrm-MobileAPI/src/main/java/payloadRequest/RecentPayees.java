package payloadRequest;

public class RecentPayees {

    public static String request(String PAT, String currentPage, String itemsPerPage) {
        String req = "{\n" +
                "    \"account_number\":\""+PAT+"\",\n" +
                "    \"current_page\":\""+currentPage+"\",\n" +
                "    \"items_per_page\":\""+itemsPerPage+"\"\n" +
                "} ";
        return req;
    }
}
