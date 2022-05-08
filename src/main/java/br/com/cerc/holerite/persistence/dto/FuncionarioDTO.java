package br.com.cerc.holerite.persistence.dto;

import br.com.cerc.holerite.persistence.model.Cargo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FuncionarioDTO {
	@NotBlank(message = "O campo 'nome' nao pode estar vazio")
	private String nome;
	@NotBlank(message = "O campo 'cpf' nao pode estar vazio")
	private String cpf;
	@NotNull(message = "O campo 'cargo_id' nao pode estar vazio")
	private long IdCargo;

	@NotNull
	private String email;

	private String linkFoto;

	private Cargo cargo;



	public FuncionarioDTO() {

	}

	public FuncionarioDTO(String nome, String cpf, String email, long idCargo, Cargo cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.IdCargo = idCargo;
		this.cargo = cargo;
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

	public long getIdCargo() {
		return IdCargo;
	}

	public void setIdCargo(long idCargo) {
		IdCargo = idCargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


}
