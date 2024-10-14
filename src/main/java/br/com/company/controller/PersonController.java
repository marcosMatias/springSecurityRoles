package br.com.company.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	
	@PostMapping
	public String acessarRotaPerson() {
		return "Acesso Concedido para Person";
	}

}
