package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Pessoa;

public interface PessoaService {

	void salvar(Pessoa pessoa);

	void editar(Pessoa pessoa);

	void excluir(Long idPessoa);

	Pessoa buscarPorId(Long idPessoa);

	List<Pessoa> listarTodos();

	List<Pessoa> buscarPorEmailSenha(String emailPessoa, String senhaPessoa);

}
