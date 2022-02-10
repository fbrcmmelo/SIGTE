package com.project.sigte2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sigte2.dao.AgendaTransporteDao;
import com.project.sigte2.domain.AgendaTransporte;
import com.project.sigte2.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class AgendaTransporteServiceImpl implements AgendaTransporteService {

	@Autowired
	private AgendaTransporteDao agendaTransporteDao;

	@Override
	public void salvar(AgendaTransporte agendaTransporte) {
		agendaTransporteDao.save(agendaTransporte);
	}

	@Override
	public void editar(AgendaTransporte agendaTransporte) {
		agendaTransporteDao.update(agendaTransporte);
	}

	@Override
	public void excluir(Long id) {
		agendaTransporteDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public AgendaTransporte buscarPorId(Long id) {
		return agendaTransporteDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AgendaTransporte> buscarTodos() {
		return agendaTransporteDao.findAll();
	}

	@Override
	public PaginacaoUtil<AgendaTransporte> buscaPorPagina(int pagina) {
		return agendaTransporteDao.buscaPaginada(pagina);
	}

	@Override
	public List<AgendaTransporte> buscarAlunosPorIdDiaIdEscola(Long idDia, Long idEscola) {
		return agendaTransporteDao.findAlunosByIdDiaIdEscola(idDia, idEscola);
	}

	@Override
	public List<AgendaTransporte> buscarPorIdAluno(Long id) {
		return agendaTransporteDao.findAlunosByIdDiaIdEscola(id);
	}

}
