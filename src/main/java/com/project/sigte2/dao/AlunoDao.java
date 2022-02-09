package com.project.sigte2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.project.sigte2.domain.Aluno;

public interface AlunoDao {

	void save(Aluno aluno);

	void update(Aluno aluno);

	void delete(Long id);

	Aluno findById(Long id);

	List<Aluno> findAll();

	List<Aluno> findByNome(String nome);

	List<Aluno> findByEscola(Long id);

	@Query("select * from Aluno where emaiPessoa= :emailPessoa and senhaPessoa = :senhaPessoa")
	Aluno findByEmailPassword(String emailPessoa, String senhaPessoa);

	@Query("select * from Aluno where emailPessoa = :emailPessoa")
	Object findByEmail(String emailPessoa);

}
