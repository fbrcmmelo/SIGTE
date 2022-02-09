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

import com.project.sigte2.domain.Administrador;
import com.project.sigte2.domain.Pessoa;
import com.project.sigte2.domain.UF;
import com.project.sigte2.service.AdministradorService;
import com.project.sigte2.service.PessoaService;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {

	@Autowired
	private AdministradorService aService;
	@Autowired
	private PessoaService pService;
	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * binder.addValidators(new AdministradorValidator()); }
	 */

	@GetMapping("/cadastrar")
	public String cadastrar(Administrador administrador, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "administrador/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa")); 
		model.addAttribute("administrador", aService.buscarTodos());
		return "administrador/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Administrador administrador, BindingResult result, RedirectAttributes attr) throws Exception {
		if (result.hasErrors()) {
			
			return "administrador/cadastro";
		}
	
		try {
		Pessoa oPessoa = new Pessoa();
		oPessoa.setIdPessoa(administrador.getIdPessoa());
		oPessoa.setNomePessoa(administrador.getNomePessoa());
		oPessoa.setEmailPessoa(administrador.getEmailPessoa());
		oPessoa.setSenhaPessoa(administrador.getSenhaPessoa());
		oPessoa.setRgPessoa(administrador.getRgPessoa());
		oPessoa.setTipoPessoa("A");
		pService.salvar(oPessoa);
		}catch (Exception e) {
			throw new Exception("Erro na Controller ao inserir Pessoa admn "+e.getMessage());
		}
		finally {
			try {
				Administrador adm = new Administrador();
				adm.setIdPessoa(administrador.getIdPessoa());
				adm.setEndereco(administrador.getEndereco());
				aService.salvar(adm);
				attr.addFlashAttribute("success", "Administrador Inserido com Sucesso!");
			} catch (Exception e2) {
				throw new Exception("Erro na Controller ao inserir Administrador " + e2.getMessage());
			}
		}
		return "redirect:/administradores/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("administrador", aService.buscarPorId(id));

		return "administrador/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Administrador administrador, BindingResult result, RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return "administrador/cadastro";
		}
		aService.editar(administrador);
		attr.addFlashAttribute("success", "Administrador Alterado com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "redirect:/administradores/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model,
			HttpServletRequest request) {
		aService.excluir(id);
		model.addAttribute("success", "Administrador excluido com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "redirect:/alunos/listar";
	}

	@GetMapping("/index")
	private String indexAluno(ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "home";
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

}
