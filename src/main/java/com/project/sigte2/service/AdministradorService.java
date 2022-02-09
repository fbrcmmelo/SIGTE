package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Administrador;

public interface AdministradorService {

	void salvar(Administrador adm);

	void editar(Administrador adm);

	void excluir(Long idPessoa);

	Administrador buscarPorId(Long idPessoa);

	List<Administrador> buscarTodos();

}
