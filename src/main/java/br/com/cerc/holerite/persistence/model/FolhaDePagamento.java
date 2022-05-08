package br.com.cerc.holerite.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "folha_de_pagamento")
public class FolhaDePagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFolhaDePagamento;

	@NotNull
	private double INSS;

	@NotNull
	private double IRRF;

	@NotNull
	private double FGTS;

	/*@NotBlank*/
	private String dataEmissao;

	@NotBlank
	private String mesReferencia;

	@NotNull
	private double salarioBruto;

	@NotNull
	private double salarioLiquido;

	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	public FolhaDePagamento() {
		
	}
	
	public FolhaDePagamento(Funcionario funcionario, double iNSS, double iRRF, double fGTS, String dataEmissao,
			String mesReferencia, double salarioBruto, double salarioLiquido) {
		this.funcionario = funcionario;
		this.INSS = iNSS;
		this.IRRF = iRRF;
		this.FGTS = fGTS;
		this.dataEmissao = dataEmissao;
		this.mesReferencia = mesReferencia;
		this.salarioBruto = salarioBruto;
		this.salarioLiquido = salarioLiquido;
	}

	public long getIdFolhaDePagamento() {
		return idFolhaDePagamento;
	}

	public void setIdFolhaDePagamento(long idFolhaDePagamento) {
		this.idFolhaDePagamento = idFolhaDePagamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public double getINSS() {
		return INSS;
	}

	public void setINSS(double iNSS) {
		INSS = iNSS;
	}

	public double getIRRF() {
		return IRRF;
	}

	public void setIRRF(double iRRF) {
		IRRF = iRRF;
	}

	public double getFGTS() {
		return FGTS;
	}

	public void setFGTS(double fGTS) {
		FGTS = fGTS;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	
}
