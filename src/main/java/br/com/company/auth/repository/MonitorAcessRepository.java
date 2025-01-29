package br.com.company.auth.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.company.auth.model.MonitorAcessModel;



@Repository
public interface MonitorAcessRepository extends JpaRepository<MonitorAcessModel, BigDecimal>{

}
