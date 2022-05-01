package br.com.cerc.holerite.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFuncionario;

	/*@Column(nullable = false)*/
	@NotBlank
	private String nome;

	/*@Column(nullable = false)*/
	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;

	@ManyToOne
	@JoinColumn(name = "id_cargo")
	@JsonIgnoreProperties("funcionario")
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private List<FolhaDePagamento> folhaDePagamento = new ArrayList<>();
	
	public Funcionario() {
		
	}

	public Funcionario(String nome, String cpf, Cargo cargo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
	}

	public List<FolhaDePagamento> getFolhaDePagamento() {
		return folhaDePagamento;
	}

	public void setFolhaDePagamento(List<FolhaDePagamento> folhaDePagamento) {
		this.folhaDePagamento = folhaDePagamento;
	}

	public long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return idFuncionario == other.idFuncionario;
	}
	
}
