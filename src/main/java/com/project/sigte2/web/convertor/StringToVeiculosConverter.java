package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.Veiculo;
import com.project.sigte2.service.VeiculoService;

@Component
public class StringToVeiculosConverter implements Converter<String, Veiculo> {

	@Autowired
	private VeiculoService service;

	@Override
	public Veiculo convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}
