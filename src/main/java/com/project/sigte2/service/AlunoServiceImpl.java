package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.AlunoDao;
import com.project.sigte2.domain.Aluno;

@Service
@Transactional(readOnly = false)
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoDao aDao;

	@Override
	public void salvar(Aluno aluno) {
		aDao.save(aluno);
	}

	@Override
	public void editar(Aluno aluno) {
		aDao.update(aluno);
	}

	@Override
	public void excluir(Long id) {
		aDao.delete(id);
	}

	@Override
	public Aluno buscarPorId(Long id) {
		return aDao.findById(id);
	}

	@Override
	public List<Aluno> buscarTodos() {
		return aDao.findAll();
	}

	@Override
	public List<Aluno> buscarPorNome(String nome) {
		return aDao.findByNome(nome);
	}

	@Override
	public List<Aluno> buscarPorEscola(Long id) {
		return aDao.findByEscola(id);
	}

	@Override
	public Aluno buscarPorEmailSenha(String emaiPessoa, String senhaPessoa) {
		return aDao.findByEmailPassword(emaiPessoa, senhaPessoa);
	}

	@Override
	public boolean alunoTemAgendamentos(Long id) {
		if(buscarPorId(id).getAgendaTransporte().isEmpty()) {
			return false;
		}
		return true;
	}

}
