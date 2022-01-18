package com.nttdata.desafioBanco.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.desafioBanco.entities.Conta;
import com.nttdata.desafioBanco.entities.Operacao;
import com.nttdata.desafioBanco.enums.OperacaoEnum;
import com.nttdata.desafioBanco.enums.TipoDeContaEnum;
import com.nttdata.desafioBanco.repository.ContaRepository;
import com.nttdata.desafioBanco.repository.OperacaoRepository;
import com.nttdata.desafioBanco.util.excptions.ContaNaoEncontradaException;
import com.nttdata.desafioBanco.util.excptions.CpfNaoEncontradoException;
import com.nttdata.desafioBanco.util.excptions.SaldoInsuficienteException;
import com.nttdata.desafioBanco.util.excptions.TransacaoNaoAutorizadaException;

@Service
public class OperacaoService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private OperacaoRepository operacaoRepository;
	
	private Optional<Conta> verificarConta;
	
	public void sacar(Operacao operacao) {
		
		operacao.setNumeroDaConta(operacao.getNumeroDaConta());;
		operacao.setValorDeTransacao(operacao.getValorDeTransacao());
		operacao.setTipoOperacao(OperacaoEnum.SAQUE);
//		operacao.setTaxa(BigDecimal.valueOf(0.0));
		
		Conta conta = contaRepository.findContaByNumeroDaConta(operacao.getNumeroDaConta());
	
		verificarConta = Optional.ofNullable(conta);
		
		if(verificarConta.isPresent()) {
			
			
			double valorSaque = operacao.getValorDeTransacao().doubleValue();
			double valorSaldo = conta.getSaldo().doubleValue();
			TipoDeContaEnum tipoConta = conta.getTipoDaConta();
			
			String alerta = "";
			
			if(valorSaque  <= valorSaldo && valorSaque > 0) {
				if(tipoConta == TipoDeContaEnum.PESSOA_FISICA 
						|| tipoConta == TipoDeContaEnum.PESSOA_JURIDICA) {
						double novoValorSaldo = valorSaldo - valorSaque;
						conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
						
						
						 alerta = String.format("Você possui %d saques gratuitos", 
								 conta.getSaqueSemTaxa());
						
						
						alerta = String.format("Atingido limite de saques gratuitos,"
								+ " será cobrado uma taxa de R$%f", operacao.getTaxa());
					}
				}else if(tipoConta == TipoDeContaEnum.GOVERNAMENTAL) {
						double novoValorSaldo = valorSaldo - valorSaque;
						conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
						alerta = String.format("Você possui %d saques gratuitos", 
								conta.getSaqueSemTaxa());
					
					 alerta = String.format("Atingido limite de saques gratuitos,"
					 		+ " será cobrado uma taxa de R$%f", operacao.getTaxa());
				}
			
			contaRepository.save(conta);
			operacaoRepository.save(operacao);
	

		}
          else {
         throw new ContaNaoEncontradaException("Conta não encontrada, verifique o numero da conta");
         
         }
         
	}
		
		
		
		
//		if(verificarConta.isPresent()) {
//			
//			double valorSaque = operacao.getValorDeTransacao().doubleValue();
//			double valorSaldo = conta.getSaldo().doubleValue();
//			TipoDeContaEnum tipoConta = conta.getTipoDaConta();
//			
//			String alerta = "";
//			
//			if(valorSaque < valorSaldo && valorSaque > 0) {
//				if(tipoConta == TipoDeContaEnum.PESSOA_FISICA 
//						|| tipoConta == TipoDeContaEnum.PESSOA_JURIDICA) {
//						double novoValorSaldo = valorSaldo - valorSaque;
//						conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
//						
//					}else {
//						double taxa = 10.0;
//						double novoValorSaldo = valorSaldo - (valorSaque);
//						operacao.setTaxa(BigDecimal.valueOf(taxa));
//						alerta = String.format("Atingido limite de saques gratuitos,"
//								+ " será cobrado uma taxa de R$%f", operacao.getTaxa());
//					}
//				}else if(tipoConta == TipoDeContaEnum.GOVERNAMENTAL) {
//						double novoValorSaldo = valorSaldo - valorSaque;
//						conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
//						
//					}
//				}else{
//					double taxa = 20.0;
//					double novoValorSaldo = valorSaldo - (valorSaque);
//					conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
//					operacao.setTaxa(BigDecimal.valueOf(taxa));
//					
//					 String alerta = String.format("Atingido limite de saques gratuitos,"
//					 		+ " será cobrado uma taxa de R$%f", operacao.getTaxa());
//				}
//			
//			contaRepository.save(conta);
//			operacaoRepository.save(operacao);
//	}
//		 else  {
//             throw new SaldoInsuficienteException("Transação não autorizado, saldo insuficiente!");
//		 }
//         } else {
//         throw new ContaNaoEncontradaException("Conta não encontrada, verifique o numero da conta");
//         
//         }
	


	public void depositar (Operacao operacao) {
		
		operacao.setNumeroDaConta(operacao.getNumeroDaConta());
		operacao.setValorDeTransacao(operacao.getValorDeTransacao());
		operacao.setTipoOperacao(OperacaoEnum.DEPOSITO);
		
		Conta conta = contaRepository.findContaByNumeroDaConta(operacao.getNumeroDaConta());
		
		verificarConta = Optional.ofNullable(conta);
		
		if(verificarConta.isPresent()) {
			double valorSaldo = conta.getSaldo().doubleValue();
			double valorDeposito = operacao.getValorDeTransacao().doubleValue();
			
			if(valorDeposito > 0) {
				double novoValorSaldo = valorDeposito + valorSaldo;
				conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
				contaRepository.save(conta);
				operacaoRepository.save(operacao);
			}
			else {
				 throw new TransacaoNaoAutorizadaException(
						 "Transação não autorizada, valor de peposito inválido!");
			}
		}
			else {
	            throw new ContaNaoEncontradaException(
	            		"Conta não encontrada, verifique o numero da conta");
			}
        }
		public BigDecimal saldo (Long numeroDaConta) {
			Conta conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
			verificarConta = Optional.ofNullable(conta);
			if(verificarConta.isPresent()) {
				return conta.getSaldo();

			}
			else {
	            throw new ContaNaoEncontradaException("Conta não encontrada");
	        }
		}
 		
		public void transferencia(Operacao operacao) {
			
			operacao.setNumeroDaConta(operacao.getNumeroDaConta());
			operacao.setNumeroDaContaDestino(operacao.getNumeroDaContaDestino());
			operacao.setValorDeTransacao(operacao.getValorDeTransacao());
			operacao.setTipoOperacao(OperacaoEnum.TRANFERENCIA);
			
			Conta contaOrigem = contaRepository.findContaByNumeroDaConta(operacao.getNumeroDaConta());
			Conta contaDestino = contaRepository.findContaByNumeroDaConta(operacao.getNumeroDaConta());
			
			Optional<Conta> verificarContaOrigem = Optional.ofNullable(contaOrigem);
	        Optional<Conta> verificarContaDestino = Optional.ofNullable(contaDestino);
			
			if(verificarContaOrigem.isPresent() && verificarContaDestino.isPresent()) {
				if(contaOrigem != contaDestino) {
					double saldoContaOrigem = contaOrigem.getSaldo().doubleValue();
					double saldoContaDestino = contaDestino.getSaldo().doubleValue();
					double valorTransferencia = operacao.getValorDeTransacao().doubleValue();
					
					if(valorTransferencia < saldoContaOrigem && valorTransferencia > 0) {
						double novoSaldoContaOrigem = saldoContaOrigem -= valorTransferencia;
						double novoSaldoContaDestino = saldoContaDestino += valorTransferencia;
						
						contaOrigem.setSaldo(BigDecimal.valueOf(novoSaldoContaOrigem));
	                    contaDestino.setSaldo(BigDecimal.valueOf(novoSaldoContaDestino));

	                    contaRepository.save(contaOrigem);
	                    contaRepository.save(contaDestino);
	                    operacaoRepository.save(operacao);
					}
					else {
	                    throw new SaldoInsuficienteException(
	                    		"Saldo insuficiente para realizar transferência!");
	                }
				}
					else {
		                throw new TransacaoNaoAutorizadaException(
		                		"Transação não autorizada, verifique o número das contas");
				}
			}
			else {
	            throw new ContaNaoEncontradaException(
	            		"Conta não encontrada, verifique o número das contas");
			}
		}
		
		public List<Operacao> extrado(Long numeroDaConta){
			List<Operacao> operacoes = operacaoRepository.findExtratoByNumeroDaConta(numeroDaConta);
			
			if(operacoes.size() > 0) {
				for (Operacao operacao : operacoes) {
					operacao.getNumeroDaConta();
				}
			}
			else {
				 throw new CpfNaoEncontradoException(
		                    String.format("Conta não encontrada, verifique o numero da conta"));
			}
			return operacoes;
		}
		
	}


