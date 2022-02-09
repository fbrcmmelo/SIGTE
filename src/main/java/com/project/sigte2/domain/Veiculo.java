package com.project.sigte2.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = ("VEICULOS"))
public class Veiculo extends AbstractEntity<Long> {

	@NotBlank(message = "Insira a marca do veículo")
	@Column(name = "marca", nullable = false)
	private String marca;

	@NotBlank(message = "Insira a placa do veículo")
	@Size(min = 8, message = "Digite a placa completa")
	@Column(name = "placa", nullable = false, length = 8)
	private String placa;

	@NotBlank(message = "Insira o renavam do veículo")
	@Size(max = 9, min = 9, message = "Digite o renavam completo deve conter {min} digitos")
	@Column(name = "renavam", nullable = false, length = 9)
	private String renavam;
	
	@NotBlank(message = "Insira a status do veículo (Ativo, Inativo, Manut.)")
	@Column(name = "status", nullable = false, length = 60)
	private String status;

	@NotBlank(message = "Insira qual tipo de veículo se enquadra")
	@Size(max = 10, min = 3)
	@Column(name = "tipo", nullable = false, length = 10)
	private String tipo;
	
	@NotNull(message = "Insira a quantidade de permitida de passageiros")
	@Column(name = "capacidade", nullable = false)
	private int capacidade;

	@OneToMany(mappedBy = "veiculo")
	private List<Transporte> transportes;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public List<Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(List<Transporte> transportes) {
		this.transportes = transportes;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
