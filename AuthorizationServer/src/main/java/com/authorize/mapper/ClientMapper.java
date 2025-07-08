package com.authorize.mapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import com.authorize.dto.RegisteredClientDTO;
import com.authorize.entity.Client;
import com.authorize.entity.ClientAuthorizationGrantType;
import com.authorize.entity.ClientRedirectUri;
import com.authorize.entity.Scopes;


public class ClientMapper {
	
	public static RegisteredClient toRegisteredClient(Client c)
	{
		RegisteredClient.Builder builder= RegisteredClient.withId(c.getId())
				.clientId(c.getClientId())
				.clientIdIssuedAt(c.getClientIdIssuedAt())
				.clientSecret(c.getClientSecret())
				.clientSecretExpiresAt(c.getClientSecretExpiresAt())
				.clientName(c.getClientName());
				for(ClientAuthorizationGrantType ct:c.getAuthorizationGrantTypes())
					builder.authorizationGrantType(new AuthorizationGrantType(ct.getGrantType()));
				c.getRedirectUris().forEach(r->builder.redirectUri(r.getRedirectUri()));
				c.getClientAuthenticationMethods()
				.forEach(a->builder.authorizationGrantType
						(new AuthorizationGrantType(a.getAuthenticationMethod())));
				for(Scopes s:c.getScopes())
					builder.scope(s.getScope());
				builder.clientSettings(c.getClientSettings());
				builder.tokenSettings(c.getTokenSettings());
						
						
				return builder.build();
	}
	
	
	public static Client toClient(RegisteredClient rc)
	{
		Client.ClientBuilder builder=Client.builder()
				.id(rc.getId())
				.clientId(rc.getClientId())
				.clientSecret(rc.getClientSecret())
				.clientIdIssuedAt(rc.getClientIdIssuedAt())
				.clientSecretExpiresAt(rc.getClientSecretExpiresAt())
				.clientName(rc.getClientName())
				.authorizationGrantTypes(rc.getAuthorizationGrantTypes()
						.stream().map(a->ClientAuthorizationGrantType.builder()
								.grantType(a.getValue()).build())
						.collect(Collectors.toSet()))
				.clientAuthenticationMethods(rc.getClientAuthenticationMethods()
						.stream().map(c->com.authorize.entity.ClientAuthenticationMethod
								.builder().authenticationMethod(c.getValue())
								.build()).collect(Collectors.toSet()))
				.redirectUris(rc.getRedirectUris()
						.stream().map(r->ClientRedirectUri
								.builder().redirectUri(r)
								.build()).collect(Collectors.toSet()))
		.scopes(rc.getScopes()
				.stream().map(s->Scopes
						.builder().scope(s)
						.build()).collect(Collectors.toSet()))
		.clientSettings(rc.getClientSettings())
		.tokenSettings(rc.getTokenSettings());
				
				
		return builder.build();
	}
	
	public static RegisteredClient toRegisteredClient(RegisteredClientDTO rcd)
	{
		RegisteredClient.Builder builder=RegisteredClient.withId(UUID.randomUUID().toString())
										.clientName(rcd.getClientName())
										.clientId(UUID.randomUUID().toString())
										.clientSecret(UUID.randomUUID().toString())
										.clientIdIssuedAt(Instant.now())
										.clientSecretExpiresAt(Instant.now()
												.plus(365, ChronoUnit.DAYS))
										.clientSettings(ClientSettings.builder().build())
										.tokenSettings(TokenSettings.builder().build());
		
		rcd.getAuthorizationGrantTypes().forEach(a->builder.
				authorizationGrantType(new AuthorizationGrantType(a)));
		rcd.getClientAuthenticationMethods().forEach(c->builder.
				clientAuthenticationMethod(new ClientAuthenticationMethod(c)));
		rcd.getScopes().forEach(s->builder.
				scope(s));
		rcd.getRedirectUris().forEach(r->builder.redirectUri(r));
		
	
		return builder.build();
				
	}

}
