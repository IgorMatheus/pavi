package com.leiasempre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leiasempre.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

}
