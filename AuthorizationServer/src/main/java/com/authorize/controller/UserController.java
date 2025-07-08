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

import com.authorize.dto.UserDTO;
import com.authorize.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user/{email}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserDTO> getUser(@PathVariable String email)
	{
		return ResponseEntity.ok(userService.getUser(email));
	}
	

	@PostMapping("/user")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
	}
	
}
