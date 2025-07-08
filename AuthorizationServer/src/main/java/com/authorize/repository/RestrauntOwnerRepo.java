package com.authorize.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorize.entity.RestrauntOwner;
import com.authorize.entity.User;

public interface RestrauntOwnerRepo extends JpaRepository<RestrauntOwner, Integer> {
	public boolean existsRestrauntOwnerByEmail(String email);
	public Optional<RestrauntOwner> findRestrauntOwnerByEmail(String email);
}
