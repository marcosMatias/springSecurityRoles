package br.com.company.auth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.auth.model.dto.RoleDto;
import br.com.company.auth.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.ArraySchema;


@RestController
@RequestMapping("/api/v1/role")
@Tag(name="Role", description="API para gerenciamento de roles")
public class RoleController {

	
	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		
		this.roleService = roleService;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	@Operation(summary = "Cria uma nova Role",method = "POST")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	
	public ResponseDto create( @Valid @RequestBody RoleDto role) {
		
		return roleService.createRole(role);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@ResponseStatus(HttpStatus.OK) 
	@GetMapping
	@Operation(summary = "Retorna uma lista de Roles",method = "GET")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",  array = @ArraySchema( schema = @Schema(implementation = RoleDto.class)))}), 		    	    		
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	
	public List<RoleDto> getAllRoles(){
		
		return roleService.getAllRoles();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{roleName}")
	@ResponseStatus(HttpStatus.OK) 
	@Operation(summary = "Retorna uma Role",method = "GET")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = RoleDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	
	public RoleDto getRole( @PathVariable @Valid String roleName) {
		
		return roleService.getRoleDtoByName(roleName);
	}
	
}
