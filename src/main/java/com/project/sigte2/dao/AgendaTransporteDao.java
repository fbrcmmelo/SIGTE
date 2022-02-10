package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.AgendaTransporte;
import com.project.sigte2.util.PaginacaoUtil;

public interface AgendaTransporteDao {

	void save(AgendaTransporte agendaTransporte);

	void update(AgendaTransporte agendaTransporte);

	void delete(Long id);

	AgendaTransporte findById(Long id);

	List<AgendaTransporte> findAll();

	PaginacaoUtil<AgendaTransporte> buscaPaginada(int pagina);

	List<AgendaTransporte> findAlunosByIdDiaIdEscola(Long idDia, Long idEscola);

	List<AgendaTransporte> findAlunosByIdDiaIdEscola(Long id);

}
