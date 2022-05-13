package com.ecommerceapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByMail(String userMail);

	Optional<User> findByMobile(String userMail);

}
