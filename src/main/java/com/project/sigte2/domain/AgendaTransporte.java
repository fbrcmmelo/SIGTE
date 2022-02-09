package com.project.sigte2.domain;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "AGENDATRANSPORTES")
public class AgendaTransporte extends AbstractEntity<Long> {

	@NotNull(message = "Selecione um dia da Semana")
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_diaSemana_fk")
	private DiaSemana diaSemana;
	
	@NotNull(message = "Selecione ")
	@Valid
	@ManyToOne
	@JoinColumn(name = "id_pessoa_fk")
	private Aluno aluno;

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}


	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public DiaSemana getdiaSemana() {
		return diaSemana;
	}


	public void setdiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

}
