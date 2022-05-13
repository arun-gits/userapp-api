package com.ecommerceapp.converter;

import org.springframework.stereotype.Component;

import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.model.User;

@Component
public class UserConverter {

	public User toModel(UserDTO userDTO) {

		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setMail(userDTO.getMail());
		user.setMobile(userDTO.getMobile());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setStatus(userDTO.getStatus());
		return user;
	}

	public UserDTO toDTO(User user) {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setMail(user.getMail());
		userDTO.setMobile(user.getMobile());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		userDTO.setStatus(user.getStatus());
		return userDTO;
	}
}
