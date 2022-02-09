package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Escola;

public interface EscolaService {

	void salvar(Escola escola);

	void editar(Escola escola);

	void excluir(Long id);

	Escola buscarPorId(Long id);

	List<Escola> buscarTodos();

	boolean escolaTemAlunos(Long id);

}
