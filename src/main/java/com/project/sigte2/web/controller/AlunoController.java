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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.sigte2.domain.Aluno;
import com.project.sigte2.domain.Escola;
import com.project.sigte2.domain.UF;
import com.project.sigte2.service.AgendaTransporteService;
import com.project.sigte2.service.AlunoServiceImpl;
import com.project.sigte2.service.EscolaService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoServiceImpl aService;
	@Autowired
	private EscolaService eService;
	@Autowired
	private AgendaTransporteService service;
	@GetMapping("/cadastroRemoto")
	public String cadastroRemoto(Aluno aluno) {
		return "alunos/cadastrarRemoto";
	}

	@GetMapping("/cadastrar")
	public String cadastrar(HttpServletRequest request, Aluno aluno, ModelMap model) {
		model.addAttribute("pessoa", request.getSession().getAttribute("aluno"));
		return "alunos/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("alunos", aService.buscarTodos());
		return "alunos/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr, HttpServletRequest request,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "alunos/cadastro";
		}
		Aluno oAluno = new Aluno();
		oAluno.setIdPessoa(aluno.getIdPessoa());
		oAluno.setEmailPessoa(aluno.getEmailPessoa());
		oAluno.setNomePessoa(aluno.getNomePessoa());
		oAluno.setRgPessoa(aluno.getRgPessoa());
		oAluno.setGrauEscolar(aluno.getGrauEscolar());
		oAluno.setSenhaPessoa(aluno.getSenhaPessoa());
		oAluno.setTipoPessoa("AL");
		oAluno.setDataNascimento(aluno.getDataNascimento());
		oAluno.setEscola(aluno.getEscola());
		oAluno.setEndereco(aluno.getEndereco());
		aService.salvar(oAluno);

		attr.addFlashAttribute("success", "Aluno Inserido com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/alunos/cadastrar";
	}

	@PostMapping("/salvarRemoto")
	public String salvarRemoto(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr,
			HttpServletRequest request) {
		if (result.hasErrors()) {

			return "alunos/cadastrarRemoto";
		}
		Aluno oAluno = new Aluno();
		oAluno.setIdPessoa(aluno.getIdPessoa());
		oAluno.setEmailPessoa(aluno.getEmailPessoa());
		oAluno.setNomePessoa(aluno.getNomePessoa());
		oAluno.setRgPessoa(aluno.getRgPessoa());
		oAluno.setGrauEscolar(aluno.getGrauEscolar());
		oAluno.setSenhaPessoa(aluno.getSenhaPessoa());
		oAluno.setTipoPessoa("AL");
		oAluno.setDataNascimento(aluno.getDataNascimento());
		oAluno.setEscola(aluno.getEscola());
		oAluno.setEndereco(aluno.getEndereco());
		aService.salvar(oAluno);

		request.getSession().setAttribute("pessoa", oAluno);
		request.getSession().setAttribute("saudacao", "Olá, seja Bem-vindo Sr(a) " + oAluno.getNomePessoa());
		request.getSession().setMaxInactiveInterval(300);
		attr.addFlashAttribute("success", "Aluno Inserido com Sucesso!");
		attr.addFlashAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("saudacao", request.getSession().getAttribute("saudacao"));

		return "redirect:/alunos/index";
	}

	@GetMapping("/editar/{idPessoa}")
	public String preEditar(@PathVariable("idPessoa") Long idPessoa, ModelMap model, HttpServletRequest request) {
		model.addAttribute("aluno", aService.buscarPorId(idPessoa));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "alunos/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr, HttpServletRequest request,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "alunos/cadastro";
		}
		Aluno oAluno = new Aluno();
		oAluno.setIdPessoa(aluno.getIdPessoa());
		oAluno.setEmailPessoa(aluno.getEmailPessoa());
		oAluno.setNomePessoa(aluno.getNomePessoa());
		oAluno.setRgPessoa(aluno.getRgPessoa());
		oAluno.setGrauEscolar(aluno.getGrauEscolar());
		oAluno.setSenhaPessoa(aluno.getSenhaPessoa());
		oAluno.setTipoPessoa("AL");
		oAluno.setDataNascimento(aluno.getDataNascimento());
		oAluno.setEscola(aluno.getEscola());
		oAluno.setEndereco(aluno.getEndereco());
		aService.editar(oAluno);

		attr.addFlashAttribute("success", "Aluno Alterado com Sucesso!");
		request.getSession().setAttribute("pessoa", oAluno);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/alunos/cadastrar";
	};

	@GetMapping("/excluir/{idPessoa}")
	public String excluir(@PathVariable("idPessoa") Long idPessoa, ModelMap model, HttpServletRequest request) {
		if (aService.alunoTemAgendamentos(idPessoa)) {
			model.addAttribute("fail", "Aluno não pode ser excluido, existe Agendamento(s) relacionados");
		} else {
			aService.excluir(idPessoa);
			model.addAttribute("success", "Aluno excluido com Sucesso!");
		}
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "alunos/lista";
	}

	@GetMapping("/buscar/nomePessoa")
	public String getPorNome(@RequestParam("nomePessoa") String nomePessoa, ModelMap model) {
		model.addAttribute("alunos", aService.buscarPorNome(nomePessoa));
		return "alunos/lista";
	}

	@GetMapping("/buscar/escola")
	public String getPorEscola(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("alunos", aService.buscarPorEscola(id));
		return "alunos/lista";
	}

	@ModelAttribute("escolas")
	public List<Escola> getEscolas() {
		return eService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

	@GetMapping("/index")
	private String indexAluno(ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/home";
	}
	
	@GetMapping("/listarAgenda/{id}")
	public String listarAgenda(@PathVariable("id") Long id,ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa")); 

		model.addAttribute("agendaTransporte", service.buscarPorIdAluno(id));
		return "alunos/listaAgendamento";
		
	}

	@GetMapping("/cadastroTransporte")
	public String agendaTransporte() {
		return "alunos/agendarTransporte";
	}

}