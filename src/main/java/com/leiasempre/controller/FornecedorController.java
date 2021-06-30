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
import com.leiasempre.repository.FornecedorRepository;

@Controller
public class FornecedorController {

	
	@Autowired
	private FornecedorRepository supplierRepository;

	@GetMapping("/fornecedor")
	public String listarFornecedor(Model model) {
		List<Fornecedor> supplier = new ArrayList<Fornecedor>();
		supplierRepository.findAll().forEach(supplier::add);
		model.addAttribute("listaDeFornecedores", supplier);
		return "/fornecedor/listaFornecedor";
	}

	@GetMapping("/cadastrarFornecedor")
	public String cadastrarFornecedor(Model model) {
		Fornecedor supplier = new Fornecedor();
		model.addAttribute("CadastroDeFornecedor", supplier);
				
		return "/fornecedor/cadastraFornecedor";
	}

	@PostMapping("/salvarFornecedor")
	public String salvarFornecedor(@Valid @ModelAttribute("Fornecedor") Fornecedor supplier, RedirectAttributes redAtt) {
		try {
			supplierRepository.save(supplier);
			redAtt.addFlashAttribute("Aviso", "Sucesso ao salvar");
		} catch (Exception e) {
			redAtt.addFlashAttribute("Aviso", "Erro ao salvar");
		}

		return "redirect:/fornecedor";
	}

	@RequestMapping("/editarFornecedor/{id}")
	public ModelAndView editarFornecedor(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/editarFornecedor");
		Optional<Fornecedor> supplier = supplierRepository.findById(id);
		if (supplier.isPresent()) {
			mv.addObject("produto", supplier);
		}
		return mv;
	}

	@RequestMapping("/excluirFornecedor/{id}")
	public String excluirFornecedor(@PathVariable("id") Long id, RedirectAttributes redAtt) {
		Optional<Fornecedor> supplier= supplierRepository.findById(id);
		if (supplier.isPresent()) {
			supplierRepository.deleteById(id);
			redAtt.addFlashAttribute("Aviso", "Sucesso ao excluir");
		} else {
			redAtt.addFlashAttribute("Aviso", "Erro ao excluir");
		}

		return "redirect:/fornecedor";
	}
	
	
}
