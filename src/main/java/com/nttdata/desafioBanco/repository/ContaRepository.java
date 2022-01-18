package com.nttdata.desafioBanco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.desafioBanco.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta> findContaByCpf(String cpf);
    Conta findContaByNumeroDaConta(Long numeroDaConta);
}
