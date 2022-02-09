package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.Departamento;
import com.project.sigte2.service.DepartamentoService;

@Component
public class StringToDepertamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService service;

	@Override
	public Departamento convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);

		return service.buscarPorId(id);
	}

}
