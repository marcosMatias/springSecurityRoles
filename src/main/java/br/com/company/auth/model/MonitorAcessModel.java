package br.com.company.auth.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MONITOR_ACESSO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorAcessModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MONITOR_ACESSO_S")
	@SequenceGenerator(sequenceName = "MONITOR_ACESSO_S", allocationSize = 1, name = "MONITOR_ACESSO_S")
	@Column(name = "ID_MONITOR_ACESSO")
	private BigDecimal idMonitorAcesso;
	@Column(name = "NM_LOGIN")
	private String login;
	@Column(name = "TP_METODO")
	private String httpMethod;
	@Column(name = "DS_URI")
	private String uri;
	@Column(name = "DT_ACESSO")
	private LocalDateTime acessDate;
	@Column(name = "NR_IP")
	private String ipAdress;
}
