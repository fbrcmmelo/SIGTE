package com.project.sigte2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.project.sigte2.domain.Funcionario;

public interface FuncionarioDao {

	void save(Funcionario funcionario);

	void update(Funcionario funcionario);

	void delete(Long id);

	Funcionario findById(Long id);

	List<Funcionario> findAll();

	List<Funcionario> findByNome(String nome);

	List<Funcionario> findByCargoId(Long id);

	@Query("select * from Funcionario where emaiPessoa= :emailPessoa and senhaPessoa = :senhaPessoa")
	Funcionario findByEmailPassword(String emailPessoa, String senhaPessoa);
}
