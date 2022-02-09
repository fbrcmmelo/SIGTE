package com.project.sigte2.dao;

import java.util.List;

import com.project.sigte2.domain.Cargo;
import com.project.sigte2.util.PaginacaoUtil;

public interface CargoDao {

	void save(Cargo cargo);

	void update(Cargo cargo);

	void delete(Long id);

	Cargo findById(Long id);

	List<Cargo> findAll();

	PaginacaoUtil<Cargo> buscaParginada(int pagina);
}
