package com.authorize.entity;

import java.time.Instant;
import java.util.Set;

import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientAuthenticationMethod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String authenticationMethod;
	public ClientAuthenticationMethod(String authenticationMethod) {
		super();
		this.authenticationMethod = authenticationMethod;
	}
	
}
