package com.authorize.config;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {

	@Override
	public JwtAuthenticationToken convert(Jwt source) {
		// TODO Auto-generated method stub
		List<String> role = (List<String>) source.getClaims().get("Role");
		
		List<? extends GrantedAuthority> roles=role.stream().map(SimpleGrantedAuthority::new).toList();
		
				;
		JwtAuthenticationToken authenticationObject=new JwtAuthenticationToken(source,roles);
		
		return authenticationObject;
	}

}
