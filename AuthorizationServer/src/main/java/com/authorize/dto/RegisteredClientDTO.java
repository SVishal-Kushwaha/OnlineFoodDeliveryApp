package com.authorize.dto;

import java.util.HashSet;
import java.util.Set;

import com.authorize.entity.ClientAuthenticationMethod;
import com.authorize.entity.ClientAuthorizationGrantType;
import com.authorize.entity.ClientRedirectUri;
import com.authorize.entity.Scopes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredClientDTO {
	private String clientName;
	private Set<String> clientAuthenticationMethods=new HashSet();
	private Set<String> authorizationGrantTypes=new HashSet();
	private Set<String> redirectUris=new HashSet();
	private Set<String> scopes=new HashSet();

}
