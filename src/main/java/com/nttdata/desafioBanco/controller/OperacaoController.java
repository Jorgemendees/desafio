package com.nttdata.desafioBanco.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.desafioBanco.entities.Operacao;
import com.nttdata.desafioBanco.service.OperacaoService;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {
	
	@Autowired
	private OperacaoService operacaoService;
	
	
	@PostMapping("/sacar")
	public ResponseEntity<Operacao> sacar(@RequestBody Operacao operacao){
		Operacao operacaoAtual = new Operacao();
		BeanUtils.copyProperties(operacaoAtual, operacao);
		operacaoService.sacar(operacaoAtual);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/depositar")
	public ResponseEntity<Operacao> depositar(@RequestBody @Valid Operacao operacao){
		Operacao operacaoAtual = new Operacao();
		BeanUtils.copyProperties(operacaoAtual, operacao);
		operacaoService.depositar(operacaoAtual);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/transferencia")
	public ResponseEntity<Operacao> transferencia(@RequestBody @Valid Operacao operacao){
		Operacao operacaoAtual = new Operacao();
		BeanUtils.copyProperties(operacaoAtual, operacao);
		operacaoService.transferencia(operacaoAtual);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	
//	@PostMapping("/sacar")
//	public ResponseEntity<Operacao> sacar(@RequestBody @Valid OperacaoDto operacaoDto){
//		Operacao operacao = new Operacao();
//		BeanUtils.copyProperties(operacaoDto, operacao);
//		return  ResponseEntity.status(HttpStatus.OK).build();
//	}
//	
//	@PostMapping("/depositar")
//	public ResponseEntity<Operacao> depositar(@RequestBody @Valid OperacaoDto operacaoDto){
//		Operacao operacao = new Operacao();
//		BeanUtils.copyProperties(operacaoDto, operacao);
//		return  ResponseEntity.status(HttpStatus.OK).build();
//	}
//	
//	@PostMapping("/transferencia")
//	public ResponseEntity<Operacao> transferencia(@RequestBody @Valid OperacaoDto operacaoDto){
//		Operacao operacao = new Operacao();
//		BeanUtils.copyProperties(operacaoDto, operacao);
//		operacaoService.transferencia(operacao);
//		return  ResponseEntity.status(HttpStatus.OK).build();
//	}
	
	@GetMapping("/saldo/")
	public ResponseEntity<?> saldo(@RequestParam(name = "numeroDaConta") Long numeroDaConta){
		BigDecimal saldoConta = operacaoService.saldo(numeroDaConta);
		return ResponseEntity.ok().body(saldoConta);
	}
	
	@GetMapping("/extrato/")
	public ResponseEntity<List<Operacao>> extrato(@RequestParam(name = "numeroDaConta") Long numeroDaConta){
		List<Operacao> operacao = operacaoService.extrado(numeroDaConta);
		return ResponseEntity.ok(operacao);
	}
	
	

}
