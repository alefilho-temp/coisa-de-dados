package models;

import java.time.LocalDate;

public class Cartao {
	
	private String Tipo_Cartao;
	private int Numero;
	private String Nome_Cartao;
	private LocalDate Data_Vencimento;
	private int Codigo_Seguranca;
	private int ClienteCPF;
	private int ClienteCadastroid;
	
	public String getTipo_Cartao() {
		return Tipo_Cartao;
	}
	public void setTipo_Cartao(String tipo_Cartao) {
		Tipo_Cartao = tipo_Cartao;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public String getNome_Cartao() {
		return Nome_Cartao;
	}
	public void setNome_Cartao(String nome_Cartao) {
		Nome_Cartao = nome_Cartao;
	}
	public LocalDate getData_Vencimento() {
		return Data_Vencimento;
	}
	public void setData_Vencimento(LocalDate data_Vencimento) {
		Data_Vencimento = data_Vencimento;
	}
	public int getCodigo_Seguranca() {
		return Codigo_Seguranca;
	}
	public void setCodigo_Seguranca(int codigo_Seguranca) {
		Codigo_Seguranca = codigo_Seguranca;
	}
	public int getClienteCPF() {
		return ClienteCPF;
	}
	public void setClienteCPF(int clienteCPF) {
		ClienteCPF = clienteCPF;
	}
	public int getClienteCadastroid() {
		return ClienteCadastroid;
	}
	public void setClienteCadastroid(int clienteCadastroid) {
		ClienteCadastroid = clienteCadastroid;
	}

}