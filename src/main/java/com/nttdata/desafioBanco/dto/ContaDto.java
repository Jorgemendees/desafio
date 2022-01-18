package com.nttdata.desafioBanco.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.nttdata.desafioBanco.enums.TipoDeContaEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ContaDto {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CPF
	@NotNull(message = "Favor informar um CPF válido!")
	@Column(nullable = false)
	private String cpf;
	

	@NotNull
	@Column(nullable = false)
	private Long agencia;
	

	@NotNull
	@Column(nullable = false, name = "numero_da_conta")
	private Long numeroDaConta;
	

	@NotNull
	@Column(nullable = false, name = "digito_verificador")
	private Long digitoVerificador;
	
	@Column(nullable = false, name = "tipo_da_conta")
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoDeContaEnum tipoDaConta;
	
	@Column(nullable = false)
	@NotNull
	private BigDecimal saldo;

}
