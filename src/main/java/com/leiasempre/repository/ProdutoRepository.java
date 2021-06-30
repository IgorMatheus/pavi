package com.leiasempre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leiasempre.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
