package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Veiculo;

public interface VeiculoDao {

	void save(Veiculo veiculo);

	void update(Veiculo veiculo);

	void delete(Long id);

	Veiculo findById(Long id);

	List<Veiculo> findAll();
}
