package br.com.cerc.holerite.persistence.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FuncionarioDTO {
	@NotBlank(message = "O campo 'nome' nao pode estar vazio")
	private String nome;
	@NotBlank(message = "O campo 'cpf' nao pode estar vazio")
	private String cpf;
	@NotNull(message = "O campo 'cargo_id' nao pode estar vazio")
	private long IdCargo;
	
	public FuncionarioDTO() {
		
	}

	public FuncionarioDTO(String nome, String cpf, long idCargo) {
		this.nome = nome;
		this.cpf = cpf;
		IdCargo = idCargo;
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
}
