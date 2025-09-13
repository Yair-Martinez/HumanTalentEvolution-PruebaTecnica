package com.example.demo.application.port.in;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.request.UserRequest;

public interface CreateUserUseCase {

	User CreateUser(UserRequest userRequest);
}
