package com.project.sigte2.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.sigte2.domain.Escola;
import com.project.sigte2.domain.UF;
import com.project.sigte2.service.EscolaService;

@Controller
@RequestMapping("/escolas")
public class EscolaController {

	@Autowired
	EscolaService service;

	@GetMapping("/cadastrar")
	public String cadastro(Escola escola, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "escolas/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("escolas", service.buscarTodos());
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "escolas/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Escola escola, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "escolas/cadastro";
		}
		service.salvar(escola);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Escola Inserido com Sucesso!");
		return "redirect:/escolas/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("escola", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "escolas/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Escola escola, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "escolas/cadastro";
		}
		service.editar(escola);
		attr.addFlashAttribute("success", "Escola Alterado com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/escolas/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		if (service.escolaTemAlunos(id)) {
			model.addAttribute("fail", "Escola não pode ser excluido, existe Aluno(s) relacionados");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Escola excluido com Sucesso!");
		}
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "escolas/lista";
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}
}
