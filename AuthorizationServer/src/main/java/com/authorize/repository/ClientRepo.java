package com.authorize.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import com.authorize.entity.Client;

public interface ClientRepo extends JpaRepository<Client, String> {

	  @Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
	public Optional<Client> findByClientId(String clientId);

}
