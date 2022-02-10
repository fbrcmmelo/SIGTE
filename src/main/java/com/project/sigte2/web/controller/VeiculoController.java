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

import com.project.sigte2.domain.Veiculo;
import com.project.sigte2.service.VeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	VeiculoService service;

	@GetMapping("/cadastrar")
	public String cadastro(Veiculo veiculo, ModelMap model, HttpServletRequest request) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "veiculos/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpServletRequest request) {
		model.addAttribute("veiculos", service.buscarTodos());
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "veiculos/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "veiculos/cadastro";
		}
		service.salvar(veiculo);
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		attr.addFlashAttribute("success", "Veiculo Inserido com Sucesso!");
		return "redirect:/veiculos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		model.addAttribute("veiculo", service.buscarPorId(id));
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "veiculos/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attr,
			ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
			return "veiculos/cadastro";
		}
		service.editar(veiculo);
		attr.addFlashAttribute("success", "Veiculo Alterado com Sucesso!");
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		return "redirect:/veiculos/cadastrar";
	};

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		if (service.veiculoTemAgendamento(id)) {
			model.addAttribute("fail", "Veiculo não pode ser excluido, existe Transporte(s) relacionados");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Veiculo excluido com Sucesso!");
		}
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
	return "veiculos/lista";
	}

}
