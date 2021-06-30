package com.leiasempre.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leiasempre.model.Fornecedor;
import com.leiasempre.model.Produto;
import com.leiasempre.model.Usuario;
import com.leiasempre.repository.FornecedorRepository;
import com.leiasempre.repository.ProdutoRepository;
import com.leiasempre.repository.UsuarioRepository;

@Configuration
@Profile("teste")
public class Testeconfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private ProdutoRepository productRepository;

	@Autowired
	private FornecedorRepository supplierRepository;

	@Override
	public void run(String... args) throws Exception {

		/*Usuario user01 = new Usuario(null, "nome01", "login01", "nome01@email.com", "991231234", "R.endereço", "senha");
		Usuario user02 = new Usuario(null, "nome02", "login02", "nome02@email.com", "991234567", "R. Endereço", "senha");
		Produto product01 = new Produto(null, "Livro01", "Suamario Livro01", "150", "Categoria Livro01", 20);
		Fornecedor supplier01 = new Fornecedor(null, "Fornecedor01", "CNPJ Fornecedor01", "Cidade");

		userRepository.saveAll(Arrays.asList(user01, user02));
		productRepository.saveAll(Arrays.asList(product01));
		supplierRepository.saveAll(Arrays.asList(supplier01));*/

	}

}
