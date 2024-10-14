package br.com.company.model;

import java.math.BigDecimal;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERMISSAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel implements GrantedAuthority  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_PERMISSAO")
	private BigDecimal idRole;
	@Column(name = "CD_PERMISSAO")
	private String name;

	@Override
	public String getAuthority() {
		
		return this.name;
	}
	
    
	
}
