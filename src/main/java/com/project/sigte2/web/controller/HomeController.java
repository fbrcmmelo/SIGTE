package com.project.sigte2.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(HttpServletRequest request, ModelMap model) {
		model.addAttribute("pessoa", request.getSession().getAttribute("pessoa"));
		model.addAttribute("saudacao", request.getSession().getAttribute("saudacao"));
		return "home";
	}
}
