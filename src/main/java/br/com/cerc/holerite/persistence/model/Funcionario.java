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

	@NotBlank
	private String email;


	private String linkFoto;

	@ManyToOne
	@JoinColumn(name = "id_cargo")
	@JsonIgnoreProperties("funcionario")
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private FolhaDePagamento folhaDePagamento;
	/*
	public Funcionario() {

	}

	public Funcionario(String nome, String cpf, Cargo cargo, String email) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.email = email;
	}*/

	/*public Funcionario(long idFuncionario, String nome, String cpf, Cargo cargo, Usuario usuario, List<FolhaDePagamento> folhaDePagamento) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.usuario = usuario;
		this.folhaDePagamento = folhaDePagamento;
	}*/

	public FolhaDePagamento getFolhaDePagamento() {
		return folhaDePagamento;
	}

	public void setFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
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

	@Override
	public String toString() {
		return "Funcionario{" +
				"idFuncionario=" + idFuncionario +
				", nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", email='" + email + '\'' +
				", cargo=" + cargo +
				", usuario=" + usuario +
				", folhaDePagamento=" + folhaDePagamento +
				'}';
	}
}
