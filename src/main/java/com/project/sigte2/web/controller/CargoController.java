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

import com.project.sigte2.domain.Cargo;
import com.project.sigte2.domain.Departamento;
import com.project.sigte2.service.CargoService;
import com.project.sigte2.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService service;
	@Autowired
	private DepartamentoService dService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "cargo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("cargos", service.buscarTodos());
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "cargo/cadastro";
		}
		service.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com Sucesso !");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("cargo", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "cargo/cadastro";
	};

	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr, ModelMap model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "cargo/cadastro";
		}
		service.editar(cargo);
		attr.addFlashAttribute("success", "Cargo alterado com sucesso !");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "redirect:/cargos/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		if (service.cargoTemFuncionarios(id)) {
			model.addAttribute("fail", "Cargo não pode ser excluido, existe Funcionario(s) relacionados");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Cargo excluido com Sucesso !");
		}
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));

		return "cargo/lista";
	};

	@ModelAttribute("departamentos")
	public List<Departamento> listarDepartamento() {
		return dService.buscarTodos();
	};
}
