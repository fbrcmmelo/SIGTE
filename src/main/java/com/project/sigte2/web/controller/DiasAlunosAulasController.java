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

import com.project.sigte2.domain.DiasAlunosAulas;
import com.project.sigte2.service.DiaAlunosAulasService;

@Controller
@RequestMapping("/diasAlunosAulas")
public class DiasAlunosAulasController {

	@Autowired
	private DiaAlunosAulasService service;

	@GetMapping("/cadastrar")
	public String cadastrar(DiasAlunosAulas diasAlunosAulas, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diasAlunosAulas/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("diasAlunosAulass", service.buscarTodos());
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diasAlunosAulas/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid DiasAlunosAulas diasAlunosAulas, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
			if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "diasAlunosAulas/cadastro";
		}
		service.salvar(diasAlunosAulas);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Dia da Semana Inserido com Sucesso!");
		return "redirect:/diasAlunosAulas/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("diasAlunosAulas", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diasAlunosAulas/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid DiasAlunosAulas diasAlunosAulas, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "diasAlunosAulas/cadastro";
		}
		service.editar(diasAlunosAulas);
		attr.addFlashAttribute("success", "Dia da Semana Alterado com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/diasAlunosAulas/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
			service.excluir(id);
			model.addAttribute("success", "Dia de Aula excluido com Sucesso!");
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/diasAlunosAulas/listar";
	}
	
	@GetMapping("/listar/{idD}/{idE}")
	public String listarAlunos(@PathVariable("idD") Long idDia,@PathVariable("idE") Long idEscola,
			ModelMap model, HttpServletRequest request) {
		model.addAttribute("diasAlunosAulas", service.buscarAlunosPorIdDiaIdEscola(idDia, idEscola));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "diasAlunosAulas/cadastro";
	}
	
}
