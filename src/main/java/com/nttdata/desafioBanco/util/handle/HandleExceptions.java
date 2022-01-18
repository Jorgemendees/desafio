package com.nttdata.desafioBanco.util.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nttdata.desafioBanco.util.error.StandardError;
import com.nttdata.desafioBanco.util.excptions.ClienteExistenteException;
import com.nttdata.desafioBanco.util.excptions.ContaExistenteException;
import com.nttdata.desafioBanco.util.excptions.ContaNaoEncontradaException;
import com.nttdata.desafioBanco.util.excptions.CpfNaoEncontradoException;
import com.nttdata.desafioBanco.util.excptions.SaldoInsuficienteException;
import com.nttdata.desafioBanco.util.excptions.TransacaoNaoAutorizadaException;

@ControllerAdvice
public class HandleExceptions extends ResponseEntityExceptionHandler {
	 

	    @ExceptionHandler(ClienteExistenteException.class)
	    public ResponseEntity<?> handleClienteExistenteException(ClienteExistenteException e){
	    	StandardError error = StandardError.builder()
	    			.mensagem(e.getMessage()).build();

	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    }

	    @ExceptionHandler(CpfNaoEncontradoException.class)
	    public ResponseEntity<?> handleCpfNaoEncontradoException(CpfNaoEncontradoException e){
	    	StandardError error = StandardError.builder()
	    			.mensagem(e.getMessage()).build();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }

	    @ExceptionHandler(ContaExistenteException.class)
	    public  ResponseEntity<?> hadleContaExistenteException(ContaExistenteException e){
	    	StandardError error = StandardError.builder()
	    			.mensagem(e.getMessage()).build();
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    }

	    @ExceptionHandler(ContaNaoEncontradaException.class)
	    public ResponseEntity<?> handleContaNaoEncontradoException(ContaNaoEncontradaException e){
	    	StandardError error = StandardError.builder()
	    			.mensagem(e.getMessage()).build();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }

	    @ExceptionHandler(SaldoInsuficienteException.class)
	    public ResponseEntity<?> handleSaldoInsuficienteException(SaldoInsuficienteException e){
	    	StandardError error = StandardError.builder()
	    			.mensagem(e.getMessage()).build();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }

	    @ExceptionHandler(TransacaoNaoAutorizadaException.class)
	    public ResponseEntity<?> handleTransacaoNaoAutorizadaException(TransacaoNaoAutorizadaException e){
	    	StandardError error = StandardError.builder()
	    			.mensagem(e.getMessage()).build();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	    
	    @Override
	    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, org.springframework.http.HttpHeaders headers,
	    		HttpStatus status, WebRequest request){
	    	
	    	body = StandardError.builder()
	    		.mensagem(status.getReasonPhrase())
	    		.build();
	    	
	    	return super.handleExceptionInternal(ex, body, headers, status, request);
	    }
	    
	   
	    

}
