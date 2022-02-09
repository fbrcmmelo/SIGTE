package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sigte2.dao.AdministradorDao;
import com.project.sigte2.domain.Administrador;

@Service
public class AdministradorServiceImpl implements AdministradorService {

	@Autowired
	private AdministradorDao dao;

	@Override
	public void salvar(Administrador adm) {
		dao.save(adm);
	}

	@Override
	public void editar(Administrador adm) {
		dao.update(adm);
	}

	@Override
	public void excluir(Long idPessoa) {
		dao.delete(idPessoa);
	}

	@Override
	public Administrador buscarPorId(Long idPessoa) {
		return dao.findById(idPessoa);
	}

	@Override
	public List<Administrador> buscarTodos() {
		return dao.findAll();
	}

}
