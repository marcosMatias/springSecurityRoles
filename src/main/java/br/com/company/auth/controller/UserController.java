package br.com.company.auth.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.auth.model.dto.AuthenticateResponseDto;
import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.auth.model.dto.UserDto;
import br.com.company.auth.model.dto.UserLoginDto;
import br.com.company.auth.model.dto.UserUpdatePasswordDto;
import br.com.company.auth.model.dto.UserUpdatePermissionDto;
import br.com.company.auth.model.dto.UserUpdateStatusDto;
import br.com.company.auth.model.dto.UserViewDto;
import br.com.company.auth.service.UserPermissionService;
import br.com.company.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name="User")
public class UserController {
	

	private final UserService userService;
	private final UserPermissionService userPermissionService;
	
	public UserController(UserService userService,UserPermissionService userPermissionService) {

		this.userService = userService;
		this.userPermissionService = userPermissionService;
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	@Operation(summary = "Cria um novo usuário",method = "POST")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = AuthenticateResponseDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	   
	public ResponseDto create(@Valid @RequestBody UserDto user) {
		
		return userService.createUser(user);
	}
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/password")
	@ResponseStatus(HttpStatus.OK) 
	@Operation(summary = "Atualiza a senha do usuário",method = "PUT")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = AuthenticateResponseDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	 
	public ResponseDto updatePassword(@Valid @RequestBody UserUpdatePasswordDto userPassword) {
		
		return userService.updatePassword(userPassword);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PutMapping("/update/status")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Atualiza o status do usuário",method = "PUT")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = AuthenticateResponseDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	 
	public ResponseDto updateStatus(@Valid @RequestBody UserUpdateStatusDto userStatus) {
		
		return userService.updateStatus(userStatus);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Retorna todos os usuários",method = "GET")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = UserViewDto.class)))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	 
	public List<UserViewDto> getAllUsers(){
		
		return userService.getAllUsers();
		
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@GetMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Retorna o usuário pelo login",method = "GET")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = UserViewDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	
	public UserViewDto getUser(@Valid @RequestBody UserLoginDto userLoginDto) {
		
		return userService.getUser(userLoginDto);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/role")
	@ResponseStatus(HttpStatus.CREATED) 
	@Operation(summary = "Adiciona uma nova Role ao Usuário",method = "POST")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	   
	public ResponseDto insertPermission(@Valid @RequestBody UserUpdatePermissionDto user) {
		
		return userPermissionService.insertPermission(user);
	}
	
}
