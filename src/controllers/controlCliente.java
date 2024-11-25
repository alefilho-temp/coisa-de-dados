package controllers;
import java.time.LocalDate;

import service.ClienteDao;
import service.ClienteDAOImpl;
import service.ClienteException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cliente;

public class controlCliente {
	private IntegerProperty cpf = new SimpleIntegerProperty(0);
	private IntegerProperty carrinhoid = new SimpleIntegerProperty(0);
	private IntegerProperty cadastroId = new SimpleIntegerProperty(0);
    private ObservableList<Cliente> lista = FXCollections.observableArrayList();

	private StringProperty usuario = new SimpleStringProperty("");
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty genero = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());
	private ClienteDao ClienteDao;
	
    private int contador = 0;

    
	 public controlCliente() {
		 ClienteDao = new ClienteDAOImpl();
	}
	public void entidadeParaTela(Cliente c) {
		if (c != null) {
			this.cpf.set(c.getCpf());
			this.usuario.set(c.getUsuario());
			this.nome.set(c.getNome());
			this.genero.set(c.getGenero());
			this.nascimento.set(c.getNascimento());
			this.carrinhoid.set(c.getCarrinhoid());
			this.cadastroId.set(c.getCadastroId());
		}
	}

	public Cliente telaParaEntidade() {
		Cliente c = new Cliente();
		c.setCpf(this.cpf.get());
		c.setUsuario(this.usuario.get());
		c.setNome(this.nome.get());
		c.setGenero(this.genero.get());
		c.setNascimento(this.nascimento.get());
		c.setCarrinhoid(this.carrinhoid.get());
		c.setCadastroId(this.cadastroId.get());
		return c;
	}

	public void gravar() throws ClienteException {
		Cliente c = telaParaEntidade();
		ClienteDao.gravar(c);
		pesquisarTodos();
		limparTudo();
	}
	
	public void atualizar() throws ClienteException {
		Cliente c = telaParaEntidade();
		ClienteDao.atualizar(c);
		pesquisarTodos();
		limparTudo();
	}

	public void pesquisar() throws ClienteException {
		lista.clear();
		lista.addAll(ClienteDao.pesquisar(usuario.get()));
	}

    public void pesquisarTodos()  throws ClienteException { 
        lista.clear();
        lista.addAll(ClienteDao.pesquisarTodos());
    }
	public void remover(Cliente c) throws ClienteException {
		ClienteDao.remover(c);
		pesquisarTodos();
	}

	public void limparTudo() {
		cpf.set(0);
		nome.set("");
		usuario.set("");
		genero.set("");
		nascimento.set(LocalDate.now());
		cadastroId.set(0);
		carrinhoid.set(0);
	}

	public IntegerProperty cpfProperty() {
		return this.cpf;
	}
	public StringProperty usuarioProperty() {
		return this.usuario;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public StringProperty generoProperty() {
		return this.genero;
	}

	public ObjectProperty<LocalDate> nascimentoProperty() {
		return this.nascimento;
	}
	public IntegerProperty carrinhoidProperty() {
		return this.carrinhoid;
	}
	public IntegerProperty cadastroProperty() {
		return this.cadastroId;
	}
	public ObservableList<Cliente> getLista() {
		return this.lista;
	}

}
