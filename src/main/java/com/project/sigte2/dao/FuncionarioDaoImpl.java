package com.project.sigte2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sigte2.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	@Override
	public List<Funcionario> findByNome(String nome) {

		return createQuery("select f from Funcionario f where f.nome like concat('%',?1,'%') ", nome);
	}

	@Override
	public List<Funcionario> findByCargoId(Long id) {
		return createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
	}

	@Override
	public Funcionario findByEmailPassword(String emailPessoa, String senhaPessoa) {
		Funcionario funcionario = new Funcionario();
		return funcionario;
	}

}
