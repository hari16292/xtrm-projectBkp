package payloadRequest;

public class LinkBankBeneficiary {
    public static String request(String institutionName, String currency,
                                 String SWIFTBIC, String accountNumber, String routingNumber, String countryISO2, String contactName) {
        String req = "{\n" +
                "    \"LinkBankBeneficiary\": {\n" +
                "        \"request\": {\n" +
                "            \"IssuerAccountNumber\": \"SPN19134662\",\n" +
                "            \"UserID\": \"SPN22139313\",\n" +
                "            \"Beneficiary\": {\n" +
                "                \"BeneficiaryDetails\": {\n" +
                "                    \"BeneficiaryInformation\": {\n" +
                "                        \"ContactName\": \""+contactName+"\",\n" +
                "                        \"PhoneNumber\": \"456789456\",\n" +
                "                        \"AddressLine1\": \"Test Addr1\",\n" +
                "                        \"City\": \"Belmont\",\n" +
                "                        \"Region\": \"California\",\n" +
                "                        \"PostalCode\": \"90001\",\n" +
                "                        \"CountryISO2\": \"US\",\n" +
                "                        \"AddressLine2\": \"Test Addr2\"\n" +
                "                    }\n" +
                "                },\n" +
                "                \"BankDetails\": {\n" +
                "                    \"BeneficiaryBankInformation\": {\n" +
                "                        \"InstitutionName\": \""+institutionName+"\",\n" +
                "                        \"Currency\": \""+currency+"\",\n" +
                "                        \"SWIFTBIC\": \""+SWIFTBIC+"\",\n" +
                "                        \"AccountNumber\": \""+accountNumber+"\",\n" +
                "                        \"RoutingNumber\": \""+routingNumber+"\",\n" +
                "                        \"CountryISO2\": \""+countryISO2+"\",\n" +
                "                        \"WithdrawType\": \"\",\n" +
                "                        \"RemittanceLine3\": \"\",\n" +
                "                        \"RemittanceLine4\": \"\",\n" +
                "                        \"RegulatoryFields\": []\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        return req;
    }
}
