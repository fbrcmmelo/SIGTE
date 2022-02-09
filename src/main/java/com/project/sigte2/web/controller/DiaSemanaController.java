package com.project.sigte2.web.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.project.sigte2.domain.DiaSemana;
import com.project.sigte2.service.DiaSemanaService;

@Controller
@RequestMapping("/diaSemana")
public class DiaSemanaController{

	@Autowired
	private DiaSemanaService service;

	@GetMapping("/cadastrar")
	public String cadastrar(DiaSemana diaSemana, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diaSemana/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("diaSemana", service.buscarTodos());
		return "diaSemana/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid DiaSemana diaSemana, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "diaSemana/cadastro";
		}
		service.salvar(diaSemana);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Dia da Semana Inserido com Sucesso!");
		return "redirect:/diaSemana/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("diaSemana", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diaSemana/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid DiaSemana diaSemana, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "diaSemana/cadastro";
		}
		service.editar(diaSemana);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Dia da Semana Alterado com Sucesso!");
		return "redirect:/diaSemana/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		if (service.DiaSemanaTemTransportes(id)) {
			model.addAttribute("fail",
					"Dia da Semana não pode ser excluido, existe Agendamento de Transporte(s) relacionados");
		} else {
			service.excluir(id);
			model.addAttribute("success", "diaSemana excluido com Sucesso!");
		}
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diaSemana/lista";
	}
}
