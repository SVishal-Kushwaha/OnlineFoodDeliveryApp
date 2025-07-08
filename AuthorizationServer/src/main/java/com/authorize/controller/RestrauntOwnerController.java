package com.authorize.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authorize.dto.RestraunOwnerDTO;
import com.authorize.dto.UserDTO;
import com.authorize.service.RestrauntOwnerService;
import com.authorize.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RestrauntOwnerController {
	
	private final RestrauntOwnerService ownerService;
	
	@GetMapping("/restrauntowner/{email}")
	@PreAuthorize("hasRole('RESTRAUNTOWNER')")
	public ResponseEntity<RestraunOwnerDTO> getRestrauntOwner(@PathVariable String email)
	{
		return ResponseEntity.ok(ownerService.getRestrauntOwner(email));
	}
	

	@PostMapping("/restrauntowner")
	public ResponseEntity<RestraunOwnerDTO> saveRestrauntOwner(@RequestBody RestraunOwnerDTO ownerDTO)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.saveRestrauntOwner(ownerDTO));
	}
	
}
