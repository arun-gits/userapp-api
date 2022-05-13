package com.ecommerceapp.util;

import com.ecommerceapp.exception.ValidationException;

public class StringUtil {

	public static void isValidString(String input, String message) throws ValidationException {

		if (input.isEmpty() || input.isBlank()) {
			throw new ValidationException(message);

		}

	}

	public static void isValidMail(String mail) throws ValidationException {
		if (!mail.contains("@") || !mail.contains(".") || mail.endsWith(".")) {
			throw new ValidationException("Invalid mail");
		}
	}

	public static void isValidMobile(String mobile) throws ValidationException {
		if (mobile.length() != 10) {
			System.out.println(mobile + " invalid");
			throw new ValidationException("Invalid number !");

		} else if (mobile.length() == 10) {
			for (int i = 0; i < mobile.length(); i++) {
				char ch = mobile.charAt(i);
				if (!Character.isDigit(ch)) {
					throw new ValidationException("Mobile number should contain only numbers!");

				}
			}
		}
	}

	public static void isValidPassword(String password) throws ValidationException {
		if (password.isBlank() || password.length() < 8 || password.length() > 16) {
			throw new ValidationException("Password should contain minimum 8 characters and maximum 16 characters");

		}
	}

	public static boolean isMail(String input) {
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (Character.isAlphabetic(ch) && (input.contains("@") || input.contains("."))) {
				return true;
			}
		}
		return false;
	}
}
