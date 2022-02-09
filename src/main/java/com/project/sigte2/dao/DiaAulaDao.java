package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.DiasSemana;

public interface DiaAulaDao {

	void save(DiasSemana diaAula);

	void update(DiasSemana diaAula);

	void delete(Long id);

	DiasSemana findById(Long id);

	List<DiasSemana> findAll();
}
