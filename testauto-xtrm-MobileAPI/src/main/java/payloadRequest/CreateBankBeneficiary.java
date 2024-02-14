package payloadRequest;

import pojoClasses.createBankBeneficiary.*;

public class CreateBankBeneficiary {

	public static ReqObject request(String account_number, String beneficiary_contact_name, String beneficiary_phone_number,
			String address_line1, String address_line2, String city, String region, String postalcode,
			String beneficiary_information_country_ISO2, String institution_name, String currency, String SWIFTBIC,
			String beneficiary_account_number, String routing_number, String remittance_line3, String remittance_line4,
			String country_ISO2) {

		ReqBeneficiaryBankInformation rbbi = new ReqBeneficiaryBankInformation();
		rbbi.setBeneficiary_account_number(beneficiary_account_number);
		rbbi.setCountry_ISO2(country_ISO2);
		rbbi.setCurrency(currency);
		rbbi.setInstitution_name(institution_name);
		rbbi.setRemittance_line3(remittance_line3);
		rbbi.setRemittance_line4(remittance_line4);
		rbbi.setRouting_number(routing_number);
		rbbi.setSWIFTBIC(SWIFTBIC);
		
		ReqBankDetails rbd = new ReqBankDetails();
		rbd.setBeneficiary_bank_information(rbbi);
		
		ReqBeneficiaryInformation rbi = new ReqBeneficiaryInformation();
		rbi .setAddress_line1(address_line1);
		rbi.setAddress_line2(address_line2);
		rbi.setBeneficiary_contact_name(beneficiary_contact_name);
		rbi.setBeneficiary_phone_number(beneficiary_phone_number);
		rbi.setCity(city);
		rbi.setCountry_ISO2(beneficiary_information_country_ISO2);
		rbi.setPostalcode(postalcode);
		rbi.setRegion(region);
		
		ReqBeneficiaryDetails rbed = new ReqBeneficiaryDetails();
		rbed.setBeneficiary_information(rbi);
		
		ReqBeneficiary rb = new ReqBeneficiary();
		rb.setBank_details(rbd);
		rb.setBeneficiary_details(rbed);
		
		ReqObject ro = new ReqObject();
		ro.setAccount_number(account_number);
		ro.setBeneficiary(rb);
		
		return ro;
	}

}
