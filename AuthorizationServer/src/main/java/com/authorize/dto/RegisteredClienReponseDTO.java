package com.authorize.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RegisteredClienReponseDTO {
	
	private String clientId;
	private String clientSecret;

}
