package com.authorize.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorize.dto.UserDTO;
import com.authorize.entity.User;
import com.authorize.entity.Users;
import com.authorize.exception.UserNotFoundException;
import com.authorize.exception.UserPresentException;
import com.authorize.repository.UserRepo;
import com.authorize.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepo userRepo;
	private final UsersRepo usersRepo;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserDTO saveUser(UserDTO userDto)
	{
		if(usersRepo.existsUsersByEmail(userDto.getEmail()))
			throw new UserPresentException("this email is assosiated with an account :"+userDto.getEmail());
		if(userRepo.existsUserByEmail(userDto.getEmail()))
		throw new UserPresentException("this email is assosiated with an account :"+userDto.getEmail());
		
		User user=mapper.map(userDto,User.class);
		user.setRole("USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	
		user=userRepo.save(user);
		var u=Users.builder().email(userDto.getEmail())
				.role("USER").build();
				usersRepo.save(u);
				
		return mapper.map(user, UserDTO.class);
	
	}
	public UserDTO getUser(String email)
	{
		var user=userRepo.findUserByEmail(email).orElseThrow(
				()->new UserNotFoundException("their is no user present with this email: "));
		return mapper.map(user, UserDTO.class);
	}

}
