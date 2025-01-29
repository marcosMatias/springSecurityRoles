package br.com.company.auth.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.company.auth.model.RoleModel;
import br.com.company.auth.model.UserModel;
import br.com.company.auth.model.UserPermissionModel;




@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermissionModel,BigDecimal>{
	
	Optional<UserPermissionModel> findByUserAndRole(UserModel user, RoleModel role);

}
