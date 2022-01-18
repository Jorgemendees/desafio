package com.nttdata.desafioBanco.util.excptions;

public class CpfNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	 
	public CpfNaoEncontradoException(String msg) {
		super(msg);
	}
	
}
