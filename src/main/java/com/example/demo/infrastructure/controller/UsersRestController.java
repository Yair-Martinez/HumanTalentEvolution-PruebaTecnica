package com.example.demo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.port.in.CreateUserUseCase;
import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.dto.FailureResponseDto;
import com.example.demo.infrastructure.controller.dto.SuccessResponseDto;
import com.example.demo.infrastructure.controller.request.UserRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersRestController {

	private final CreateUserUseCase createUserUseCase;

	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest) {
		try {
			User user = createUserUseCase.CreateUser(userRequest);
			SuccessResponseDto successResponse = null;
			if (user != null) {
				successResponse = new SuccessResponseDto("Usuario registrado exitosamente.");
			}

			return ResponseEntity.ok(successResponse);
		} catch (Exception e) {
			FailureResponseDto failureResponse = new FailureResponseDto(e.getMessage());
			return ResponseEntity.ok(failureResponse);
		}

	}
}
