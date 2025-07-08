package com.authorize.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorize.entity.DeliveryAgent;
import com.authorize.entity.RestrauntOwner;
import com.authorize.entity.User;

public interface DeliveryAgentRepo extends JpaRepository<DeliveryAgent, Integer> {
	public boolean existsDeliveryAgentByEmail(String email);

	public Optional<DeliveryAgent> findDeliveryAgentByEmail(String email);
}
