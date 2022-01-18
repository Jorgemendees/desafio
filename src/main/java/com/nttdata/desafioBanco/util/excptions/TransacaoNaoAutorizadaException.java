package com.nttdata.desafioBanco.util.excptions;

public class TransacaoNaoAutorizadaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	 
	public TransacaoNaoAutorizadaException(String msg) {
		super(msg);
	}
	
}
