package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Transporte;
import com.project.sigte2.util.PaginacaoUtil;

public interface TransporteService {

	void salvar(Transporte transporte);

	void editar(Transporte transporte);

	void excluir(Long id);

	Transporte buscarPorId(Long id);

	List<Transporte> buscarTodos();

	PaginacaoUtil<Transporte> buscaPorPagina(int pagina);

	List<Transporte> buscarTransportePorIdDia(Long id);

}
