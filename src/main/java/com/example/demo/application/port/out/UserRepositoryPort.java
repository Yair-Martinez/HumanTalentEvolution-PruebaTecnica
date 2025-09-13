package com.example.demo.application.port.out;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.request.UserRequest;

public interface UserRepositoryPort {

	User save(UserRequest userRequest);
}
