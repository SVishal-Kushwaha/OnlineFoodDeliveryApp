package com.authorize.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Service;

import com.authorize.entity.DeliveryAgent;
import com.authorize.entity.RestrauntOwner;
import com.authorize.entity.User;
import com.authorize.principle.DeliveryAgentPrinciple;
import com.authorize.principle.RestrauntOwnerPrinciple;
import com.authorize.principle.UserPrinciple;
import com.authorize.repository.DeliveryAgentRepo;
import com.authorize.repository.RestrauntOwnerRepo;
import com.authorize.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;
	private final RestrauntOwnerRepo ownerRepo;
	private final DeliveryAgentRepo agentRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (userRepo.existsUserByEmail(username)) {
			Optional<User> user = userRepo.findUserByEmail(username);
			return new UserPrinciple(user.get());
		} else if (ownerRepo.existsRestrauntOwnerByEmail(username)) {
			Optional<RestrauntOwner> user = ownerRepo.findRestrauntOwnerByEmail(username);
			return new RestrauntOwnerPrinciple(user.get());
		} else if (agentRepo.existsDeliveryAgentByEmail(username)) {
			Optional<DeliveryAgent> user = agentRepo.findDeliveryAgentByEmail(username);
			return new DeliveryAgentPrinciple(user.get());
		}
		throw new UsernameNotFoundException("there is no any account with this user name: " + username);
	}

}
