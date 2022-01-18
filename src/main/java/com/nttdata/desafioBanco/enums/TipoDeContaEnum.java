package com.nttdata.desafioBanco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoDeContaEnum {

	PESSOA_FISICA(0, "Pessoa Fisica", 10), 
	PESSOA_JURIDICA(1, "Pessoa Juridica", 10), 
	GOVERNAMENTAL(2,"Conta Governamental", 20);
	
	public int codigo;
    public String descricao;
    private double taxa;

}
