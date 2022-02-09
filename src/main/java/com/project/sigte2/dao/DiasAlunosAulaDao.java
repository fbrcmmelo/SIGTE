package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.DiasAlunosAulas;

public interface DiasAlunosAulaDao {

	void save(DiasAlunosAulas diasAlunosAulas);

	void update(DiasAlunosAulas diasAlunosAulas);

	void delete(Long id);

	DiasAlunosAulas findById(Long id);

	List<DiasAlunosAulas> findAll();

	List<DiasAlunosAulas> findAlunoByIdDiaIdEscola(Long idDia, Long idEscola);
	
}
