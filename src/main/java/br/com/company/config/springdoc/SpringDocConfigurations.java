package br.com.company.config.springdoc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringDocConfigurations {

	
	@Value("${server.port}")
    private String serverPort;


	@Bean
	public OpenAPI openApi() {
		return new OpenAPI().components(new Components().addSecuritySchemes("Bearer Authentication", new SecurityScheme().type(SecurityScheme.Type.HTTP)
																														 .scheme("bearer")
																														 .bearerFormat("JWT")
																														 .in(SecurityScheme.In.HEADER)
																														 .name("SecurityScheme.Type.HTTP")))
														.addSecurityItem(new SecurityRequirement()
																			.addList("Bearer Authentication"))
																			
														.info(new Info().title("Spring Security Roles")
								                				        .description("API segura com controle de acesso baseado em roles.")								                				        
								                				        .contact(new Contact()
								                                        .name("Marcos")
								                                        .email("marcos_klony@hotmail.com"))
								                				        .version("1.0.1")								                				        								                				        
														.license(new License()
									                            .name("Copyright © 2025 My Company")
									                            .url("https://www.mycompany.com.br/")))
														.servers(retornarServidores())
														;
		
	}


	   
	private List<Server> retornarServidores() {

		Server server = new Server();
		server.setDescription("Servidor de Desenvolvimento");
		server.setUrl("http://localhost:"+serverPort+"/");

		List<Server> servidores = new ArrayList<>();
		servidores.add(server);

		return servidores;
		
	}

}
