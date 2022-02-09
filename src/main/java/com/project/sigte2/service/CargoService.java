package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Cargo;
import com.project.sigte2.util.PaginacaoUtil;

public interface CargoService {

	void salvar(Cargo cargo);

	void editar(Cargo cargo);

	void excluir(Long id);

	Cargo buscarPorId(Long id);

	List<Cargo> buscarTodos();

	boolean cargoTemFuncionarios(Long id);

	PaginacaoUtil<Cargo> buscaPorPagina(int pagina);
}
