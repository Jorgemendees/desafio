package com.nttdata.desafioBanco.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nttdata.desafioBanco.enums.OperacaoEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Operacao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@Column(nullable = false)
	private Long numeroDaConta;
	
	@Column(nullable = false)
	private Long numeroDaContaDestino;
	
	@Column(nullable = false)
	private BigDecimal valorDeTransacao;
	
	@Column(nullable = false)
	private BigDecimal taxa;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OperacaoEnum tipoOperacao;


}
