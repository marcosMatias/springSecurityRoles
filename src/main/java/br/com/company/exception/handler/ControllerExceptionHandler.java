package br.com.company.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import br.com.company.exception.ForbiddenException;
import br.com.company.exception.ResourceNotFoundException;
import br.com.company.model.dto.ResponseErrorDto;
import br.com.company.service.UtilService;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	@Autowired
	private UtilService utilService;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseErrorDto resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	
		ResponseErrorDto message = new ResponseErrorDto(HttpStatus.NOT_FOUND.value()
											               ,utilService.retornarLocalDateTimeNowString()
											               ,ex.getMessage()
											               ,request.getDescription(false));
		
		log.error("########> Erro em resourceNotFoundException: "+String.valueOf(message));
		return message;
	}
	
	
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseErrorDto globalExceptionHandler(Exception ex, WebRequest request) {
		
		ResponseErrorDto message = new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value()
				                                           ,utilService.retornarLocalDateTimeNowString()
										                   ,ex.getMessage()
										                   ,request.getDescription(false));
		
		log.error("########> Erro em globalExceptionHandler: "+String.valueOf(message));
		return message;
	}
	
	
	
	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ResponseErrorDto forbiddenException(ForbiddenException ex, WebRequest request) {
		log.error("########> ENTROU AQUI!!!!");
		ResponseErrorDto message = new ResponseErrorDto(HttpStatus.FORBIDDEN.value()
				                                       ,utilService.retornarLocalDateTimeNowString()
				                                       ,ex.getMessage()
				                                       ,request.getDescription(false));

		log.error("########> Erro em forbiddenException: " + String.valueOf(message));
		return message;
	}
	
	


	
}
