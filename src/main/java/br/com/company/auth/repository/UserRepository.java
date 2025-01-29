package br.com.company.auth.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.company.auth.model.UserModel;



@Repository
public interface UserRepository extends JpaRepository<UserModel, BigDecimal>{
	
	UserDetails findByLoginIgnoreCase(String login);

	Optional<UserModel> findByEmailIgnoreCase(String email);
}
