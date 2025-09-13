package com.example.demo.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.port.in.CreateUserUseCase;
import com.example.demo.application.port.in.GetAllUsersUseCase;
import com.example.demo.application.port.in.GetUserUseCase;
import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.dto.FailureResponseDto;
import com.example.demo.infrastructure.controller.dto.SuccessResponseDto;
import com.example.demo.infrastructure.controller.dto.UserDto;
import com.example.demo.infrastructure.controller.request.UserRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersRestController {

	private final CreateUserUseCase createUserUseCase;
	private final GetUserUseCase getUserUseCase;
	private final GetAllUsersUseCase getAllUsersUseCase;
	
	
	@GetMapping
	public ResponseEntity<?> getUsers(@RequestParam(required = false) String email) {
	    try {
	        if (email != null && !email.isBlank()) {
	            UserDto userDto = getUserUseCase.GetUserByEmail(email);
	            return ResponseEntity.ok(userDto);
	            
	        } else {
	            List<UserDto> users = getAllUsersUseCase.getAllUsers();
	            return ResponseEntity.ok(users);
	            
	        }
	    } catch (Exception e) {
	        FailureResponseDto failureResponse = new FailureResponseDto(e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
	    }
	}

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
			return ResponseEntity.badRequest().body(failureResponse);
		}

	}
}
