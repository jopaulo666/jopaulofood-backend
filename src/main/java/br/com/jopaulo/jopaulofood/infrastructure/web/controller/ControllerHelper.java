package br.com.jopaulo.jopaulofood.infrastructure.web.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import br.com.jopaulo.jopaulofood.domain.restaurante.CategoriaRestaurante;
import br.com.jopaulo.jopaulofood.domain.restaurante.CategoriaRestauranteRepository;

public class ControllerHelper {

	public static void setEditModel(Model model, boolean isEdit) {
		model.addAttribute("editMode", isEdit);
	}
	
	public static void addCategoriasToRequest(CategoriaRestauranteRepository repository, Model model) {
		List<CategoriaRestaurante> categorias = repository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
	}
}
