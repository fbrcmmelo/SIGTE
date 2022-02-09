package com.project.sigte2.web.controller;

import java.util.List;

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

import com.project.sigte2.domain.AgendaTransporte;
import com.project.sigte2.domain.Aluno;
import com.project.sigte2.domain.DiaSemana;
import com.project.sigte2.domain.Escola;
import com.project.sigte2.service.AgendaTransporteService;
import com.project.sigte2.service.AlunoService;
import com.project.sigte2.service.DiaSemanaService;
import com.project.sigte2.service.EscolaService;

@Controller
@RequestMapping("/agendaTransportes")
public class AgendaTransporteController {

	@Autowired
	private AgendaTransporteService service;
	@Autowired
	private DiaSemanaService diaAulaService;
	@Autowired
	private AlunoService aService;
	@Autowired
	private EscolaService eService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(AgendaTransporte agendaTransporte, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "agendaTransportes/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa")); 

		model.addAttribute("agendaTransporte", service.buscarTodos());
		return "agendaTransportes/lista";
		
	}
	
	@GetMapping("/listar/{idD}/{idE}")
	public String listarAlunos(@PathVariable("idD") Long idDia,@PathVariable("idE") Long idEscola,
			ModelMap model, HttpServletRequest request) {
		model.addAttribute("agendaTransporte", service.buscarAlunosPorIdDiaIdEscola(idDia, idEscola));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "agendaTransportes/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid AgendaTransporte agendaTransporte, BindingResult result, 
			RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "agendaTransportes/cadastro";
		}
		service.salvar(agendaTransporte);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Agenda Inserido com Sucesso!");
		return "redirect:/agendaTransportes/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("agendaTransporte", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "agendaTransportes/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid AgendaTransporte agendaTransporte, BindingResult result, RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "agendaTransportes/cadastro";
		}
		service.editar(agendaTransporte);
		attr.addFlashAttribute("success", "Agenda Alterado com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "redirect:/agendaTransportes/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model,
			HttpServletRequest request) {
		service.excluir(id);
		model.addAttribute("success", "Agenda excluido com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "agendaTransportes/lista";
	}

	@ModelAttribute("diaSemana")
	public List<DiaSemana> getDiaSemana() {
		return diaAulaService.buscarTodos();
	}
	
	@ModelAttribute("aluno")
	public List<Aluno> getAluno() {
		return aService.buscarTodos();
	}
	
	@ModelAttribute("escola")
	public List<Escola> getEscola() {
		return eService.buscarTodos();
	}
}
