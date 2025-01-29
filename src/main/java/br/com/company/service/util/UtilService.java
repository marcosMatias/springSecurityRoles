package br.com.company.service.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.company.auth.model.dto.ResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UtilService {

private final ObjectMapper objectMapper;
	

	public UtilService(ObjectMapper objectMapper) {

		this.objectMapper = objectMapper;
	}

	public String retornarLocalDateTimeNowString() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return now.format(formatter);
	}

	public LocalDate convertStringToLocalDate(String data) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(data, formatter);
	}

	public void sendErrorResponseFilter(int statusCode, String message, String description, ServletResponse response)throws IOException, ServletException {

		ResponseDto resp = new ResponseDto(statusCode, retornarLocalDateTimeNowString(), message,description);

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setStatus(statusCode);
		httpServletResponse.setContentType("application/json");

	
		objectMapper.writeValue(httpServletResponse.getWriter(), resp);
		
	}
}
