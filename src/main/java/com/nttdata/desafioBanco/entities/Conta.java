package com.nttdata.desafioBanco.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nttdata.desafioBanco.enums.TipoDeContaEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Table(name = "conta_bancaria")
public class Conta {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cpf;
	
	private Long agencia;
	
	private Long numeroDaConta;
	
	private Long digitoVerificador;
	
	private TipoDeContaEnum tipoDaConta;
	
	private BigDecimal saldo;
	
	private int saqueSemTaxa;
	
	private String aviso;
	
}
