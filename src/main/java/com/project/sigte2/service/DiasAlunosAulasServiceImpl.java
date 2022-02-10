package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.DiasAlunosAulaDao;
import com.project.sigte2.domain.DiasAlunosAulas;

@Service @Transactional(readOnly = false)
public class DiasAlunosAulasServiceImpl implements DiasAlunosAulasService{
	
	@Autowired
	private DiasAlunosAulaDao dao;
	
	@Override
	public void salvar(DiasAlunosAulas diasAlunosAulas) {
		dao.save(diasAlunosAulas);
	}

	@Override
	public void editar(DiasAlunosAulas diasAlunosAulas) {
		dao.update(diasAlunosAulas);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public DiasAlunosAulas buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<DiasAlunosAulas> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public boolean DiaSemanaTemTransportes(Long id) {
		return false;
	}

	@Override
	public List<DiasAlunosAulas> buscarAlunosPorIdDiaIdEscola(Long idD, Long idE) {
		return dao.findAlunoByIdDiaIdEscola(idD, idE);
	}

}
