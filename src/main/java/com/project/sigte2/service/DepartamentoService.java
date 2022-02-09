package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Departamento;

public interface DepartamentoService {

	void salvar(Departamento departamento);

	void editar(Departamento departamento);

	void excluir(Long id);

	Departamento buscarPorId(Long id);

	List<Departamento> buscarTodos();

	boolean departamentoTemCargos(Long id);
}
