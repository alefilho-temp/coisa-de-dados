package controllers;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cartao;
import service.CartaoDAO;
import service.CartaoDAOImpl;

/**
 * Classe responsavel pelo controle das informacoes de um Cartao.
 */
public class CartaoControl {

	private StringProperty Tipo_Cartao = new SimpleStringProperty("");
	private IntegerProperty Numero = new SimpleIntegerProperty();
	private StringProperty Nome_Cartao = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> Data_Vencimento = new SimpleObjectProperty<>(LocalDate.now());
	private IntegerProperty Codigo_Seguranca = new SimpleIntegerProperty();
	private IntegerProperty ClienteCPF = new SimpleIntegerProperty();
	private IntegerProperty ClienteCadastroid = new SimpleIntegerProperty();

	private CartaoDAO cartaoDAO;
	private ObservableList<Cartao> lista = FXCollections.observableArrayList();

	/**
	 * Construtor da classe.
	 * 
	 * Inicializa a classe que realiza as interacoes com o banco de dados.
	 * 
	 * @throws Exception Caso ocorra algum erro na inicializacao do controlador do
	 *                   banco.
	 */
	public CartaoControl() throws Exception {
		cartaoDAO = new CartaoDAOImpl();
	}

	/**
	 * Transfere os dados de um Cartao para os atributos da tela.
	 * 
	 * @param c O cartao que tera suas informacoes exibidas na tela.
	 */
	public void entidadeParaTela(Cartao c) {
		if (c != null) {
			this.Tipo_Cartao.set(c.getTipo_Cartao());
			this.Numero.set(c.getNumero());
			this.Nome_Cartao.set(c.getNome_Cartao());
			this.Data_Vencimento.set(c.getData_Vencimento());
			this.Codigo_Seguranca.set(c.getCodigo_Seguranca());
			this.ClienteCPF.set(c.getClienteCPF());
			this.ClienteCadastroid.set(c.getClienteCadastroid());
		}
	}

	/**
	 * Exclui um cartao do banco de dados.
	 * 
	 * @param c O cartao que sera removido.
	 * @throws Exception Caso ocorra um erro na exclusao do cartao.
	 */
	public void excluir(Cartao c) throws Exception {
		System.err.println("Excluido cartao de apelido: " + c.getNome_Cartao());
		cartaoDAO.remover(c);
		pesquisarTodos();
	}

	/**
	 * Grava um novo cartao no banco de dados ou atualiza um existente.
	 * 
	 * @throws Exception Caso ocorra um erro na gravacao ou atualizacao do cartao.
	 */
	public void gravar() throws Exception {
		Cartao c = new Cartao();

		c.setTipo_Cartao(this.Tipo_Cartao.get());
		c.setNumero(this.Numero.get());
		c.setNome_Cartao(this.Nome_Cartao.get());
		c.setData_Vencimento(this.Data_Vencimento.get());
		c.setCodigo_Seguranca(this.Codigo_Seguranca.get());
		c.setClienteCPF(this.ClienteCPF.get());
		c.setClienteCadastroid(this.ClienteCadastroid.get());
		System.out.println("INSERTANDO -> " + "Numero: " + c.getNumero() + ", Tipo: " + c.getTipo_Cartao() + ", Nome: "
				+ c.getNome_Cartao() + ", Data: " + c.getData_Vencimento() + ", Codigo: " + c.getCodigo_Seguranca()
				+ ", CPF: " + c.getClienteCPF() + ", CadastroId: " + c.getClienteCadastroid());
		cartaoDAO.inserir(c);
		pesquisarTodos();
		limparTudo();
		System.out.println("Lista de cartaos: " + lista.size());
	}

	/**
	 * Limpa os campos de entrada da tela de Cartao.
	 */
	public void limparTudo() {
		this.Tipo_Cartao.set("");
		this.Numero.set(0);
		this.Nome_Cartao.set("");
		this.Data_Vencimento.set(LocalDate.now());
		this.Codigo_Seguranca.set(0);
		this.ClienteCPF.set(0);
		this.ClienteCadastroid.set(0);
	}

	/**
	 * Realiza uma pesquisa por cartoes utilizando o nome do cartao.
	 * 
	 * @throws Exception Caso ocorra um erro na consulta ao banco de dados.
	 */
	public void pesquisarPorNome() throws Exception {
		lista.clear();
		lista.addAll(cartaoDAO.pesquisarPorNome(Nome_Cartao.get()));
	}

	/**
	 * Pesquisa todos os cartoes registrados no banco de dados.
	 * 
	 * @throws Exception Caso ocorra um erro na consulta ao banco de dados.
	 */
	public void pesquisarTodos() throws Exception {
		lista.clear();
		lista.addAll(cartaoDAO.pesquisarTodos());
	}

	/**
	 * Obtem o tipo do cartao.
	 * 
	 * @return Uma propriedade representando o tipo do cartao.
	 */
	public StringProperty getTipo_Cartao() {
		return Tipo_Cartao;
	}

	/**
	 * Obtem o numero do cartao.
	 * 
	 * @return Uma propriedade representando o numero do cartao.
	 */
	public IntegerProperty getNumero() {
		return Numero;
	}

	/**
	 * Obtem o nome do cartao.
	 * 
	 * @return Uma propriedade representando o nome do cartao.
	 */
	public StringProperty getNome_Cartao() {
		return Nome_Cartao;
	}

	/**
	 * Obtem a data de vencimento do cartao.
	 * 
	 * @return Uma propriedade representando a data de vencimento do cartao.
	 */
	public ObjectProperty<LocalDate> getData_Vencimento() {
		return Data_Vencimento;
	}

	/**
	 * Obtem o codigo de seguranca do cartao.
	 * 
	 * @return Uma propriedade representando o codigo de seguranca do cartao.
	 */
	public IntegerProperty getCodigo_Seguranca() {
		return Codigo_Seguranca;
	}

	/**
	 * Obtem o CPF do cliente associado ao cartao.
	 * 
	 * @return Uma propriedade representando o CPF do cliente.
	 */
	public IntegerProperty getClienteCPF() {
		return ClienteCPF;
	}

	/**
	 * Obtem o ID do cadastro do cliente associado ao cartao.
	 * 
	 * @return Uma propriedade representando o ID do cadastro do cliente.
	 */
	public IntegerProperty getClienteCadastroid() {
		return ClienteCadastroid;
	}

	/**
	 * Obtem a lista de cartoes.
	 * 
	 * @return Uma lista observavel contendo os cartoes registrados.
	 */
	public ObservableList<Cartao> getLista() {
		return lista;
	}

}