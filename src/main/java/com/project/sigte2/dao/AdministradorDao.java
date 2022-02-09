package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Administrador;

public interface AdministradorDao {

	void save(Administrador adm);

	void update(Administrador adm);

	void delete(Long id);

	Administrador findById(Long idPessoa);

	List<Administrador> findAll();

}
