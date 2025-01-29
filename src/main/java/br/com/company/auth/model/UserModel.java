package br.com.company.auth.model;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "UserModel")
@Table(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserModel implements UserDetails{

    @Serial
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_S")
    @SequenceGenerator(sequenceName = "USUARIO_S", allocationSize = 1, name = "USUARIO_S")
	@Column(name="ID_USUARIO")
	private BigDecimal idUsuario;
	@Column(name="NM_USUARIO")
	private String nome;
	@Column(name="NM_LOGIN")
	private String login;
	@Column(name="DS_SENHA")
	private String password;
	@Column(name="NM_EMPRESA")
	private String empresa;
	@Column(name="DS_EMAIL")
	private String email;
	@Column(name="IN_ATIVO")	
	private String active;

	 @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "PERMISSAO_USUARIO",
	            joinColumns = @JoinColumn(name = "ID_USUARIO"),
	            inverseJoinColumns = @JoinColumn(name = "ID_PERMISSAO")
	    )
	    private Set<RoleModel> roles = new HashSet<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return  roles;
	}
	@Override
	public String getPassword() {
		
		return password;
	}
	@Override
	public String getUsername() {
		
		return login;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		if (this.getActive().equals("S")) {
			return true;
		}
		
		return false;
		
	}

}
