package com.nttdata.desafioBanco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.desafioBanco.entities.Cliente;
import com.nttdata.desafioBanco.entities.Conta;
import com.nttdata.desafioBanco.enums.TipoDeContaEnum;
import com.nttdata.desafioBanco.repository.ClienteRepository;
import com.nttdata.desafioBanco.repository.ContaRepository;
import com.nttdata.desafioBanco.util.excptions.ContaExistenteException;
import com.nttdata.desafioBanco.util.excptions.CpfNaoEncontradoException;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Conta> listar(){
		return contaRepository.findAll();
	}
	
	public void adicionar(Conta conta) {
		
		
		conta.setCpf(conta.getCpf());
		conta.setAgencia(conta.getAgencia());
		conta.setNumeroDaConta(conta.getNumeroDaConta());
		conta.setDigitoVerificador(conta.getDigitoVerificador());
		conta.setTipoDaConta(conta.getTipoDaConta());
		conta.setSaldo(conta.getSaldo());
		
		Optional<Cliente> verificarCpfConta = Optional.ofNullable(
				clienteRepository.findClienteByCpf(conta.getCpf()));
		
		Optional<Conta> verificarExistenciaConta = Optional.ofNullable(
				contaRepository.findContaByNumeroDaConta(conta.getNumeroDaConta()));
		
		if(verificarExistenciaConta.isPresent()) {
			throw new ContaExistenteException("Número de conta em uso");
		}
		if(verificarCpfConta.isPresent() && verificarExistenciaConta.isEmpty()) {
			TipoDeContaEnum tipoConta = conta.getTipoDaConta();
			if(tipoConta == TipoDeContaEnum.PESSOA_FISICA) {
				conta.setSaqueSemTaxa(5);
				conta.setAviso(String.format("Você possui %d saques mensais gratuitos. " +
						"Após atigir esse limite será cobrando uma taxa de R$10,00!",
						conta.getSaqueSemTaxa()));
				conta.setTipoDaConta(TipoDeContaEnum.PESSOA_FISICA);
			}else if (tipoConta == TipoDeContaEnum.PESSOA_JURIDICA) {
				conta.setSaqueSemTaxa(50);
				conta.setAviso(String.format("Você possui %d saques mensais gratuitos. " +
						"Após atigir esse limite será cobrando uma taxa de R$10,00!",
						conta.getSaqueSemTaxa()));
				conta.setTipoDaConta(TipoDeContaEnum.PESSOA_JURIDICA);
			}else if (tipoConta == TipoDeContaEnum.GOVERNAMENTAL) {
				conta.setSaqueSemTaxa(250);
				conta.setAviso(String.format("Você possui %d saques mensais gratuitos. " +
						"Após atigir esse limite será cobrando uma taxa de R$20,00!",
						conta.getSaqueSemTaxa()));
				conta.setTipoDaConta(TipoDeContaEnum.GOVERNAMENTAL);
			}
			contaRepository.save(conta);
		}else {
			throw new ContaExistenteException("CPF não encontrado");
		}
	}

	public List<Conta> buscar(String cpf){
		List<Conta> contas = contaRepository.findContaByCpf(cpf);
		if(contas.size() > 0) {
			for(Conta conta : contas) {
				conta.getCpf();
			}
			}else {
				throw new CpfNaoEncontradoException(
						String.format("Conta de CPF %s não existe", cpf));
			}
			return contas;
		}
	
	public void excluir(Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		
		if(conta.isPresent()) {
				contaRepository.deleteById(id);
			}
	 else{
		 throw new CpfNaoEncontradoException(
					String.format("Conta de ID %s não existe!", id));
	}
		}
	}
	
//	public void excluir(String cpf) {
//		List<Conta> contas = contaRepository.findContaByCpf(cpf);
//		
//		if(contas.size() > 0) {
//			for(Conta conta : contas) {
//				contaRepository.delete(conta);
//			}
//		}else {
//			throw new CpfNaoEncontradoException(
//					String.format("Conta de CPF %s não existe", cpf));
//		}
//	}
			

