package com.project.sigte2.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.sigte2.domain.Administrador;
import com.project.sigte2.domain.Aluno;
import com.project.sigte2.domain.Escola;
import com.project.sigte2.domain.Funcionario;
import com.project.sigte2.domain.Pessoa;
import com.project.sigte2.domain.UF;
import com.project.sigte2.service.AdministradorService;
import com.project.sigte2.service.AlunoService;
import com.project.sigte2.service.EscolaService;
import com.project.sigte2.service.FuncionarioService;
import com.project.sigte2.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private AdministradorService admService;
	@Autowired
	private FuncionarioService fService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private PessoaService pService;

	private Pessoa oPessoa;
	@Autowired
	private EscolaService eService;

	@GetMapping("/logar/emailPessoa/senhaPessoa")
	public String logar(@RequestParam("emailPessoa") String emailPessoa,
			@RequestParam("senhaPessoa") String senhaPessoa, RedirectAttributes attr, HttpServletRequest request,
			ModelMap model) {
		if (emailPessoa.isEmpty()) {
			attr.addFlashAttribute("fail", "Digite o Email");
			return "redirect:/";
		} else if (senhaPessoa.isEmpty()) {
			attr.addFlashAttribute("fail", "Digite a Senha");
			return "redirect:/";
		}

		List<Pessoa> listPessoas = pService.buscarPorEmailSenha(emailPessoa, senhaPessoa);
		if (listPessoas != null && !listPessoas.isEmpty()) {
			oPessoa = new Pessoa();
			oPessoa = listPessoas.get(0);
			request.getSession().setAttribute("pessoa", oPessoa);
			request.getSession().setAttribute("saudacao", "Olá, seja Bem-vindo Sr(a) " + oPessoa.getNomePessoa());
			request.getSession().setMaxInactiveInterval(300);
			Long idPessoa = oPessoa.getIdPessoa();

			if (oPessoa.getTipoPessoa().equals("A")) {
				Administrador adm = new Administrador();

				adm = admService.buscarPorId(idPessoa);
				request.getSession().setAttribute("administrador", adm);

				attr.addFlashAttribute("administrador", request.getSession().getAttribute("adm"));
				attr.addFlashAttribute("pessoa", request.getSession().getAttribute("pessoa"));
				attr.addFlashAttribute("saudacao", request.getSession().getAttribute("saudacao"));
				return "redirect:/administradores/index";
			} else if (oPessoa.getTipoPessoa().equals("F")) {
				Funcionario funcionario = new Funcionario();
				funcionario = fService.buscarPorId(idPessoa);
				request.getSession().setAttribute("funcionario", funcionario);

				attr.addFlashAttribute("funcionario", request.getSession().getAttribute("funcionario"));
				attr.addFlashAttribute("pessoa", request.getSession().getAttribute("pessoa"));
				attr.addFlashAttribute("saudacao", request.getSession().getAttribute("saudacao"));
				return "redirect:/funcionarios/index";
			} else if (oPessoa.getTipoPessoa().equals("AL")) {
				Aluno aluno = new Aluno();
				aluno = alunoService.buscarPorId(idPessoa);
				request.getSession().setAttribute("aluno", aluno);

				attr.addFlashAttribute("pessoa", request.getSession().getAttribute("pessoa"));
				attr.addFlashAttribute("aluno", request.getSession().getAttribute("aluno"));
				attr.addFlashAttribute("saudacao", request.getSession().getAttribute("saudacao"));
				model.addAttribute("saudacao", request.getSession().getAttribute("saudacao"));
				return "redirect:/alunos/index";
			} else {
				attr.addFlashAttribute("fail", "Usuário esta com o Tipo indefinido, Contate o Administrador");
				return "redirect:/";
			}
		} else

			attr.addFlashAttribute("fail", "Usuário não encontrado, tente novamente");
		return "redirect:/";
	}

	@GetMapping("editar/{idPessoa}")
	public String preEditarPessoa(@PathVariable("idPessoa") Long idPessoa, ModelMap model, HttpServletRequest request) {
		Administrador adm = admService.buscarPorId(idPessoa);
		Aluno aluno = alunoService.buscarPorId(idPessoa);
		Funcionario funcionario = fService.buscarPorId(idPessoa);

		if (aluno != null) {
			model.addAttribute("aluno", aluno);
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "alunos/cadastro";
		} else if (funcionario != null) {
			model.addAttribute("funcionario", funcionario);
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "funcionario/cadastro";
		} else
			model.addAttribute("administrador", adm);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "adm/cadastro";
	}
	
	@ModelAttribute("escolas")
	public List<Escola> getEscolas() {
		return eService.buscarTodos();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
