package com.ecommerceapp.validation;

import org.springframework.stereotype.Component;

import com.ecommerceapp.exception.ValidationException;
import com.ecommerceapp.model.User;
import com.ecommerceapp.util.StringUtil;

@Component
public class UserValidator {

	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */

	public void validateRegisterUserDetails(User user) throws ValidationException {
		StringUtil.isValidString(user.getName(), "Invalid name");
		StringUtil.isValidMail(user.getMail());
		StringUtil.isValidPassword(user.getPassword());
		StringUtil.isValidMobile(user.getMobile());
	}

	public void validateLoginUserMail(User user) throws ValidationException {

		StringUtil.isValidMail(user.getMail());

		StringUtil.isValidPassword(user.getPassword());
	}

	public void validateLoginUserMobile(User user) throws ValidationException {

//		StringUtil.isValidMobile(user.getData());

		StringUtil.isValidPassword(user.getPassword());
	}

}
