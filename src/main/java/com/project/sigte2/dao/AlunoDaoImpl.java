package com.project.sigte2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sigte2.domain.Aluno;

@Repository
public class AlunoDaoImpl extends AbstractDao<Aluno, Long> implements AlunoDao {

	@Override
	public List<Aluno> findByNome(String nomePessoa) {
		return createQuery("select a from Aluno a where a.nomePessoa = ?1", nomePessoa);
	}

	@Override
	public List<Aluno> findByEscola(Long id) {
		return createQuery("select a from Aluno a where a.escola.id = ?1", id);
	}

	@Override
	public Object findByEmail(String emailPessoa) {
		Aluno aluno = new Aluno();
		return aluno;
	}

	@Override
	public Aluno findByEmailPassword(String emailPessoa, String senhaPessoa) {
		Aluno aluno = new Aluno();
		return aluno;
	}

}
