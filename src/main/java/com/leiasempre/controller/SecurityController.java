package com.leiasempre.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leiasempre.model.Usuario;
import com.leiasempre.repository.UsuarioRepository;
import com.leiasempre.service.AutenticacaoService;

@Controller
public class SecurityController {

	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	private AutenticacaoService autenticacaoService;

	// Pegar as credenciais de que está logado no sistema. Saber quem está logado no
	// sistema.
	public static String getUsernameLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	@GetMapping("/login")
	public String login(RedirectAttributes redAtt) {
		String username = getUsernameLogado();
		Usuario user = userRepository.findByLogin(username);
		if (user == null) {
			// redAtt.addAttribute("mensagem", "Usuário não localizado!"); Validação sendo
			// feita no login.html
			return "login";
		} else {
			redAtt.addAttribute("username", username);
			return "redirect:/";
		}
	}

	@GetMapping("/novoUsuario")
	public String novoUsuario(Model model) {
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);

		return "/usuario/novoUsuario";
	}

	@GetMapping("/esqueciSenha")
	public String esqueciSenha() {
		return "esqueciSenha";
	}

	@PostMapping("/salvarUsuario")
	public String salvarUsuario(@ModelAttribute("usuario") @Valid Usuario user, BindingResult result,
			RedirectAttributes redAtt) {
		Usuario u = userRepository.findByLogin(user.getLogin());
		if (u != null) {
			result.rejectValue("login", null, "Já existe cadatro com este email");
		}
		if (result.hasErrors()) {
			redAtt.addFlashAttribute("mensagem", "Erro ao gerar novo usuário.");
			return "/usuario/novoUsuario";
		}
		autenticacaoService.salvar(user);
		redAtt.addFlashAttribute("mensagem", "Usuário cadastrado.");
		return "redirect:/login";
	}

	//Acessado somente por usuários com permissão de Administrador.
	@GetMapping("/usuarioadm")
	public String exibeUsuario(Model model) {
		List<Usuario> user = new ArrayList<Usuario>();
		userRepository.findAll().forEach(user::add);
		if (user.isEmpty()) {
			model.addAttribute("usuarios", null);
		} else {
			model.addAttribute("usuarios", user);
		}
		return "usuario";
	}

}
