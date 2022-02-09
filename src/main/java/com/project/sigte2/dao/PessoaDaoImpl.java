package com.project.sigte2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sigte2.domain.Pessoa;

@Repository
public class PessoaDaoImpl extends AbstractDao<Pessoa, Long> implements PessoaDao {

	@Override
	public List<Pessoa> getByEmailPassword(String emailPessoa, String senhaPessoa) {
		return createQuery("select p from Pessoa p where p.emailPessoa = ?1 and p.senhaPessoa = ?2", emailPessoa,
				senhaPessoa);
	}
}
