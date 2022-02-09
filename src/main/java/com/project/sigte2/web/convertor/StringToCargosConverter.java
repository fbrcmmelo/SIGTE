package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.Cargo;
import com.project.sigte2.service.CargoService;

@Component
public class StringToCargosConverter implements Converter<String, Cargo> {

	@Autowired
	private CargoService cService;

	@Override
	public Cargo convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return cService.buscarPorId(id);

	}
}
