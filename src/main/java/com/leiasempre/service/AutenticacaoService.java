package com.leiasempre.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leiasempre.model.Permissao;
import com.leiasempre.model.Usuario;
import com.leiasempre.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = userRepository.findByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não localizado.");
		}
		return new User(user.getLogin(), user.getSenha(), mapRolesToAuthorities(user.getPermissoes()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Permissao> permissoes) {
		return permissoes.stream().map(permissao -> new SimpleGrantedAuthority(permissao.getNome()))
				.collect(Collectors.toList());
	}

	public void salvar(Usuario usuario) {
		Usuario u = new Usuario();
		u.setNome(usuario.getNome());
		u.setLogin(usuario.getLogin());
		u.setEmail(usuario.getEmail());
		u.setCelular(usuario.getCelular());
		u.setEndereco(usuario.getEndereco());
		u.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		u.setPermissoes(Arrays.asList(new Permissao("ROLE_USUARIO")));
		try {
			userRepository.save(u);
		} catch (Exception e) {
			System.out.println("Erro ao salvar: " + e);
		}
	}

}