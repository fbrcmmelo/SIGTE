package com.project.sigte2.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "TRANSPORTES")
public class Transporte extends AbstractEntity<Long> {
	
	@NotNull(message = "Selecione um veiculo para o transporte")
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_veiculo_fk")
	private Veiculo veiculo;
	
	@NotNull(message = "Selecione uma escola para destino")
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_escola_fk")
	private Escola escola;
	
	@NotNull(message = "Selecione o Motorista")
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_funcionario_fk")
	private Funcionario funcionario;
	
	@NotNull(message = "Selecione um dia da Semana")
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_diaSemana_fk")
	private DiaSemana diaSemana;

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
