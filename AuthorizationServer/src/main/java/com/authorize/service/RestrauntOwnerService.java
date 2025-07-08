package com.authorize.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorize.dto.RestraunOwnerDTO;
import com.authorize.dto.UserDTO;
import com.authorize.entity.RestrauntOwner;
import com.authorize.entity.User;
import com.authorize.entity.Users;
import com.authorize.exception.UserNotFoundException;
import com.authorize.exception.UserPresentException;
import com.authorize.repository.RestrauntOwnerRepo;
import com.authorize.repository.UserRepo;
import com.authorize.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestrauntOwnerService {

	private final RestrauntOwnerRepo ownerRepo;
	private final UsersRepo usersRepo;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;

	public RestraunOwnerDTO saveRestrauntOwner(RestraunOwnerDTO ownerDTO) {
		if (usersRepo.existsUsersByEmail(ownerDTO.getEmail()))
			throw new UserPresentException("this email is assosiated with an account :" + ownerDTO.getEmail());
		if (ownerRepo.existsRestrauntOwnerByEmail(ownerDTO.getEmail()))
			throw new UserPresentException("this email is assosiated with an account :" + ownerDTO.getEmail());

		RestrauntOwner owner = mapper.map(ownerDTO, RestrauntOwner.class);
		owner.setRole("RESTRAUNTOWNER");
		owner.setPassword(passwordEncoder.encode(owner.getPassword()));

		owner = ownerRepo.save(owner);
		var u = Users.builder().email(ownerDTO.getEmail()).role("RESTRAUNTOWNER").build();
		usersRepo.save(u);

		return mapper.map(owner, RestraunOwnerDTO.class);

	}

	public RestraunOwnerDTO getRestrauntOwner(String email) {
		RestrauntOwner owner = ownerRepo.findRestrauntOwnerByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("their is no user present with this email: "+email));
		return mapper.map(owner, RestraunOwnerDTO.class);
	}

}
