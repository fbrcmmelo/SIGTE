package com.project.sigte2.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@SuppressWarnings("serial")
@Table(name = "ALUNOS")
public class Aluno extends Pessoa {

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", columnDefinition = "DATE", nullable = false)
	private LocalDate dataNascimento;

	@Column(name = "grau_escolar")
	private String grauEscolar;
	
	@Valid
	@NotNull(message = "Selecione uma Escola")
	@ManyToOne
	@JoinColumn(name = "id_escola_fk")
	private Escola escola;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "aluno")
	private List<AgendaTransporte> agendaTransporte;

	public List<AgendaTransporte> getAgendaTransporte() {
		return agendaTransporte;
	}

	public void setAgendaTransporte(List<AgendaTransporte> agendaTransporte) {
		this.agendaTransporte = agendaTransporte;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGrauEscolar() {
		return grauEscolar;
	}

	public void setGrauEscolar(String grauEscolar) {
		this.grauEscolar = grauEscolar;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
