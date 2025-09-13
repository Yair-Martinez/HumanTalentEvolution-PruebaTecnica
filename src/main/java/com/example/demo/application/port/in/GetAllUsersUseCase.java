package com.example.demo.application.port.in;

import java.util.List;

import com.example.demo.infrastructure.controller.dto.UserDto;

public interface GetAllUsersUseCase {

	List<UserDto> getAllUsers();
}
