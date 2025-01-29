package br.com.company.auth.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "PERMISSAO_USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSAO_USUARI_S ")
    @SequenceGenerator(sequenceName = "PERMISSAO_USUARI_S ", allocationSize = 1, name = "PERMISSAO_USUARI_S ")
    @Column(name = "ID_PERMISSAO_USUARIO")
    private BigDecimal idUserRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERMISSAO", nullable = false)
    private RoleModel role;

}
