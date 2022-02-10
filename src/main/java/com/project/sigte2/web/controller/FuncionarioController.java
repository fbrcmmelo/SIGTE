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

import com.project.sigte2.domain.Cargo;
import com.project.sigte2.domain.Funcionario;
import com.project.sigte2.domain.UF;
import com.project.sigte2.service.CargoService;
import com.project.sigte2.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService fService;
	@Autowired
	private CargoService cService;

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * binder.addValidators(new FuncionarioValidator()); }
	 */

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("funcionarios", fService.buscarTodos());
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "funcionario/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "funcionario/cadastro";
		}
		Funcionario oFuncionario = new Funcionario();
		oFuncionario.setIdPessoa(funcionario.getIdPessoa());
		oFuncionario.setEmailPessoa(funcionario.getEmailPessoa());
		oFuncionario.setNomePessoa(funcionario.getNomePessoa());
		oFuncionario.setRgPessoa(funcionario.getRgPessoa());
		oFuncionario.setSenhaPessoa(funcionario.getSenhaPessoa());
		oFuncionario.setTipoPessoa("F");
		oFuncionario.setDataEntrada(funcionario.getDataEntrada());
		oFuncionario.setDataSaida(funcionario.getDataSaida());
		oFuncionario.setSalario(funcionario.getSalario());
		oFuncionario.setCargo(funcionario.getCargo());
		oFuncionario.setEndereco(funcionario.getEndereco());
		fService.salvar(oFuncionario);

		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Funcionario Inserido com Sucesso !");
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("funcionario", fService.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "funcionario/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "funcionario/cadastro";
		}
		Funcionario oFuncionario = new Funcionario();
		oFuncionario.setIdPessoa(funcionario.getIdPessoa());
		oFuncionario.setEmailPessoa(funcionario.getEmailPessoa());
		oFuncionario.setNomePessoa(funcionario.getNomePessoa());
		oFuncionario.setRgPessoa(funcionario.getRgPessoa());
		oFuncionario.setSenhaPessoa(funcionario.getSenhaPessoa());
		oFuncionario.setTipoPessoa("F");
		oFuncionario.setDataEntrada(funcionario.getDataEntrada());
		oFuncionario.setDataSaida(funcionario.getDataSaida());
		oFuncionario.setSalario(funcionario.getSalario());
		oFuncionario.setCargo(funcionario.getCargo());
		oFuncionario.setEndereco(funcionario.getEndereco());
		fService.editar(oFuncionario);

		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Funcionario Alterado com Sucesso !");
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		fService.excluir(id);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("success", "Funcionario excluido com Sucesso!");

		return "funcionario/lista";
	}

	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("funcionarios", fService.buscarPorNome(nome));
		return "funcionario/lista";
	}

	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("funcionarios", fService.buscarPorCargo(id));
		return "funcionario/lista";
	}

	@GetMapping("/index")
	private String indexAluno(ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "home";
	}

	@ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		return cService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}
}
