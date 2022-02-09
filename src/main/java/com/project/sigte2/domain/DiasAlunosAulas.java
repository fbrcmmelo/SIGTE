package com.project.sigte2.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity
@Table(name = "DIASALUNOSAULAS")
public class DiasAlunosAulas extends AbstractEntity<Long> {
	
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_diaSemana_fk")
	private DiaSemana diaSemana;
	
	@Valid

	@NotNull(message = "Deve ser relacionado à um aluno")
	@JoinColumn(name = "id_aluno_fk")
	private Aluno idaluno;
	
	@NotNull(message = "Insira o id da Escola do Aluno")
	@JoinColumn(name = "id_escola_aluno_fk")
	private Escola idEscola;
	
	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}


}
