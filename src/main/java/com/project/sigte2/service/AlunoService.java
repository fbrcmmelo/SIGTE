package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Aluno;

public interface AlunoService {

	void salvar(Aluno aluno);

	void editar(Aluno aluno);

	void excluir(Long id);

	Aluno buscarPorId(Long id);

	List<Aluno> buscarTodos();

	List<Aluno> buscarPorNome(String nomePessoa);

	List<Aluno> buscarPorEscola(Long id);

	Aluno buscarPorEmailSenha(String emailPessoa, String senhaPessoa);
	
	boolean alunoTemAgendamentos(Long id);

}
