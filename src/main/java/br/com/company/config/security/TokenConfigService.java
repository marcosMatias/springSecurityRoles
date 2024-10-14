package br.com.company.config.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.company.exception.ForbiddenException;
import br.com.company.model.UserModel;
import br.com.company.model.dto.AuthenticateResponseDto;
import br.com.company.service.UtilService;




@Service
public class TokenConfigService {
	
	
	private static final String ISSUER = "neo-api";
	@Value("${neo-api.security.token.secret}")
	private String secret;
	@Autowired
	private UtilService utilService;
	
	public AuthenticateResponseDto gerarTokenService(UserModel usuario) {

		return decodificarTokenJwtService(gerarToken(usuario));
	}
	
	public String getSubject(String tokenJWT)   {
		
		try {  
			
			  var algoritmo = Algorithm.HMAC256(secret);		
			  return JWT.require(algoritmo)
					  .withIssuer(ISSUER)
			          .build()
			          .verify(tokenJWT)
			          .getSubject();
			        
			    
			} 
		catch (JWTVerificationException jwtEx) {
			
		    throw new ForbiddenException("Token JWT inválido ou expirado!");
	
		} catch (Exception ex) {
		    throw new ForbiddenException("Erro ao verificar o token JWT: "+ ex.getMessage());
		}
		
			
		}
	

	
	
	private String gerarToken(UserModel usuario) {
		
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
		    
		  return JWT.create()
		        .withIssuer(ISSUER)
		        .withSubject(usuario.getLogin())		        
		        .withExpiresAt(dataExpiracao())
		        .withClaim("empresa", usuario.getEmpresa())		  
		        .withClaim("date", utilService.retornarLocalDateTimeNowString())		       
		        .sign(algoritmo);
		} catch (JWTCreationException exception){
		   throw new RuntimeException("erro ao gerar token jwt",exception);
		}
		
	}
	
  private AuthenticateResponseDto decodificarTokenJwtService(String tokenJWT) {
		
		try {
			var algoritmo = Algorithm.HMAC256(secret);	
			var authenticateResponseDto = new AuthenticateResponseDto();
			
			DecodedJWT jwt = JWT.require(algoritmo)
								.build()
								.verify(tokenJWT);
			
			Map<String, Claim> claims = jwt.getClaims();
			
			if (claims.containsKey("sub")) {
				authenticateResponseDto.setUsuario(claims.get("sub").asString());

			}	
			
			if (claims.containsKey("exp")) {
				authenticateResponseDto.setExpiresIn(claims.get("exp").asLong());

			}	
			
			if (claims.containsKey("empresa")) {
				authenticateResponseDto.setEmpresa(claims.get("empresa").asString());

			}	
			
			if (claims.containsKey("date")) {
				authenticateResponseDto.setDate(claims.get("date").asString());

			}
			
			authenticateResponseDto.setAcessToken(tokenJWT);
			
			
			return authenticateResponseDto;
			
			
		}catch (JWTVerificationException jwtEx) {
		    throw new ForbiddenException("Retornar Informacaoes - Token JWT inválido ou expirado !");
		}
		
		catch (Exception ex) {
		    throw new ForbiddenException("Retornar Informacaoes - Erro ao verificar o token JWT: "+ ex.getMessage());
		}
		
	}
	
	
	
	private Instant dataExpiracao() {
				
		return  LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
	}
	

	
}
