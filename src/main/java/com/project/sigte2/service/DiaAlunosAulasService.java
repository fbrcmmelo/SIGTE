package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.DiasAlunosAulas;

public interface DiaAlunosAulasService {

	void salvar(DiasAlunosAulas diaAula);

	void editar(DiasAlunosAulas diaAula);

	void excluir(Long id);

	DiasAlunosAulas buscarPorId(Long id);

	List<DiasAlunosAulas> buscarTodos();

	boolean DiaSemanaTemTransportes(Long id);

	List<DiasAlunosAulas> buscarAlunosPorIdDiaIdEscola(Long idDia, Long idEscola);
}