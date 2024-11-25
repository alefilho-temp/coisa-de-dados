package models;

public class TelefoneModel {

	private int Telefone;
	private int Cadastroid;
	
	public int getTelefone() {
		return Telefone;
	}
	public void setTelefone(int telefone) {
		Telefone = telefone;
	}
	public int getCadastroid() {
		return Cadastroid;
	}
	public void setCadastroid(int cadastroid) {
		Cadastroid = cadastroid;
	}
	
	public TelefoneModel(){
		super();
	}
}