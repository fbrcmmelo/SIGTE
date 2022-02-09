package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.Escola;
import com.project.sigte2.service.EscolaService;

@Component
public class StringToEscolasConverter implements Converter<String, Escola> {

	@Autowired
	private EscolaService service;

	@Override
	public Escola convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}
}
