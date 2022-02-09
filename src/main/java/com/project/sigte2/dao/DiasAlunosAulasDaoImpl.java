package com.project.sigte2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sigte2.domain.DiasAlunosAulas;

@Repository
public class DiasAlunosAulasDaoImpl extends AbstractDao<DiasAlunosAulas, Long> implements DiasAlunosAulaDao{

	@Override
	public List<DiasAlunosAulas> findAlunoByIdDiaIdEscola(Long idDia, Long idEscola) {
		return createQuery("select d from DiasAlunosAulas d where d.diaSemana.id = ?1 and d.escola.id = ?2", idDia, idEscola);
	}

}
