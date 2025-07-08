package com.authorize.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authorize.dto.RegisteredClienReponseDTO;
import com.authorize.dto.RegisteredClientDTO;
import com.authorize.mapper.ClientMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class CustomDynamicClientRegistration {

	private final RegisteredClientRepository repo;

	@PostMapping("/register-client")
	public ResponseEntity<RegisteredClienReponseDTO> registerClient(@RequestBody RegisteredClientDTO rcd) {
		RegisteredClient rclient = ClientMapper.toRegisteredClient(rcd);

		repo.save(rclient);
		RegisteredClienReponseDTO response = RegisteredClienReponseDTO.builder().clientId(rclient.getClientId())
				.clientSecret(rclient.getClientSecret()).build();

		return ResponseEntity.ok(response);

	}

	@PostMapping("/demo/{id}")
	public String demo(@PathVariable int id) {
		System.out.println(id);
		return "demo";
	}
}
