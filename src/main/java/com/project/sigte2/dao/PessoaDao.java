package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Pessoa;

public interface PessoaDao {

	void save(Pessoa pessoa);

	void update(Pessoa pessoa);

	void delete(Long id);

	Pessoa findById(Long id);

	List<Pessoa> findAll();

	List<Pessoa> getByEmailPassword(String emailPessoa, String senhaPessoa);
}
