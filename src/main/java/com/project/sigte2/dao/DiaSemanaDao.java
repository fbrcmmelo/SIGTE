package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.DiaSemana;

public interface DiaSemanaDao {

	void save(DiaSemana diaAula);

	void update(DiaSemana diaAula);

	void delete(Long id);

	DiaSemana findById(Long id);

	List<DiaSemana> findAll();
}
