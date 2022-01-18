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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.desafioBanco.dto.ClienteDto;
import com.nttdata.desafioBanco.entities.Cliente;
import com.nttdata.desafioBanco.repository.ClienteRepository;
import com.nttdata.desafioBanco.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
		
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody @Valid ClienteDto clienteDto){
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		clienteService.adicionar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> buscar(@RequestParam(name = "cpf") String cpf){
		Cliente cliente = clienteService.buscar(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Cliente> atualizar(@RequestBody @Valid ClienteDto clienteDto,
			@RequestParam(name = "CPF") String cpf){
		Cliente cliente = clienteService.buscar(cpf);
		BeanUtils.copyProperties(clienteDto, cpf); 
		clienteService.atualizar(cliente, cpf);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> remover(@PathVariable Long id){
		clienteService.excluir(id);
		return ResponseEntity.status(HttpStatus.OK).build(); 
		
	}
	
}


