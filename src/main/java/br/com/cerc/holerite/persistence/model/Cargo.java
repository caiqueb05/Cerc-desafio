package br.com.cerc.holerite.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCargo;

	@NotBlank
	private String nome;

	@NotNull
	private double pagamentoHora;

	@OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	/*public Cargo() {
		
	}
	
	public Cargo(String nome, double pagamentoHora) {
		this.nome = nome;
		this.pagamentoHora = pagamentoHora;
	}*/

	public List<Funcionario> getFuncionario() {
		return funcionarios;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionarios = funcionario;
	}

	public long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(long idCargo) {
		this.idCargo = idCargo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPagamentoHora() {
		return pagamentoHora;
	}
	public void setPagamentoHora(double pagamentoHora) {
		this.pagamentoHora = pagamentoHora;
	}
	
	
}
