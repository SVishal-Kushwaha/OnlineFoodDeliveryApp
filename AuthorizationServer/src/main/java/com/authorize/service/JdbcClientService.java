package com.authorize.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authorize.entity.Client;
import com.authorize.mapper.ClientMapper;
import com.authorize.repository.ClientRepo;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JdbcClientService implements RegisteredClientRepository {
	
	private final ClientRepo repo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void save(RegisteredClient registeredClient) {
		Client c=ClientMapper.toClient(registeredClient);
		c.setClientSecret(passwordEncoder.encode(c.getClientSecret()));
		repo.save(c);
		
	}

	@Override
	public RegisteredClient findById(String id) {
		// TODO Auto-generated method stu
		 var client =
			        repo.findById(id)
			            .orElseThrow();
		return ClientMapper.toRegisteredClient(client);
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		// TODO Auto-generated method stub
		 var client =
			        repo.findByClientId(clientId)
			            .orElseThrow();
		 System.out.println(client);
		return ClientMapper.toRegisteredClient(client);
	}

}
