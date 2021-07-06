package com.leiasempre.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leiasempre.model.Produto;
import com.leiasempre.model.Usuario;
import com.leiasempre.repository.FornecedorRepository;
import com.leiasempre.repository.UsuarioRepository;


@Controller
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository userRepository;
	
	
	@GetMapping("/usuarios")
	public ModelAndView usuario() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/usuario/listaUsuario");
		mv.addObject("listaDeUsuarios", userRepository.findAll());
		return mv;
	}
	
	@GetMapping("/usuario")
	public String listarUsuario(Model model) {
		List<Usuario> user = new ArrayList<Usuario>();
		userRepository.findAll().forEach(user::add);
		model.addAttribute("listaDeUsuarios", user);
		return "/usuario/listaUsuario";
	}
	
	@GetMapping("/pesquisarUsuario/{id}")
	public ModelAndView pesquisarUsuario(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/pesquisarUsuario");
		Optional<Usuario> user = userRepository.findById(id);
		if(user.isPresent()) {
			mv.addObject("Usuario", user);
		}
		return mv;
	}
	
	@GetMapping("/listaUsuario")
	public ModelAndView listaUsuario(Model model) {
		ModelAndView mv = new ModelAndView();
		List<Usuario> user = new ArrayList<Usuario>();
		userRepository.findAll().forEach(user::add);
		mv.setViewName("usuario/listaUsuario");
		if (user.isEmpty()) {
			model.addAttribute("usuarios", null);
		} else {
			model.addAttribute("usuarios", user);
		}
		return mv;
	}
	
	
	@RequestMapping("/editarUsuario/{id}")
	public String editarUsuario() {
		
		return null;
	}
	
	
	@RequestMapping("/excluirUsuario/{id}")
	public String excluirUsuario() {
		
		return null;
	}
	
}
