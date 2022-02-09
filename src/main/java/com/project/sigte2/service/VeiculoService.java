package com.project.sigte2.service;

import java.util.List;

import com.project.sigte2.domain.Veiculo;

public interface VeiculoService {

	void salvar(Veiculo veiculo);

	void editar(Veiculo veiculo);

	void excluir(Long id);

	Veiculo buscarPorId(Long id);

	List<Veiculo> buscarTodos();

	boolean veiculoTemAgendamento(Long id);

}
