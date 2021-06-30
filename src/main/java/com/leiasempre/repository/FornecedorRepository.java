package com.leiasempre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.leiasempre.model.Fornecedor;

public interface FornecedorRepository extends CrudRepository<Fornecedor, Long> {

	Optional<Fornecedor> findById(Long id);

	List<Fornecedor> findByNome(String nome);

}
