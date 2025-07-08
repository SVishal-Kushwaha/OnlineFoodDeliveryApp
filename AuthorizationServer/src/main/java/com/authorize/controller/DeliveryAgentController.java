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

import com.authorize.dto.DeliveryAgentDTO;
import com.authorize.dto.RestraunOwnerDTO;
import com.authorize.dto.UserDTO;
import com.authorize.service.DeliveryAgentService;
import com.authorize.service.RestrauntOwnerService;
import com.authorize.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class DeliveryAgentController {
	
	private final DeliveryAgentService agentService;
	
	@GetMapping("/deliveryagent/{email}")
	@PreAuthorize("hasRole('DELIVERYAGENT')")
	public ResponseEntity<DeliveryAgentDTO> getDeliveryAgent(@PathVariable String email)
	{
		return ResponseEntity.ok(agentService.getDeliveryAgent(email));
	}
	

	@PostMapping("/deliveryagent")
	public ResponseEntity<DeliveryAgentDTO> saveDeliveryAgent(@RequestBody DeliveryAgentDTO agentDTO)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(agentService.saveDeliveryAgent(agentDTO));
	}
	
}
