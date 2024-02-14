package payloadRequest;


import pojoClasses.updateBankBeneficiary.*;

public class UpdateBankBeneficiary {

	public static ReqObject request(String account_number, String beneficiary_contact_name, String beneficiary_phone_number,
									String address_line1, String address_line2, String city, String region, String postalcode,
									String beneficiary_country_ISO2, String institution_name, String withdraw_type, String currency,
									String SWIFTBIC, String beneficiary_account_number, String routing_number, String remittance_line3,
									String remittance_line4, String bank_details_country_ISO2) {

		ReqBeneficiaryInformation rbci = new ReqBeneficiaryInformation();
		rbci.setAddress_line1(address_line1);
		rbci.setAddress_line2(address_line2);
		rbci.setBeneficiary_contact_name(beneficiary_contact_name);
		rbci.setBeneficiary_phone_number(beneficiary_phone_number);
		rbci.setCity(city);
		rbci.setCountry_ISO2(beneficiary_country_ISO2);
		rbci.setPostalcode(postalcode);
		rbci.setRegion(region);

		ReqBeneficiaryBankInformation rbcbi = new ReqBeneficiaryBankInformation();
		rbcbi.setBeneficiary_account_number(beneficiary_account_number);
		rbcbi.setCountry_ISO2(bank_details_country_ISO2);
		rbcbi.setCurrency(currency);
		rbcbi.setInstitution_name(institution_name);
		rbcbi.setRemittance_line3(remittance_line3);
		rbcbi.setRemittance_line4(remittance_line4);
		rbcbi.setRouting_number(routing_number);
		rbcbi.setSWIFTBIC(SWIFTBIC);
		rbcbi.setWithdraw_type(withdraw_type);

		ReqBeneficiaryDetails rbcd = new ReqBeneficiaryDetails();
		rbcd.setBeneficiary_information(rbci);

		ReqBankDetails rbd = new ReqBankDetails();
		rbd.setBeneficiary_bank_information(rbcbi);

		ReqBeneficiary rb = new ReqBeneficiary();
		rb.setBank_details(rbd);
		rb.setBeneficiary_details(rbcd);

		ReqObject ro = new ReqObject();
		ro.setAccount_number(account_number);
		ro.setBeneficiary(rb);
		
		return ro;

	}
}
