package com.example.demo.application.port.in;

import com.example.demo.infrastructure.controller.dto.UserDto;

public interface GetUserUseCase {

	UserDto GetUserByEmail(String email) throws Exception;
}
