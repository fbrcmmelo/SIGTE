package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.AbstractDao;
import com.project.sigte2.dao.VeiculoDao;
import com.project.sigte2.domain.Veiculo;

@Service
@Transactional(readOnly = false)
public class VeiculoServiceImpl extends AbstractDao<Veiculo, Long> implements VeiculoService {

	@Autowired
	private VeiculoDao veiculoDao;

	@Override
	public void salvar(Veiculo veiculo) {
		veiculoDao.save(veiculo);
	}

	@Override
	public void editar(Veiculo veiculo) {
		veiculoDao.update(veiculo);
	}

	@Override
	public void excluir(Long id) {
		veiculoDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Veiculo buscarPorId(Long id) {
		return veiculoDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Veiculo> buscarTodos() {
		return veiculoDao.findAll();
	}

	@Override
	public boolean veiculoTemAgendamento(Long id) {
		if (buscarPorId(id).getTransportes().isEmpty()) {
			return false;
		}
		return true;
	}

}
