package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.TransporteDao;
import com.project.sigte2.domain.Transporte;
import com.project.sigte2.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class TransporteServiceImpl implements TransporteService {

	@Autowired
	private TransporteDao dao;

	@Override
	public void salvar(Transporte transporte) {
		dao.save(transporte);
	}

	@Override
	public void editar(Transporte transporte) {
		dao.update(transporte);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Transporte buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transporte> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public PaginacaoUtil<Transporte> buscaPorPagina(int pagina) {
		return dao.buscaParginada(pagina);
	}

	@Override
	public List<Transporte> buscarTransportePorIdDia(Long id) {
		return dao.findTransporteByIdDay(id);
	}
}
