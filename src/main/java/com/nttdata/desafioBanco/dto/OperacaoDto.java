package com.nttdata.desafioBanco.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.nttdata.desafioBanco.enums.OperacaoEnum;

import lombok.Data;

@Data
public class OperacaoDto {

	@NotNull
	@Column(nullable = false)
	private Long numeroDaConta;

	@NotNull
	@Column(nullable = false)
	private BigDecimal valorDeTransacao;
	
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OperacaoEnum tipoOperação;

}
