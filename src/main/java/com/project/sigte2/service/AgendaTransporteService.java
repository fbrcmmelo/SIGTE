package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.AgendaTransporte;
import com.project.sigte2.domain.Aluno;
import com.project.sigte2.util.PaginacaoUtil;

public interface AgendaTransporteService {

	void salvar(AgendaTransporte agendaTransporte);

	void editar(AgendaTransporte agendaTransporte);

	void excluir(Long id);

	AgendaTransporte buscarPorId(Long id);

	List<AgendaTransporte> buscarTodos();

	PaginacaoUtil<AgendaTransporte> buscaPorPagina(int pagina);

	List<AgendaTransporte> buscarAlunosPorIdDiaIdEscola(Long idDia, Long idEscola);

	List<AgendaTransporte> buscarPorIdAluno(Long id);
}
