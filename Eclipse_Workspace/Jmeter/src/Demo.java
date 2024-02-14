
public class Demo {

	public static void main(String[] args) {
		String response = "{\r\n" + "    \"GetCompanyTransactionDetailsResponse\": {\r\n"
				+ "        \"GetCompanyTransactionDetailsResult\": {\r\n" + "            \"Field\": [\r\n"
				+ "                [\r\n" + "                    {\r\n"
				+ "                        \"Name\": \"Method\",\r\n"
				+ "                        \"Value\": \"AnyPay\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Type\",\r\n"
				+ "                        \"Value\": \"Credit\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Status\",\r\n"
				+ "                        \"Value\": \"Transaction Completed (Apr 01, 2022)\"\r\n"
				+ "                    },\r\n" + "                    {\r\n"
				+ "                        \"Name\": \"Transferred from\",\r\n"
				+ "                        \"Value\": \"Hari Test (haritestuser1@mailinator.com)\"\r\n"
				+ "                    },\r\n" + "                    {\r\n"
				+ "                        \"Name\": \"Description\",\r\n"
				+ "                        \"Value\": \"Test mass fund\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Credit or Debit Reference\",\r\n"
				+ "                        \"Value\": \"Ref02\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Amount\",\r\n"
				+ "                        \"Value\": \"96.02\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Fees\",\r\n"
				+ "                        \"Value\": \"1.98\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Total Amount\",\r\n"
				+ "                        \"Value\": \"98.00\"\r\n" + "                    },\r\n"
				+ "                    {\r\n" + "                        \"Name\": \"Currency\",\r\n"
				+ "                        \"Value\": \"USD\"\r\n" + "                    }\r\n"
				+ "                ]\r\n" + "            ],\r\n"
				+ "            \"PayDirectTransactionDetails\": [],\r\n" + "            \"OperationStatus\": {\r\n"
				+ "                \"Success\": true,\r\n" + "                \"Errors\": null\r\n"
				+ "            }\r\n" + "        }\r\n" + "    }\r\n" + "}";
		String expMethod = "AnyPay";
		String expType = "Credit";
		String sErrLog = "";

		// System.out.println(actualerror);
		// System.out.println(expmessage);
		// System.out.println(casename + "_" + actualerror);
		String transferMethod = response.substring(response.indexOf("Method\",\"Value\":\""), response.indexOf("}"))
				.replace("Method\",\"Value\":\"", "").replace("\"", "");
		String transferType = response.substring(response.indexOf("Type\",\"Value\":\""), response.indexOf("}"))
				.replace("Type\",\"Value\":\"", "").replace("\"", "");

		if ((transferMethod.contains(expMethod)) && (transferType.contains(expType))) {
			response.contains("Credit or Debit Reference");
			response.contains("Description");
		} else {
			transactionId = "";
			Failure = true;
			sErrLog = "Expected response parameter missing";
			FailureMessage = sErrLog;
		}
	}
}
