package com.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapp.dto.Message;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.exception.ServiceException;
import com.ecommerceapp.exception.ValidationException;
import com.ecommerceapp.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {

		try {
			userService.register(userDTO);
			Message message = new Message("Registered Successfully");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

		try {
//			Message message = new Message("Logged in successfully");
			return new ResponseEntity<>(userService.login(userDTO), HttpStatus.OK);
		} catch (ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("*")
	public ResponseEntity<?> test(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}

}
