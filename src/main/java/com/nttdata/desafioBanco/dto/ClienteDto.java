package com.nttdata.desafioBanco.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ClienteDto {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private String nome;
	
	@CPF
	@NotNull(message = "Favor informar um CPF válido!")
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Pattern(regexp= "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
	@NotNull
	@Column(nullable = false)
	private String telefone;
	
	@NotNull
	@Column(nullable = false) 
	private String endereco;

}
