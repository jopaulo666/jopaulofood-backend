package br.com.jopaulo.jopaulofood.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jopaulo.jopaulofood.application.service.ClienteService;
import br.com.jopaulo.jopaulofood.application.service.ValidationException;
import br.com.jopaulo.jopaulofood.domain.cliente.Cliente;
import br.com.jopaulo.jopaulofood.domain.cliente.ClienteRepository;
import br.com.jopaulo.jopaulofood.util.SecurityUtils;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping(path = "/home")
	public String home() {
		return "cliente-home";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		Integer clienteId = SecurityUtils.loggedCliente().getId();		
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
		model.addAttribute("cliente", cliente);
		ControllerHelper.setEditModel(model, true);
		return "cliente-cadastro";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("cliente") @Valid Cliente cliente, Errors errors, Model model) {
		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente salvo com sucesso!");
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}		
		ControllerHelper.setEditModel(model, true);
		return "cliente-cadastro";
	}
}
