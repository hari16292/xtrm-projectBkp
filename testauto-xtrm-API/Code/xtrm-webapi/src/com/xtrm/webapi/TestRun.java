package com.xtrm.webapi;

import java.io.IOException;

public class TestRun {

	public static void main(String[] args) throws IOException {
		
		// System.out.println(Checkassertions.checkPattern("D:\\Automation\\xtrm-webapi\\files\\data.json",
		// "D:\\Automation\\xtrm-webapi\\files\\Schema.json"));
//	System.out.println(Checkassertions.checkPattern("D:\\Automation\\xtrm-webapi\\files\\Schema.json","D:\\Automation\\xtrm-webapi\\files\\data.json"));
		// System.out.println(Checkassertions.mail());

	/*	String regex1 = "{\"GetPaymentMethodsResponse\":{\"PaymentMethodResult\":{\"PaymentMethods\":{\"PaymentMethodDetails\":[{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*? Company\"},{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*?\"},{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*?\"},{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*?\"},{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*?\"},{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*?\"},{\"PaymentMethodId\":\".*?\",\"PaymentMethodName\":\".*?\"}]},\"OperationStatus\":{\"Success\":true,\"Errors\":null}}}}";
		String expected = "{\"GetPaymentMethodsResponse\":{\"PaymentMethodResult\":{\"PaymentMethods\":{\"PaymentMethodDetails\":{\"PaymentMethodId\":\"XTR94504\",\"PaymentMethodName\":\"AnyPay Company\"},{\"PaymentMethodId\":\"XTR94502\",\"PaymentMethodName\":\"AnyPay Individual\"},{\"PaymentMethodId\":\"XTR94500\",\"PaymentMethodName\":\"Bank\"},{\"PaymentMethodId\":\"XTR94507\",\"PaymentMethodName\":\"Bank Check\"},{\"PaymentMethodId\":\"XTR94505\",\"PaymentMethodName\":\"Digital Gift Card\"},{\"PaymentMethodId\":\"XTR94503\",\"PaymentMethodName\":\"Prepaid Virtual Debit Card\"},{\"PaymentMethodId\":\"XTR94508\",\"PaymentMethodName\":\"Rapid Transfer\"}},\"OperationStatus\":{\"Success\":true,\"Errors\":null}}}}";
		String response = "{\"GetPaymentMethodsResponse\":{\"PaymentMethodResult\":{\"PaymentMethods\":{\"PaymentMethodDetails\":{\"PaymentMethodId\":\"XTR94504\",\"PaymentMethodName\":\"AnyPay Company\"},{\"PaymentMethodId\":\"XTR94502\",\"PaymentMethodName\":\"AnyPay Individual\"},{\"PaymentMethodId\":\"XTR94500\",\"PaymentMethodName\":\"Bank\"},{\"PaymentMethodId\":\"XTR94507\",\"PaymentMethodName\":\"Bank Check\"},{\"PaymentMethodId\":\"XTR94505\",\"PaymentMethodName\":\"Digital Gift Card\"},{\"PaymentMethodId\":\"XTR94503\",\"PaymentMethodName\":\"Prepaid Virtual Debit Card\"},{\"PaymentMethodId\":\"XTR94508\",\"PaymentMethodName\":\"Rapid Transfer\"}},\"OperationStatus\":{\"Success\":true,\"Errors\":null}}}}";
		sErrorLog += CommonFunctions.compareResponsesJson(regex1, response, expected);
		*/
		/*String compareElement="test";
		sErrorLog += CommonFunctions.compareString(expected,response,compareElement);
		
		if (!sErrorLog.equals("")) {
			System.out.println("Pattern Test " + sErrorLog);
		} else {
			System.out.println("Pattern Test " + sErrorLog + "null");
		}
		*/
		
		//System.out.println(Checkassertions.checkPattern1("D:\\Automation\\jmeter scripts\\jmeter scripts\\Doc\\expectedres.txt","D:\\Automation\\jmeter scripts\\jmeter scripts\\Doc\\schema.txt"));
		//System.out.println(Checkassertions.checkPattern1("D:\\Automation\\jmeter scripts\\jmeter scripts\\Doc\\schema.txt","D:\\Automation\\jmeter scripts\\jmeter scripts\\Doc\\expectedres.txt"));
		String  response="{\"GetUserPaymentMethodsResponse\":{\"UserPaymentMethodResult\":{\"UserPaymentMethods\":{\"UserPaymentMethodDetails\":[{\"UserPaymentMethodId\":\"XTR94502\",\"UserPaymentMethodName\":\"AnyPay Individual\"},{\"UserPaymentMethodId\":\"XTR94500\",\"UserPaymentMethodName\":\"Bank\"},{\"UserPaymentMethodId\":\"XTR94507\",\"UserPaymentMethodName\":\"Bank Check\"},{\"UserPaymentMethodId\":\"XTR94505\",\"UserPaymentMethodName\":\"Digital Gift Card\"},{\"UserPaymentMethodId\":\"XTR94503\",\"UserPaymentMethodName\":\"Prepaid Virtual Debit Card\"},{\"UserPaymentMethodId\":\"XTR94508\",\"UserPaymentMethodName\":\"Rapid Transfer\"},{\"UserPaymentMethodId\":\"XTR94506\",\"UserPaymentMethodName\":\"Reward Link\"}]},\"OperationStatus\":{\"Success\":true,\"Errors\":null}}}}";
		
		String Parameter= "setAssertionForFailure:Checkassertions.PatternandexpectedCheck(D:\\Test%20Automation\\testauto-xtrm-API\\Assertions\\PaymentMethodsPattern.txt,,D:\\Test%20Automation\\testauto-xtrm-API\\Assertions\\PaymentMethodsExpected.txt))\r\n"
				+ "";
		/*System.out.println(Checkassertions.checkPattern(Parameter,response));
		
		
		String Parameter= "setAssertionForFailure:Checkassertions.PatternCheck(\"D:\\\\Automation\\\\jmeter scripts\\\\jmeter scripts\\\\Doc\\\\regex.txt\",\"\",\"D:\\\\Automation\\\\jmeter scripts\\\\jmeter scripts\\\\Doc\\\\expectedres.txt\")";
		*/
		System.out.println(Checkassertions.PatternCheck(Parameter,response));
		
	}





}

