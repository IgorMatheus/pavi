package com.leiasempre.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leiasempre.model.Fornecedor;
import com.leiasempre.model.Produto;
import com.leiasempre.model.Usuario;
import com.leiasempre.repository.FornecedorRepository;
import com.leiasempre.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository productRepository;

	@Autowired
	private FornecedorRepository supplierRepository;

	@GetMapping("/")
	public String homePage() {
		return "index";
	}

	@GetMapping("/produto")
	public String opcoesProduto(Model model) {
		List<Produto> product = new ArrayList<Produto>();
		productRepository.findAll().forEach(product::add);
		model.addAttribute("listaDeProdutos", product);
		return "/produto/opcoesProduto";
	}

	@GetMapping("/listarProduto")
	public String listarProduto(Model model) {
		List<Produto> product = new ArrayList<Produto>();
		productRepository.findAll().forEach(product::add);
		model.addAttribute("listaDeProdutos", product);
		return "/produto/listaProduto";
	}

	@GetMapping("/cadastrarProduto")
	public String cadastrarProduto(Model model) {
		List<Fornecedor> supplier = new ArrayList<Fornecedor>();
		supplierRepository.findAll().forEach(supplier::add);

		Produto product = new Produto();
		model.addAttribute("cadastroDeProduto", product);
		model.addAttribute("fornecedores", supplier);

		return "/produto/cadastraProduto";
	}

	// EXCLUINDO PRODUTO
	@PostMapping("/salvarProduto")
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto product, RedirectAttributes redAtt) {
		try {
			productRepository.save(product);
			redAtt.addFlashAttribute("Aviso", "Sucesso ao salvar");
		} catch (Exception e) {
			redAtt.addFlashAttribute("Aviso", "Erro ao salvar");
		}

		return "redirect:/produto";
	}

	/*
	 * @RequestMapping("/editarProduto/{id}") public ModelAndView
	 * editarProduto(@PathVariable("id") Long id) { ModelAndView mv = new
	 * ModelAndView("/editarProduto"); Optional<Produto> product =
	 * productRepository.findById(id); if (product.isPresent()) {
	 * mv.addObject("produto", product); } return mv; }
	 */

	@GetMapping("/editarProduto/{id}")
	public ModelAndView editarProduto(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("produto/editarProduto");
		Optional<Produto> product = productRepository.findById(id);
		if (product.isPresent()) {
			mv.addObject("produto", product);
		}
		return mv;
	}
			
	@PostMapping("/editarProduto")
	public ModelAndView editarProduto(Produto produto) {
		ModelAndView mv = new ModelAndView();
		productRepository.save(produto);
		mv.setViewName("redirect:/listarProduto");
		return mv;
	}

	@RequestMapping("/excluirProduto/{id}")
	public String excluirProduto(@PathVariable("id") Long id, RedirectAttributes redAtt) {
		Optional<Produto> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
			redAtt.addFlashAttribute("Aviso", "Sucesso ao excluir");
		} else {
			redAtt.addFlashAttribute("Aviso", "Erro ao excluir");
		}

		return "redirect:/produto";
	}

	private void updateProduto(Produto product, Produto obj) {
		product.setNome(obj.getNome());
		product.setSupplier(obj.getSupplier());
		product.setQntdPaginas(obj.getQntdPaginas());
		product.setPreco(obj.getPreco());
	
	}
	
}
