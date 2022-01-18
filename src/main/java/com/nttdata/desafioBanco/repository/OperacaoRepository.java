package com.nttdata.desafioBanco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.desafioBanco.entities.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

	List<Operacao> findExtratoByNumeroDaConta(Long numeroDaConta);
}
