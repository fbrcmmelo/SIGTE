package com.project.sigte2.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.sigte2.domain.DiasSemana;
import com.project.sigte2.service.DiaSemanaService;

@Controller
@RequestMapping("/diaAulas")
public class DiaAulaSemana {

	@Autowired
	private DiaSemanaService service;

	@GetMapping("/cadastrar")
	public String cadastrar(DiasSemana diaAula) {
		return "diaAulas/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("diaAulas", service.buscarTodos());
		return "diaAulas/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid DiasSemana diaAula, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "diaAulas/cadastro";
		}
		service.salvar(diaAula);
		attr.addFlashAttribute("success", "Dia da Semana Inserido com Sucesso!");
		return "redirect:/diaAulas/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("diaAula", service.buscarPorId(id));
		return "diaAulas/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid DiasSemana diaAula, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "diaAula/cadastro";
		}
		service.editar(diaAula);
		attr.addFlashAttribute("success", "Dia da Semana Alterado com Sucesso!");
		return "redirect:/diaAulas/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.DiaSemanaTemTransportes(id)) {
			model.addAttribute("fail",
					"Dia da Semana não pode ser excluido, existe Agendamento de Transporte(s) relacionados");
		} else {
			service.excluir(id);
			model.addAttribute("success", "DiaAula excluido com Sucesso!");
		}
		return listar(model);
	}
}
