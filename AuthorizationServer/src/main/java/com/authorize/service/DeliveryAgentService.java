package com.authorize.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorize.dto.DeliveryAgentDTO;
import com.authorize.dto.UserDTO;
import com.authorize.entity.DeliveryAgent;
import com.authorize.entity.User;
import com.authorize.entity.Users;
import com.authorize.exception.UserNotFoundException;
import com.authorize.exception.UserPresentException;
import com.authorize.repository.DeliveryAgentRepo;
import com.authorize.repository.UserRepo;
import com.authorize.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryAgentService {

	private final DeliveryAgentRepo agentRepo;
	private final UsersRepo usersRepo;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;

	public DeliveryAgentDTO saveDeliveryAgent(DeliveryAgentDTO agentDto) {
		if (usersRepo.existsUsersByEmail(agentDto.getEmail()))
			throw new UserPresentException("this email is assosiated with an account :" + agentDto.getEmail());
		if (agentRepo.existsDeliveryAgentByEmail(agentDto.getEmail()))
			throw new UserPresentException("this email is assosiated with an account :" + agentDto.getEmail());

		DeliveryAgent user = mapper.map(agentDto, DeliveryAgent.class);
		user.setRole("DELIVERYAGENT");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRegisteredAt(Instant.now());
		user.setAvailable(true);
		user = agentRepo.save(user);
		var u = Users.builder().email(agentDto.getEmail()).role("DELEVERYAGENT").build();
		usersRepo.save(u);

		return mapper.map(user, DeliveryAgentDTO.class);

	}

	public DeliveryAgentDTO getDeliveryAgent(String email) {
		var user = agentRepo.findDeliveryAgentByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("their is no user present with this email: "));
		return mapper.map(user, DeliveryAgentDTO.class);
	}

}
