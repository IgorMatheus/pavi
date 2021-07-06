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
		
		Produto product01 = new Produto(null, "Arquitetura Limpa", "As regras universais de arquitetura de software aumentam dramaticamente a produtividade dos desenvolvedores ao longo da vida dos sistemas de software. ", "432", "Computação", "https://images-na.ssl-images-amazon.com/images/I/41T8NdKFqEL._SX352_BO1,204,203,200_.jpg", 59.9);
		Produto product02 = new Produto(null, "Pai Rico, Pai Pobre", "Celebrando 20 anos como o livro n° 1 em finanças pessoais.A escola prepara as crianças para o mundo real? Essa é a primeira pergunta com a qual o leitor se depara neste livro.", "336", "Finanças", "https://images-na.ssl-images-amazon.com/images/I/51VKbEh8uSL._SX356_BO1,204,203,200_.jpg", 44.89);
		Produto product03 = new Produto(null, "A rainha vermelha", "O mundo de Mare Barrow é dividido pelo sangue: vermelho ou prateado. Mare e sua família são vermelhos: plebeus, humildes, destinados a servir uma elite prateada cujos poderes sobrenaturais os tornam quase deuses. ", "424", "Ação e Aventura", "https://images-na.ssl-images-amazon.com/images/I/41rK-7HUAML._SX346_BO1,204,203,200_.jpg", 26.79);
		Produto product04 = new Produto(null, "O cavaleiro da morte - Crônicas saxônicas - vol. 2", "Suamario Livro02", "338", "Categoria Livro02","https://m.media-amazon.com/images/I/51QxVdN3mfL.jpg", 35.9);
		
		productRepository.saveAll(Arrays.asList(product01,product02,product03,product04));


	}

}
