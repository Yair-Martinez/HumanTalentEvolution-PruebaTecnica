package com.example.demo.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.application.port.in.CreateUserUseCase;
import com.example.demo.application.port.in.GetAllUsersUseCase;
import com.example.demo.application.port.in.GetUserUseCase;
import com.example.demo.application.port.out.UserRepositoryPort;
import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.dto.UserDto;
import com.example.demo.infrastructure.controller.request.UserRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, GetUserUseCase, GetAllUsersUseCase {
	
	private final UserRepositoryPort userRepositoryPort;

	@Override
	public User CreateUser(UserRequest userRequest) throws Exception {
		UserDto user = userRepositoryPort.findByEmail(userRequest.getEmail());
		if (user != null) throw new Exception("El email ya está registrado.");
		
		return userRepositoryPort.save(userRequest);
	}

	@Override
	public UserDto GetUserByEmail(String email) throws Exception {
		UserDto user = userRepositoryPort.findByEmail(email);
		if (user == null) throw new Exception("El email " + email + " no existe");
		
		return user;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> usersDto = userRepositoryPort.findAllUsers();
		return usersDto;
	}

}
