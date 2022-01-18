package com.nttdata.desafioBanco.util.excptions;

public class ContaExistenteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ContaExistenteException(String msg) {
		super(msg);
	}

}
