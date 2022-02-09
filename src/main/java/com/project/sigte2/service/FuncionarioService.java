package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Funcionario;

public interface FuncionarioService {

	void salvar(Funcionario funcionario);

	void editar(Funcionario funcionario);

	void excluir(Long id);

	Funcionario buscarPorId(Long id);

	List<Funcionario> buscarTodos();

	List<Funcionario> buscarPorNome(String nome);

	List<Funcionario> buscarPorCargo(Long id);

	Funcionario buscarPorEmailSenha(String emailPessoa, String senhaPessoa);
}
