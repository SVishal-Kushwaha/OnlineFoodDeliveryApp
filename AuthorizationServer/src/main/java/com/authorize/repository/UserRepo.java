package com.authorize.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorize.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public boolean existsUserByEmail(String email);
	public Optional<User> findUserByEmail(String email);
}
