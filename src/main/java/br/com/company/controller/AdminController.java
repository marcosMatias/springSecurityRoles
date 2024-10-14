package br.com.company.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@PostMapping
	public String acessarRotaAdmin() {
		return "Acesso Concedido para Admin";
	}

}
