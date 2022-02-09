package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.DiaSemana;

public interface DiaSemanaService {

	void salvar(DiaSemana diaSemana);

	void editar(DiaSemana diaSemana);

	void excluir(Long id);

	DiaSemana buscarPorId(Long id);

	List<DiaSemana> buscarTodos();

	boolean DiaSemanaTemTransportes(Long id);
}
