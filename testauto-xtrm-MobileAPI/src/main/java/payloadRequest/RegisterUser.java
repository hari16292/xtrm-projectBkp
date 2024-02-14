package payloadRequest;

import pojoClasses.registerUser.ReqRegisterUser;

public class RegisterUser {

    public static ReqRegisterUser request(String PAT, String first_name, String last_name, String email, String password, String mobile_no,
                        String country_calling_flag, String calling_code, String date_of_birth, String device_ip,
                        String device_browser, String device_type, String country, String state, String zip_code) {


        ReqRegisterUser rru = new ReqRegisterUser();
        rru.setAccount_number(PAT);
        rru.setFirst_name(first_name);
        rru.setLast_name(last_name);
        rru.setEmail(email);
        rru.setPassword(password);
        rru.setMobile_no(mobile_no);
        rru.setCountry_calling_flag(country_calling_flag);
        rru.setCalling_code(calling_code);
        rru.setDate_of_birth(date_of_birth);
        rru.setDevice_ip(device_ip);
        rru.setDevice_browser(device_browser);
        rru.setDevice_type(device_type);
        rru.setCountry(country);
        rru.setState(state);
        rru.setZip_code(zip_code);
        return rru;
    }

}
