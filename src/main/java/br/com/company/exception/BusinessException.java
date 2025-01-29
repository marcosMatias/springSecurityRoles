package br.com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

import java.io.Serial;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class BusinessException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = 1L;
	
	public BusinessException(String msg) {
		super(msg);
	}
}
