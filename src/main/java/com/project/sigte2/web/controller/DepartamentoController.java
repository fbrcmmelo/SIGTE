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

import com.project.sigte2.domain.Departamento;
import com.project.sigte2.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento, ModelMap model, HttpServletRequest request) {

		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {

		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("departamentos", service.buscarTodos());
		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "departamento/cadastro";
		}
		service.salvar(departamento);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Departamento Inserido com Sucesso!");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id,ModelMap model, HttpServletRequest request) {
		model.addAttribute("departamento", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "departamento/cadastro";
		}
		service.editar(departamento);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Departamento Alterado com Sucesso!");
		return "redirect:/departamentos/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		if (service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não pode ser excluido, existe Cargo(s) relacionados");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Departamento excluido com Sucesso!");
		}
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "departamento/lista";
	}
}
