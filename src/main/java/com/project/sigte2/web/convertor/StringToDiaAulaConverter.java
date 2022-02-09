package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.DiaSemana;
import com.project.sigte2.service.DiaSemanaService;

@Component
public class StringToDiaAulaConverter implements Converter<String, DiaSemana> {

	@Autowired
	private DiaSemanaService service;

	@Override
	public DiaSemana convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}
}
