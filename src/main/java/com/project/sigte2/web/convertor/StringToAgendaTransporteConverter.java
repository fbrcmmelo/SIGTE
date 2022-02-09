package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.AgendaTransporte;
import com.project.sigte2.service.AgendaTransporteService;

@Component
public class StringToAgendaTransporteConverter implements Converter<String, AgendaTransporte> {

	@Autowired
	private AgendaTransporteService service;

	@Override
	public AgendaTransporte convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}
}
