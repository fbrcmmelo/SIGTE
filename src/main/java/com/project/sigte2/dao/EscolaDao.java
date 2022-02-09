package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Escola;

public interface EscolaDao {

	void save(Escola Escola);

	void update(Escola Escola);

	void delete(Long id);

	Escola findById(Long id);

	List<Escola> findAll();
}
