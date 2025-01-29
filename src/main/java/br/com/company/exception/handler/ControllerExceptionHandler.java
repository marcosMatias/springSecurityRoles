package br.com.company.exception.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.exception.BadRequestException;
import br.com.company.exception.BusinessException;
import br.com.company.exception.InvalidJwtException;
import br.com.company.exception.ResourceNotFoundException;
import br.com.company.service.util.UtilService;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


	private final UtilService utilService;

	public ControllerExceptionHandler(UtilService utilService) {
		
		this.utilService = utilService;
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDto handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				             .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		String validationErrors = String.join(", ", errors.values());

        ResponseDto response = new ResponseDto(HttpStatus.BAD_REQUEST.value(),
                                               utilService.retornarLocalDateTimeNowString(),
                                               validationErrors,
                                               request.getDescription(false));

		log.error("Erro de validação: " + validationErrors);
		return response;
	}
	
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDto badRequestException(BadRequestException ex, WebRequest request) {

		ResponseDto message = new ResponseDto(HttpStatus.BAD_REQUEST.value(),
				                              utilService.retornarLocalDateTimeNowString(), 
				                              ex.getMessage(), 
				                              request.getDescription(false));

		log.error("########> Erro em badRequestException: " + String.valueOf(message));
		return message;
	}
	
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseDto resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	
		ResponseDto message = new ResponseDto(HttpStatus.NOT_FOUND.value()
											               ,utilService.retornarLocalDateTimeNowString()
											               ,ex.getMessage()
											               ,request.getDescription(false));
		
		log.error("########> Erro em resourceNotFoundException: "+String.valueOf(message));
		return message;
	}
	
	
	
	
	
	
	
	
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseDto usernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.NOT_FOUND.value()
				                                           ,utilService.retornarLocalDateTimeNowString()
										                   ,"Usuario nao encontrado! "+ex.getMessage()
										                   ,request.getDescription(false));
		
		log.error("########> Erro em usernameNotFoundException: "+String.valueOf(message));
		return message;
	}
	
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseDto internalAuthenticationServiceException(InternalAuthenticationServiceException ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.NOT_FOUND.value()
				                                           ,utilService.retornarLocalDateTimeNowString()
										                   ,"Usuario nao encontrado! "+ex.getMessage()
										                   ,request.getDescription(false));
		
		log.error("########> Erro em internalAuthenticationServiceException: "+String.valueOf(message));
		return message;
	}
	
	

	@ExceptionHandler(JWTVerificationException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseDto jwtTVerificationException(JWTVerificationException ex, WebRequest request) {
		
		
		ResponseDto message = new ResponseDto(HttpStatus.UNAUTHORIZED.value()
													    ,utilService.retornarLocalDateTimeNowString()
													    ,ex.getMessage()
													    ,request.getDescription(false));
		log.error("########> Erro em jwtTVerificationException: " + String.valueOf(message));
		return message;
	}
	
	@ExceptionHandler(InvalidJwtException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseDto invalidJwtException(InvalidJwtException ex, WebRequest request) {
		
		
		ResponseDto message = new ResponseDto(HttpStatus.UNAUTHORIZED.value()
													    ,utilService.retornarLocalDateTimeNowString()
													    ,ex.getMessage()
													    ,request.getDescription(false));
		log.error("########> Erro em invalidJwtException: " + String.valueOf(message));
		return message;
	}
	

	
	@ExceptionHandler(Unauthorized.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseDto unauthorizedException(Unauthorized ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.UNAUTHORIZED.value()
				                                       ,utilService.retornarLocalDateTimeNowString()
				                                       ,ex.getMessage()
				                                       ,request.getDescription(false));

		log.error("########> Erro em unauthorizedException: " + String.valueOf(message));
		return message;
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseDto badCredentialsException(BadCredentialsException ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.UNAUTHORIZED.value()
				                                       ,utilService.retornarLocalDateTimeNowString()
				                                       ,ex.getMessage()
				                                       ,request.getDescription(false));

		log.error("########> Erro em BadCredentialsException: " + String.valueOf(message));
		return message;
	}
	
	
	
	@ExceptionHandler(Forbidden.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ResponseDto forbiddenException(Forbidden ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.FORBIDDEN.value()
				                                       ,utilService.retornarLocalDateTimeNowString()
				                                       ,ex.getMessage()
				                                       ,request.getDescription(false));

		log.error("########> Erro em forbiddenException: " + String.valueOf(message));
		return message;
	}
	
	
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ResponseDto accessDeniedException(AccessDeniedException ex, WebRequest request) {
		
		
		ResponseDto message = new ResponseDto(HttpStatus.FORBIDDEN.value()
													    ,utilService.retornarLocalDateTimeNowString()
													    ,ex.getMessage()
													    ,request.getDescription(false));
		log.error("########> Erro em accessDeniedException: " + String.valueOf(message));
		return message;
	}
	
	
	
	
	
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseDto businessException(BusinessException ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value()
				                                       ,utilService.retornarLocalDateTimeNowString()
				                                       ,ex.getMessage()
				                                       ,request.getDescription(false));

		log.error("########> Erro em businessException: " + String.valueOf(message));
		return message;
	}
	
	
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseDto globalExceptionHandler(Exception ex, WebRequest request) {
		
		ResponseDto message = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value()
				                                           ,utilService.retornarLocalDateTimeNowString()
										                   ,ex.getMessage()
										                   ,request.getDescription(false));
		
		log.error("########> Erro em globalExceptionHandler: "+String.valueOf(message));
		return message;
	}
}
