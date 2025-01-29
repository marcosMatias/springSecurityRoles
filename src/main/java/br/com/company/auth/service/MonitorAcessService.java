package br.com.company.auth.service;


import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.company.auth.model.MonitorAcessModel;
import br.com.company.auth.model.dto.MonitorAcessDto;
import br.com.company.auth.repository.MonitorAcessRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MonitorAcessService {
	
	
	private final ModelMapper modelMapper;
	private final MonitorAcessRepository monitorAcessRepository;
	
	
	public MonitorAcessService(ModelMapper modelMapper,MonitorAcessRepository monitorAcessRepository) {
		
		this.modelMapper = modelMapper;
		this.monitorAcessRepository = monitorAcessRepository;
		
	}

	
	public void logAudit(String username, HttpServletRequest request) {

		MonitorAcessDto monitorAcessDto = new MonitorAcessDto();
		
		monitorAcessDto.setLogin(username);
		monitorAcessDto.setAcessDate(LocalDateTime.now());
		monitorAcessDto.setIpAdress(request.getRemoteAddr());
		monitorAcessDto.setHttpMethod(request.getMethod());
		monitorAcessDto.setUri(request.getRequestURI());
		
		System.out.println("acessDto {}" + monitorAcessDto);

		try {
			saveData(monitorAcessDto);

		} catch (Exception e) {
			log.error("Erro ao salvar o log de auditoria: {}", e.getMessage());
		}

	}
	
	@Transactional
	private void saveData(MonitorAcessDto monitorAcessDto) {
		log.info("grava dados de acesso{}"+monitorAcessDto);
		monitorAcessRepository.save(toMonitorAcessModel(monitorAcessDto));
		
	}
	
	
	private MonitorAcessModel toMonitorAcessModel(MonitorAcessDto monitorAcessDto) {
		return modelMapper.map(monitorAcessDto, MonitorAcessModel.class);
	}
	
}
