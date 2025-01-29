package br.com.company.auth.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.company.auth.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, BigDecimal> {

	Optional<RoleModel> findByNameIgnoreCase(String roleName);

}
