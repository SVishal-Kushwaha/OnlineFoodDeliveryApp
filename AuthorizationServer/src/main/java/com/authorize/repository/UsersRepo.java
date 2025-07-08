package com.authorize.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorize.entity.Users;

public interface UsersRepo extends JpaRepository<Users, String> {
	
	public boolean existsUsersByEmail(String email);

}
