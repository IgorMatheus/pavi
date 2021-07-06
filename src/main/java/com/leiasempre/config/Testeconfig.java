package com.leiasempre.config;

import java.time.Instant;
import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leiasempre.model.Fornecedor;
import com.leiasempre.model.Pedido;
import com.leiasempre.model.PedidoProduto;
import com.leiasempre.model.Produto;
import com.leiasempre.model.Usuario;
import com.leiasempre.repository.FornecedorRepository;
import com.leiasempre.repository.PedidoProdutoRepository;
import com.leiasempre.repository.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository orderRepository;
	
	@Autowired 
	private PedidoProdutoRepository pedidoProdutoRepository;

	@Override
	public void run(String... args) throws Exception {

		/*Usuario user01 = new Usuario(null, "nome01", "login01", "nome01@email.com", "991231234", "R.endereço2", "senha");
		Usuario user02 = new Usuario(null, "nome02", "login02", "nome02@email.com", "991234567", "R. Endereço", "senha");
		Produto product01 = new Produto(null, "Livro01", "Suamario Livro01", "150", "Categoria Livro01", 20);
		Pedido pedido = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), user01);
		Fornecedor supplier01 = new Fornecedor(null, "Fornecedor01", "CNPJ Fornecedor01", "Cidade");

		userRepository.saveAll(Arrays.asList(user01, user02));
		productRepository.saveAll(Arrays.asList(product01));
		supplierRepository.saveAll(Arrays.asList(supplier01));
		orderRepository.save(pedido);
		
		PedidoProduto pp1 = new PedidoProduto(pedido, product01, 2, product01.getPreco());
		pedidoProdutoRepository.save(pp1);*/
		
		Usuario user01 = new Usuario(null, "nome01", "login01", "nome01@email.com", "991231234", "R.endereço2", "senha");
		Usuario user02 = new Usuario(null, "nome02", "login02", "nome02@email.com", "991234567", "R. Endereço", "senha");
		userRepository.saveAll(Arrays.asList(user01, user02));
		
		Produto product01 = new Produto(null, "Livro01", "Suamario Livro01", "150", "Categoria Livro01", 20);
		Produto product02 = new Produto(null, "Livro02", "Suamario Livro02", "200", "Categoria Livro02", 32);
		Produto product03 = new Produto(null, "Livro01", "Suamario Livro01", "150", "Categoria Livro01", 20);
		Produto product04 = new Produto(null, "Livro02", "Suamario Livro02", "200", "Categoria Livro02", 32);
		Produto product05 = new Produto(null, "Livro01", "Suamario Livro01", "150", "Categoria Livro01", 20);
		Produto product06 = new Produto(null ,"Livro3", "Sumario livro 01",  "300",  "Categoria","./assets/img/products-01.jpg",  50);
		
		productRepository.saveAll(Arrays.asList(product01,product02,product03,product04,product05,product06));


	}

}
