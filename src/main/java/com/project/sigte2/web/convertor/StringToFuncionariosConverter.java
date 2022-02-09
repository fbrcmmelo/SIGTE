package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.Funcionario;
import com.project.sigte2.service.FuncionarioService;

@Component
public class StringToFuncionariosConverter implements Converter<String, Funcionario> {

	@Autowired
	private FuncionarioService fService;

	@Override
	public Funcionario convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return fService.buscarPorId(id);
	}

}
