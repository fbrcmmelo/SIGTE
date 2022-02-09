package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.EscolaDao;
import com.project.sigte2.domain.Escola;

@Service
@Transactional(readOnly = false)
public class EscolaServiceImpl implements EscolaService {

	@Autowired
	private EscolaDao dao;

	@Override
	public void salvar(Escola escola) {
		dao.save(escola);
	}

	@Override
	public void editar(Escola escola) {
		dao.update(escola);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Escola buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Escola> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public boolean escolaTemAlunos(Long id) {
		if (buscarPorId(id).getAlunos().isEmpty()) {
			return false;
		}
		return true;
	}

}
