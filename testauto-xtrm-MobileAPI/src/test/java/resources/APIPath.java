package resources;

public enum APIPath {
	
	//Authorization API call
	accessToken("oAuth/token"),

	//Register User API call
	registerUser("api/v1/Signup/RegisterUser"),
	verifyPATwithOTP("api/v1/Signup/VerifyPATwithOTP"),
	resendOTP("api/v1/Signup/ResendOTP"),

	//Bank API call
	getLinkedBankAccounts("api/v1/Bank/GetLinkedBankAccounts"),
	getBeneficiaryDetails("api/v1/Bank/GetBeneficiaryDetails"),
	updateBankBeneficiary("api/v1/Bank/UpdateBankBeneficiary"),
	searchBank("api/v1/Bank/SearchBank"),
	getBankWithdrawTypes("api/v1/Bank/GetBankWithdrawTypes"),
	createBankBeneficiary("api/v1/Bank/CreateBankBeneficiary"),
	deleteBankBeneficiary("api/v1/Bank/DeleteBankBeneficiary"),
	getCurrencyList("api/v1/General/GetCurrencyList"),

	//Link Card API call
	getLinkedCards("api/v1/Settings/GetLinkedCards"),
	linkCard("api/v1/Settings/LinkCard"),
	deleteLinkCard("api/v1/Settings/DeleteLinkCard"),
	editLinkCard("api/v1/Settings/EditLinkCard"),

	//Link Other API call
	getOtherLinkAccounts("api/v1/Settings/GetOtherLinkAccounts"),
	getDigitalGiftCards("api/v1/GiftCard/GetDigitalGiftCards"),
	getPrepaidGiftCards("api/v1/GiftCard/GetPrepaidGiftCards"),
	getRewardGiftCards("api/v1/GiftCard/GetRewardGiftCards"),

	//General
	getBuyingCurrencyList("api/v1/General/GetBuyingCurrencyList"),
	getCountryList("api/v1/General/GetCountryList"),
	getStateList("api/v1/General/GetStateList"),
	getPaymentMethods("api/v1/Payment/GetPaymentMethods"),
	getFee("api/v1/Payment/GetFee"),

	//Profile
	getUserPersonal("api/v1/Profile/GetUserPersonal"),
	updateUserPersonalProfile("api/v1/Profile/UpdateUserPersonalProfile"),
	getUserContact("api/v1/Profile/GetUserContact"),
	updateUserContactInfo("api/v1/Profile/UpdateUserContactInfo"),
	getUserEmployer("api/v1/Profile/GetUserEmployer"),
	getEmployers("api/v1/General/GetEmployers"),
	deleteUserEmployerInfo("api/v1/Profile/DeleteUserEmployerInfo"),
	saveUserEmployerInfo("api/v1/Profile/SaveUserEmployerInfo"),

	//Devices
	getDevices("api/v1/Devices/GetDevices"),

	//Wallets API call
	getUserWallets("api/v1/Wallet/GetUserWallets"),
	getAvailableCurrencies("api/v1/Wallet/GetAvailableCurrencies"),
	createWallet("api/v1/Wallet/CreateWallet"),
	updateWallet("api/v1/Wallet/UpdateWallet"),
	getUserWalletsTransactions("api/v1/Wallet/GetUserWalletsTransactions"),
	getUserWalletTransactionDetails("api/v1/Wallet/GetUserWalletTransactionDetails"),

	//Send API call
	fundTransfer("api/v1/Payment/FundTransfer"),
	simpleSendSearch("api/v1/Payment/SimpleSendSearch"),
	recentPayees("api/v1/Payment/Recentpayees"),

	//Transfer API call
	userWithdrawFund("api/v1/Fund/UserWithdrawFund"),
	getUserPaymentMethods("api/v1/Payment/GetUserPaymentMethods"),
	getPrepaidDebitCardAvailableCurrencies("api/v1/GiftCard/GetPrepaidDebitCardAvailableCurrencies"),
	getDigitalGiftCardAvailableCurrencies("api/v1/GiftCard/GetDigitalGiftCardAvailableCurrencies"),

	//Exchange
	getExchangeRate("api/v1/Payment/GetExchangeRate"),
	getSellingExchangeRate("api/v1/Payment/GetSellingExchangeRate"),
	bookCurrencyExchange("api/v1/Payment/BookCurrencyExchange"),

	//Claims
	getClaims("api/v1/Claims/GetClaims"),
	getClaimDetails("api/v1/Claims/GetClaimDetails"),
	changeClaimStatus("api/v1/Claims/ChangeClaimStatus"),

	//Settings
	getUserAccountInfo("api/v1/Profile/GetUserAccountInfo"),
	changePassword("api/v1/Settings/ChangePassword"),
	getOTPForgotPassword("api/v1/Settings/GetOTPForgotPassword"),
	validateOTPForgotPassword("api/v1/Settings/ValidateOTPForgotPassword"),
	resetPassword("api/v1/Settings/ResetPassword"),
	getUserEmailOTP("api/v1/Profile/GetUserEmailOTP"),
	updateUserEmail("api/v1/Profile/UpdateUserEmail"),
	getUserMobileOTP("api/v1/Profile/GetUserMobileOTP"),
	updateUserMobile("api/v1/Profile/UpdateUserMobile");

	private String path;
	
	APIPath(String path) {
		this.path = path;
	}
	public String getAPIPath()
	{
		return path;
	}

}
