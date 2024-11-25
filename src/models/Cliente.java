package models;
import java.time.LocalDate;

public class Cliente {
	private int cpf = 0;
	private String nome = "";
	private String usuario = "";
	private String genero = "";
	private LocalDate nascimento = LocalDate.now();
	private int carrinhoid = 0;
	private int cadastroId = 0;
	
	public int getCadastroId() {
		return cadastroId;
	}

	public void setCadastroId(int cadastroId) {
		this.cadastroId = cadastroId;
	}

	public int getCarrinhoid() {
		return carrinhoid;
	}

	public void setCarrinhoid(int carrinhoid) {
		this.carrinhoid = carrinhoid;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
}
