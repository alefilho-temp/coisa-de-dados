package models;

import java.time.LocalDate;

public class Vendedor_Pessoal {
	private int VendedorCadastro_Id = 0;
	private String nome = "";
	private LocalDate Data_Nascimento = LocalDate.now();
	private String Inscricao_Estadual = "";
	private int CPF = 0;
	
	public int getCPF() {
		return CPF;
	}
	public void setCPF(int cPF) {
		CPF = cPF;
	}
	public int getVendedorCadastro_Id() {
		return VendedorCadastro_Id;
	}
	public void setVendedorCadastro_Id(int vendedorCadastro_Id) {
		VendedorCadastro_Id = vendedorCadastro_Id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getData_Nascimento() {
		return Data_Nascimento;
	}
	public void setData_Nascimento(LocalDate data_Nascimento) {
		Data_Nascimento = data_Nascimento;
	}
	public String getInscricao_Estadual() {
		return Inscricao_Estadual;
	}
	public void setInscricao_Estadual(String inscricao_Estadual) {
		Inscricao_Estadual = inscricao_Estadual;
	}	
}
