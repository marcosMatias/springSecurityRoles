package br.com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class JWTCustomVerificationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
	
	
	public JWTCustomVerificationException(String message) {
		
		super(message);
		
	}

}
