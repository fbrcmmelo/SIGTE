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

import com.project.sigte2.domain.Aluno;
import com.project.sigte2.domain.DiaSemana;
import com.project.sigte2.domain.Escola;
import com.project.sigte2.domain.Funcionario;
import com.project.sigte2.domain.Transporte;
import com.project.sigte2.domain.Veiculo;
import com.project.sigte2.service.AlunoService;
import com.project.sigte2.service.DiaSemanaService;
import com.project.sigte2.service.EscolaService;
import com.project.sigte2.service.FuncionarioService;
import com.project.sigte2.service.TransporteService;
import com.project.sigte2.service.VeiculoService;

@Controller
@RequestMapping("/transportes")
public class TransporteController {

	@Autowired
	private TransporteService tService;

	@Autowired
	private VeiculoService vService;

	@Autowired
	private EscolaService eService;

	@Autowired
	private DiaSemanaService dService;

	@Autowired
	private AlunoService aService;

	@Autowired
	private FuncionarioService fService;

	@GetMapping("/cadastrar")
	public String cadastro(Transporte transporte, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "transportes/cadastro";
	}

	@GetMapping("/listar")
	public String listar( ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("transportes", tService.buscarTodos());
		return "transportes/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Transporte transporte, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "transportes/cadastro";
		}
		tService.salvar(transporte);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Transporte Inserido com Sucesso!");
		return "redirect:/transportes/cadastrar";
	}
	
	@GetMapping("/listar/{id}")
	public String listarTransporte(@PathVariable("id") Long id, ModelMap model,
			HttpServletRequest request) {

		model.addAttribute("transportes", tService.buscarTransportePorIdDia(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "transportes/lista";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("transporte", tService.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "transportes/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Transporte transporte, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "transportes/cadastro";
		}
		tService.editar(transporte);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Transporte Alterado com Sucesso!");
		return "redirect:/transportes/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		tService.excluir(id);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("success", "Transporte excluido com Sucesso!");

		return "transportes/lista";
	}

	@ModelAttribute("veiculos")
	public List<Veiculo> getVeiculos() {
		return vService.buscarTodos();
	}

	@ModelAttribute("escolas")
	public List<Escola> getescolas() {
		return eService.buscarTodos();
	}

	@ModelAttribute("diaSemana")
	public List<DiaSemana> getdiaSemana() {
		return dService.buscarTodos();
	}

	@ModelAttribute("alunos")
	public List<Aluno> getAlunos() {
		return aService.buscarTodos();
	}

	@ModelAttribute("funcionarios")
	public List<Funcionario> getFuncionarios() {
		return fService.buscarTodos();
	}
}
