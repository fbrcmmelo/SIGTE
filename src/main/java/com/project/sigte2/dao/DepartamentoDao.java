package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Departamento;

public interface DepartamentoDao {

	void save(Departamento departamento);

	void update(Departamento departamento);

	void delete(Long id);

	Departamento findById(Long id);

	List<Departamento> findAll();
}
