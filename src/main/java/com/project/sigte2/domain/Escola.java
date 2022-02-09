package com.project.sigte2.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "ESCOLAS")
public class Escola extends AbstractEntity<Long> {

	@NotBlank(message = "Campo nome vazio, insira um nome")
	@Size(min = 3, max = 60, message = "O nome da Escola deve conter entre {min} e {max} caracteres")
	@Column(name = "nome_escola", nullable = false, unique = true, length = 60)
	private String nomeEscola;

	@OneToMany(mappedBy = "escola")
	private List<Aluno> alunos;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;

	@OneToMany(mappedBy = "escola")
	private List<Transporte> transportes;
	
	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
