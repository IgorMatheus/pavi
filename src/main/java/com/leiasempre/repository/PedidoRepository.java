package com.leiasempre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leiasempre.model.Pedido;

public interface PedidoRepository extends  JpaRepository<Pedido, Long>{

}
