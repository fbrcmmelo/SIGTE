package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Transporte;
import com.project.sigte2.util.PaginacaoUtil;

public interface TransporteDao {

	void save(Transporte transporte);

	void update(Transporte transporte);

	void delete(Long id);

	Transporte findById(Long id);

	List<Transporte> findAll();

	PaginacaoUtil<Transporte> buscaParginada(int pagina);

	List<Transporte> findTransporteByIdDay(Long id);
}
