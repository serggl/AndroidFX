package com.anjlab.android.fx;

import java.util.regex.Pattern;

public class ValidationTools {

	public static boolean validateEmail(String email)
	{
		return Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(email).matches();
	}

	public static boolean validateUSPhone(String phone)
	{
		return Pattern.compile("[0-9]{10,12}").matcher(phone).matches();
	}

	public static boolean validateLocalPhone(String phone, String localCallCode, int minimumLength, int maximumLength)
	{	
		return Pattern.compile(String.format("%s[0-9]{%d,%d}", localCallCode, minimumLength, maximumLength)).matcher(phone).matches();
	}
	
	public static boolean isEmptyString(String value) {
		return value == null || value.trim().length() == 0;
	}
	
	public static boolean isNonEmptyString(String value) {
		return !isEmptyString(value);
	}

}
