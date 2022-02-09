package com.project.sigte2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.sigte2.domain.Pessoa;

@Controller
public class LoginController {

	@GetMapping("/")
	public String login(Pessoa pessoa) {
		return "login";
	}
}
