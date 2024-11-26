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

/**
 * Classe responsavel pelo controle das operacoes relacionadas ao cliente.
 */
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



	/**
	 * Construtor que inicializa o DAO do cliente.
	 */
	public controlCliente() {
		ClienteDao = new ClienteDAOImpl();
	}

	/**
	 * Transfere os dados de uma entidade Cliente para os campos da tela.
	 *
	 * @param c O cliente cujos dados serao exibidos.
	 */
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

	/**
	 * Transfere os dados dos campos da tela para uma entidade Cliente.
	 *
	 * @return Um objeto Cliente com os dados preenchidos.
	 */
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

	/**
	 * Grava os dados do cliente.
	 *
	 * @throws ClienteException Caso ocorra um erro na gravacao.
	 */
	public void gravar() throws ClienteException {
		Cliente c = telaParaEntidade();
		ClienteDao.gravar(c);
		pesquisarTodos();
		limparTudo();
	}

	/**
	 * Atualiza os dados de um cliente existente.
	 *
	 * @throws ClienteException Caso ocorra um erro na atualizacao.
	 */
	public void atualizar() throws ClienteException {
		Cliente c = telaParaEntidade();
		ClienteDao.atualizar(c);
		pesquisarTodos();
		limparTudo();
	}

	/**
	 * Pesquisa clientes com base no usuario informado.
	 *
	 * @throws ClienteException Caso ocorra um erro na pesquisa.
	 */
	public void pesquisar() throws ClienteException {
		lista.clear();
		lista.addAll(ClienteDao.pesquisar(usuario.get()));
	}

	/**
	 * Pesquisa todos os clientes.
	 *
	 * @throws ClienteException Caso ocorra um erro na pesquisa.
	 */
	public void pesquisarTodos() throws ClienteException {
		lista.clear();
		lista.addAll(ClienteDao.pesquisarTodos());
	}

	/**
	 * Remove um cliente.
	 *
	 * @param c O cliente que sera removido.
	 * @throws ClienteException Caso ocorra um erro na remocao.
	 */
	public void remover(Cliente c) throws ClienteException {
		ClienteDao.remover(c);
		pesquisarTodos();
	}

	/**
	 * Limpa todos os campos da tela.
	 */
	public void limparTudo() {
		cpf.set(0);
		nome.set("");
		usuario.set("");
		genero.set("");
		nascimento.set(LocalDate.now());
		cadastroId.set(0);
		carrinhoid.set(0);
	}

	/**
	 * Retorna a propriedade CPF.
	 *
	 * @return A propriedade CPF.
	 */
	public IntegerProperty cpfProperty() {
		return this.cpf;
	}

	/**
	 * Retorna a propriedade usuario.
	 *
	 * @return A propriedade usuario.
	 */
	public StringProperty usuarioProperty() {
		return this.usuario;
	}

	/**
	 * Retorna a propriedade nome.
	 *
	 * @return A propriedade nome.
	 */
	public StringProperty nomeProperty() {
		return this.nome;
	}

	/**
	 * Retorna a propriedade genero.
	 *
	 * @return A propriedade genero.
	 */
	public StringProperty generoProperty() {
		return this.genero;
	}

	/**
	 * Retorna a propriedade nascimento.
	 *
	 * @return A propriedade nascimento.
	 */
	public ObjectProperty<LocalDate> nascimentoProperty() {
		return this.nascimento;
	}

	/**
	 * Retorna a propriedade carrinhoID.
	 *
	 * @return A propriedade carrinhoID.
	 */
	public IntegerProperty carrinhoidProperty() {
		return this.carrinhoid;
	}

	/**
	 * Retorna a propriedade cadastroID.
	 *
	 * @return A propriedade cadastroID.
	 */
	public IntegerProperty cadastroProperty() {
		return this.cadastroId;
	}

	/**
	 * Retorna a lista observavel de clientes.
	 *
	 * @return A lista de clientes.
	 */
	public ObservableList<Cliente> getLista() {
		return this.lista;
	}
}