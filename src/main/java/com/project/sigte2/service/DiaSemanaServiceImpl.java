package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.DiaSemanaDao;
import com.project.sigte2.domain.DiaSemana;

@Service
@Transactional(readOnly = false)
public class DiaSemanaServiceImpl implements DiaSemanaService {

	@Autowired
	private DiaSemanaDao diaSemanaDao ;

	@Override
	public void salvar(DiaSemana diaAula) {
		diaSemanaDao.save(diaAula);
	}

	@Override
	public void editar(DiaSemana diaAula) {
		diaSemanaDao.update(diaAula);
	}

	@Override
	public void excluir(Long id) {
		diaSemanaDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public DiaSemana buscarPorId(Long id) {
		return diaSemanaDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DiaSemana> buscarTodos() {
		return diaSemanaDao.findAll();
	}

	@Override
	public boolean DiaSemanaTemTransportes(Long id) {
		if (buscarPorId(id).getTransportes().isEmpty()) {
			return false;
		}
		return true;
	}

}
