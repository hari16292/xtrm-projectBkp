package resources;

public enum APIPath {
    //Authorization API call
    accessToken("oAuth/token"),

    //Bank API call
    linkBankBeneficiary("API/v4/Bank/LinkBankBeneficiary");




    private String path;
    APIPath(String path) {
        this.path = path;
    }
    public String getAPIPath()
    {
        return path;
    }
}
