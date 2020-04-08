package br.com.jopaulo.jopaulofood.infrastructure.web.controller;

import org.springframework.ui.Model;

public class ControllerHelper {

	public static void setEditModel(Model model, boolean isEdit) {
		model.addAttribute("editMode", isEdit);
	}
}
