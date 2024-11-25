package models;

public class Cadastro {
	private int id = 0;
	private String email = "";
	private String Logradouro = "";
	private int Numero = 0;
	private String Bairro = "";
	private int CEP = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int valor) {
		this.id = valor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String valor) {
		this.email = valor;
	}
	public String getLogradouro() {
		return Logradouro;
	}
	public void setLogradouro(String valor) {
		this.Logradouro = valor;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int valor) {
		this.Numero = valor;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String valor) {
		this.Bairro = valor;
	}
	public int getCep() {
		return CEP;
	}
	public void setCep(int valor) {
		this.CEP = valor;
	}
}	
