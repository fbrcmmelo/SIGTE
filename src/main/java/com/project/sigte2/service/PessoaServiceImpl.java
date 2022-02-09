package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.PessoaDao;
import com.project.sigte2.domain.Pessoa;

@Service
@Transactional(readOnly = false)
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaDao dao;

	@Override
	public void salvar(Pessoa pessoa) {
		dao.save(pessoa);
	}

	@Override
	public void editar(Pessoa pessoa) {
		dao.update(pessoa);
	}

	@Override
	public void excluir(Long idPessoa) {
		dao.delete(idPessoa);
	}

	@Override @Transactional(readOnly = true)
	public Pessoa buscarPorId(Long idPessoa) {
		return dao.findById(idPessoa);
	}

	@Override @Transactional(readOnly = true)
	public List<Pessoa> listarTodos() {
		return dao.findAll();
	}

	@Override @Transactional(readOnly = true)
	public List<Pessoa> buscarPorEmailSenha(String emailPessoa, String senhaPessoa) {
		return dao.getByEmailPassword(emailPessoa, senhaPessoa);
	}

}
