package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.FuncionarioDao;
import com.project.sigte2.domain.Funcionario;

@Service
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao dao;

	@Transactional(readOnly = false)
	@Override
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Transactional(readOnly = false)
	@Override
	public void editar(Funcionario funcionario) {
		dao.update(funcionario);
	}

	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	public Funcionario buscarPorId(Long id) {

		return dao.findById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() {

		return dao.findAll();
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		return dao.findByNome(nome);
	}

	@Override
	public List<Funcionario> buscarPorCargo(Long id) {
		return dao.findByCargoId(id);
	}

	@Override
	public Funcionario buscarPorEmailSenha(String emailPessoa, String senhaPessoa) {
		return dao.findByEmailPassword(emailPessoa, senhaPessoa);
	}

}
