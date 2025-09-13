package com.example.demo.application.service;

import org.springframework.stereotype.Component;

import com.example.demo.application.port.in.CreateUserUseCase;
import com.example.demo.application.port.out.UserRepositoryPort;
import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.request.UserRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {
	
	private final UserRepositoryPort userRepositoryPort;

	@Override
	public User CreateUser(UserRequest userRequest) {
		return userRepositoryPort.save(userRequest);
	}

}
