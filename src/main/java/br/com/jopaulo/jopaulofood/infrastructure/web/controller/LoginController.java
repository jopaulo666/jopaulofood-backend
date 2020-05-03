package br.com.jopaulo.jopaulofood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping(path = {"/login", "/"})
	public String login() {
		return "login";
	}
	
	@GetMapping(path = "/login-error")
	public String loginErro(Model model) {
		return "login";
	}
}
