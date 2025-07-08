package com.authorize.entity;

import java.time.Instant;
import java.util.Set;

import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Client {
	@Id
	private String id;
	private String clientId;
	private Instant clientIdIssuedAt;
	private String clientSecret;
	private Instant clientSecretExpiresAt;
	private String clientName;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Set<ClientAuthorizationGrantType> authorizationGrantTypes;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Set<ClientRedirectUri> redirectUris;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id",nullable = false)
	private Set<Scopes> scopes;
	 @Lob
	private ClientSettings clientSettings;
	 @Lob
	private TokenSettings tokenSettings;

}
