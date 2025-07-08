package com.authorize.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RestraunOwnerDTO {
	
	private int id;
	
	  private String name;

	    private String email;

	    private String phone;

	    private String password; 


}
