package com.nttdata.desafioBanco.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.desafioBanco.entities.Cliente;
import com.nttdata.desafioBanco.repository.ClienteRepository;
import com.nttdata.desafioBanco.util.excptions.ClienteExistenteException;
import com.nttdata.desafioBanco.util.excptions.CpfNaoEncontradoException;

@Service
public class ClienteService {
	
	private static final String MSG_CPF_NAO_ENCONTRADO = "Cliente de CPF %s não encontrado! ";
	
	private static Optional<Cliente> verificarCliente;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void adicionar(Cliente cliente) {
		
		cliente.setNome(cliente.getNome());
		cliente.setCpf(cliente.getCpf());
		cliente.setTelefone(cliente.getTelefone());
		cliente.setEndereco(cliente.getEndereco());
		
		verificarCliente = Optional.ofNullable(clienteRepository.findClienteByCpf(cliente.getCpf()));
		
		if(verificarCliente.isEmpty()) {
			clienteRepository.save(cliente);
		}else {
			
			throw new ClienteExistenteException(
					String.format("O CPF %s já possui cadastro! ", cliente.getCpf()));
		}
	}
		
		public Cliente buscar(String cpf) {
			Cliente cliente = clienteRepository.findClienteByCpf(cpf);
			
			verificarCliente = Optional.ofNullable(cliente);
			
			if(verificarCliente.isPresent()) {
				return cliente;
			}else {
				throw new CpfNaoEncontradoException(
						String.format(MSG_CPF_NAO_ENCONTRADO, cpf));
			}
		}
		
		public Cliente atualizar(Cliente cliente, String cpf) {
			Cliente clienteAtual = clienteRepository.findClienteByCpf(cpf);
			BeanUtils.copyProperties(cliente, clienteAtual);
			
			verificarCliente = Optional.ofNullable(clienteRepository.findClienteByCpf(cliente.getCpf()));
			
			if(verificarCliente.isPresent()) {
				return clienteRepository.save(clienteAtual);
			}else {
				throw new CpfNaoEncontradoException(
						String.format(MSG_CPF_NAO_ENCONTRADO, cpf));
			}
			
		}
		
		
		public void excluir(Long id) {
			Optional<Cliente> cliente = clienteRepository.findById(id);
			
			
			if(cliente.isPresent()) {
				clienteRepository.deleteById(id);
			}else {
				throw new CpfNaoEncontradoException(
						String.format("Cliente de ID %s não existe!", id));
			}
			
			
		}

		
//		public void excluir(String cpf) {
//			Cliente cliente = clienteRepository.findClienteByCpf(cpf);
//			
//			verificarCliente = Optional.ofNullable(clienteRepository.findClienteByCpf(cliente.getCpf()));
//			
//			if(verificarCliente.isPresent()) {
//				clienteRepository.delete(cliente);
//			}else {
//				throw new CpfNaoEncontradoException(
//						String.format(MSG_CPF_NAO_ENCONTRADO, cpf));
//			}
//			
//			
//		}

}
