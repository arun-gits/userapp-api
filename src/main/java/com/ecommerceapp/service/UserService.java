package com.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerceapp.converter.UserConverter;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.exception.ServiceException;
import com.ecommerceapp.exception.ValidationException;
import com.ecommerceapp.model.User;
import com.ecommerceapp.repository.UserRepository;
import com.ecommerceapp.validation.UserValidator;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConverter userConverter;

	@Autowired
	UserValidator userValidator;

	public void register(UserDTO userDTO) throws ValidationException, ServiceException {

		User user = userConverter.toModel(userDTO);
		userValidator.validateRegisterUserDetails(user);
		try {
			Optional<User> validateMail = userRepository.findByMail(user.getMail());
			Optional<User> validateMobile = userRepository.findByMobile(user.getMobile());
			if (validateMail.isPresent() || validateMobile.isPresent()) {
				throw new ValidationException("You're an existing user");
			}
			userRepository.save(user);
		} catch (DataAccessException e) {
			throw new ServiceException ("Cannot connect to server, try again later");
		}
	}

	public UserDTO login(UserDTO userDTO) throws ValidationException, ServiceException {
		UserDTO logUser = new UserDTO();
		User user = userConverter.toModel(userDTO);
		userValidator.validateLoginUserMail(user);
		try {
			User loginUser = userRepository.findByMail(user.getMail())
					.orElseThrow(() -> new ValidationException("You're not a registered user"));
			if (!loginUser.getPassword().equals(userDTO.getPassword())) {
				throw new ValidationException("Invalid Login Credentials");

			}
			logUser = userConverter.toDTO(loginUser);
		} catch (DataAccessException e) {
			throw new ServiceException ("Cannot connect to server, try again later");
		}
		logUser.setPassword(null);
		return logUser;
	}
	
	public List<UserDTO> listUsers(){
		List<User> listOfUsers = userRepository.findAll();
		List<UserDTO> listOfUsersDTO = userConverter.toDTOList(listOfUsers);
		return listOfUsersDTO;
	}
	
	public void deleteUser(int id) throws ValidationException{
		User user = userRepository.findById(id)
				.orElseThrow(()->new ValidationException("Invalid user id"));
//		Optional<User> user = userRepository.findById(id);

		userRepository.delete(user);
	}
	
	public void makeAsAdmin(int id) throws ValidationException{
		User user = userRepository.findById(id)
				.orElseThrow(()->new ValidationException("Invalid user id"));
		user.setRole("admin");
		userRepository.save(user);
	}
}
