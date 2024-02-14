package payloadRequest;

public class UserWithdrawFund {
    public static String request(String account_number, String amount, String fromCurrency, String toCurrency, String paymentMethod, String giftCardEmail, String rewardCardEmail, String linkBankID, String prepaidCardEmail, String cardToken, String sku, String OTP) {
        String req = "{\n" +
                "\t\"account_number\": \"" + account_number + "\",\n" +
                "\t\"amount\": \"" + amount + "\",\n" +
                "\t\"from_currency\": \"" + fromCurrency + "\",\n" +
                "\t\"to_currency\": \"" + toCurrency + "\",\n" +
                "\t\"payment_method_id\": \"" + paymentMethod + "\",\n" +
                "\t\"user_giftcard_email_id\": \"" + giftCardEmail + "\",\n" +
                "\t\"user_rewardcard_email_id\": \"" + rewardCardEmail + "\",\n" +
                "\t\"user_linked_bank_id\": \"" + linkBankID + "\",\n" +
                "\t\"user_prepaid_visa_email_id\": \"" + prepaidCardEmail + "\",\n" +
                "\t\"user_card_token\": \"" + cardToken + "\",\n" +
                "\t\"vSKU\": \"" + sku + "\",\n" +
                "\t\"bAlwaysUseThisEmail\": \"true\",\n" +
                "\t\"otp\": \"" + OTP + "\",\n" +
                "\t\"verification_method\": \"email\"\n" +
                "}";
        return req;
    }
}
