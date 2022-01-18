package com.nttdata.desafioBanco.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.desafioBanco.dto.ContaDto;
import com.nttdata.desafioBanco.entities.Conta;
import com.nttdata.desafioBanco.repository.ContaRepository;
import com.nttdata.desafioBanco.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaService contaService;
	
	@PostMapping
	public ResponseEntity<Conta> adicionar(@RequestBody @Valid ContaDto contaDto){
		Conta conta = new Conta();
		BeanUtils.copyProperties(contaDto, conta); 
		contaService.adicionar(conta); 
		return ResponseEntity.status(HttpStatus.CREATED).body(conta);
	}
	
	@GetMapping
	public List<Conta> listar(){
		return contaRepository.findAll();
	}
	
	@GetMapping("/cpf/")
	public ResponseEntity<?> buscar(@RequestParam(name = "cpf") String cpf){
		List<Conta> conta = contaService.buscar(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	

//(@RequestParam(name = "id") Long id)
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Conta> remover(@PathVariable Long id){
		contaService.excluir(id); 
		return ResponseEntity.status(HttpStatus.OK).build();
	
	}
//
//	@DeleteMapping("/cpf/{cpf}")
//	public ResponseEntity<Conta> remover(@RequestParam(name = "cpf") String cpf){
//		contaService.excluir(id); 
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
}
