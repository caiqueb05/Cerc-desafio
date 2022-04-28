package br.com.cerc.holerite.persistence.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FolhaDePagamentoDTO {
	/*@NotNull(message = "O campo 'func_id' nao pode estar vazio")*/
	private long func_id;
	@NotNull(message = "O campo 'horasTrabalhadas' nao pode estar vazio")
	private long horasTrabalhadas;
	@NotBlank(message = "O campo 'mesReferencia' nao pode estar vazio")
	private String mesReferencia;
	
	public FolhaDePagamentoDTO() {
		
	}
	
	public FolhaDePagamentoDTO(long func_id, long horasTrabalhadas, String mesReferencia) {
		this.func_id = func_id;
		this.horasTrabalhadas = horasTrabalhadas;
		this.mesReferencia = mesReferencia;
	}

	public long getFunc_id() {
		return func_id;
	}
	public void setFunc_id(long func_id) {
		this.func_id = func_id;
	}
	public long getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(long horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public String getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	
	
}
