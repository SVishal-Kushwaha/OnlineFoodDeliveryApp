package com.authorize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryAgentDTO {

	private int id;
	private String name;

	private String email; // unique, used for login

	private String password;

	private String phone;

}
