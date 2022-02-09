package com.project.sigte2.web.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.sigte2.domain.Aluno;
import com.project.sigte2.service.AlunoServiceImpl;

@Component
public class StringToAlunosConverter implements Converter<String, Aluno> {

	@Autowired
	private AlunoServiceImpl service;

	@Override
	public Aluno convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}
