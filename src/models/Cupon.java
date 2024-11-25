package models;

public class Cupon {
	
	private int numero_cupon;
	private int porcentagem_Desconto;
	private int ClienteCPF;
	private int ClienteCadastroid;
	
	public int getNumero_cupon() {
		return numero_cupon;
	}
	public void setNumero_cupon(int numero_cupon) {
		this.numero_cupon = numero_cupon;
	}
	public int getPorcentagem_Desconto() {
		return porcentagem_Desconto;
	}
	public void setPorcentagem_Desconto(int porcentagem_Desconto) {
		this.porcentagem_Desconto = porcentagem_Desconto;
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