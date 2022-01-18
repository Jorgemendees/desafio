package com.nttdata.desafioBanco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperacaoEnum {
		
	SAQUE(0, "Saque"),
    DEPOSITO(1, "Deposito"),
    TRANFERENCIA(2, "Transferencia");
	
	public int codigo;
    public String descricao;

}
