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

import com.example.demo.application.exception.EmailAlreadyExistsException;
import com.example.demo.application.exception.EmailNotFoundException;
import com.example.demo.application.port.in.CreateUserUseCase;
import com.example.demo.application.port.in.GetAllUsersUseCase;
import com.example.demo.application.port.in.GetUserUseCase;
import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.dto.FailureResponseDto;
import com.example.demo.infrastructure.controller.dto.SuccessResponseDto;
import com.example.demo.infrastructure.controller.dto.UserDto;
import com.example.demo.infrastructure.controller.request.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Se encarga de las operaciones relacionadas con los usuarios.")
public class UsersRestController {

	private final CreateUserUseCase createUserUseCase;
	private final GetUserUseCase getUserUseCase;
	private final GetAllUsersUseCase getAllUsersUseCase;

	@Operation(summary = "Obtener usuarios", description = "Devuelve todos los usuarios registrados o uno específico si se proporciona el parámetro 'email'.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Respuesta satisfactoria - lista de usuarios o único usuario.", 
				content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {UserDto.class, UserDto[].class }))),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado.", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Error interno.", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureResponseDto.class)))})
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
		} catch (EmailNotFoundException ex) {
			FailureResponseDto failureResponse = new FailureResponseDto(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failureResponse);

		} catch (Exception e) {
			FailureResponseDto failureResponse = new FailureResponseDto(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
		}
	}

	@Operation(summary = "Crear usuarios", description = "Crea un nuevo usuario con los datos proporcionados en el cuerpo de la solicitud.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario creado exitosamente.", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Verifique los datos enviados.", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Error interno.", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureResponseDto.class)))})
	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest) {
		try {
			User user = createUserUseCase.CreateUser(userRequest);
			SuccessResponseDto successResponse = null;
			if (user != null) {
				successResponse = new SuccessResponseDto("Usuario registrado exitosamente.");
			}

			return ResponseEntity.ok(successResponse);

		} catch (EmailAlreadyExistsException ex) {
			FailureResponseDto failureResponse = new FailureResponseDto(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failureResponse);

		} catch (Exception e) {
			FailureResponseDto failureResponse = new FailureResponseDto(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
		}

	}
}
