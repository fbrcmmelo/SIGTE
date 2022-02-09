package com.project.sigte2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoa;

	@NotNull
	@NotBlank(message = "Insira o Nome")
	@Size(max = 255, min = 3)
	@Column(name = "nome_pessoa", nullable = false, unique = true)
	private String nomePessoa;
	
	@NotNull
	@NotBlank(message = "Insira o RG")
	@Size(max = 12, min = 12)
	@Column(name = "rg_pessoa", nullable = false, unique = true)
	private String rgPessoa;
	
	@NotNull(message = "Email null")
	@NotBlank(message = "Insira o Email")
	@Column(name = "email_pessoa", nullable = false, unique = true)
	private String emailPessoa;
	
	@NotNull
	@NotBlank(message = "Insira a Senha")
	@Size(max = 255, min = 6, message = ("Senha Fraca menor que 6 digitos"))
	@Column(name = "senha_pessoa", nullable = false)
	private String senhaPessoa;
	
	@Size(max = 2, min = 1)
	@Column(name = "tipo_pessoa", nullable = false, length = 2)
	private String tipoPessoa;

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getRgPessoa() {
		return rgPessoa;
	}

	public void setRgPessoa(String rgPessoa) {
		this.rgPessoa = rgPessoa;
	}

	public String getEmailPessoa() {
		return emailPessoa;
	}

	public void setEmailPessoa(String emailPessoa) {
		this.emailPessoa = emailPessoa;
	}

	public String getSenhaPessoa() {
		return senhaPessoa;
	}

	public void setSenhaPessoa(String senhaPessoa) {
		this.senhaPessoa = senhaPessoa;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "idPessoa = " + idPessoa;
	}
}
