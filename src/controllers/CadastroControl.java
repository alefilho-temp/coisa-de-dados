package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cadastro;
import service.CadastroDAO;
import service.CadastroDAOImpl;

/**
 * Classe responsavel pelo controle das infomacoes de cadastro de Cliente
 */
public class CadastroControl {
	private ObservableList<Cadastro> lista = FXCollections.observableArrayList();
	private IntegerProperty Id = new SimpleIntegerProperty(0);
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty logradouro = new SimpleStringProperty("");
	private IntegerProperty numero = new SimpleIntegerProperty(0);
	private StringProperty bairro = new SimpleStringProperty("");
	private IntegerProperty CEP = new SimpleIntegerProperty(0);

	private CadastroDAO cadastroDAO;
	private int contador = 0;

	/**
	 * Contrutor da classe.
	 * 
	 * Inicializa a classe que realiza as interacoes com banco de dados.
	 */
	public CadastroControl() {
		cadastroDAO = new CadastroDAOImpl();
	}

	/**
	 * Insere na tela as infomacoes de um Cadastro As infomacoes sao colocadas no
	 * TextFilds.
	 * 
	 * @param c O cadastro que tera as infomacoes puxadas.
	 */
	public void entidadeParaTela(Cadastro c) {
		if (c != null) {
			this.Id.set(c.getId());
			this.email.set(c.getEmail());
			this.logradouro.set(c.getLogradouro());
			this.numero.set(c.getNumero());
			this.bairro.set(c.getBairro());
			this.CEP.set(c.getCep());
		}
	}

	/**
	 * Metodo responsavel pela exclusao dos dados de Cadastro.
	 * 
	 * @param c O cadastro que sera removido do banco de dados.
	 * @throws Exception Ocorre caso haja uma falha na conexao ou inicializacao do
	 *                   banco de dados.
	 */
	public void excluir(Cadastro c) throws Exception {
		System.out.println("Excluido cadastro de id: " + c.getId());
		cadastroDAO.remover(c);
		pesquisarTodos();
	}

	/**
	 * Metodo responsavel por gravar e armazenar as infomacoes de cadastro no banco
	 * de dados.
	 * 
	 * @throws Exception Ocorre caso haja uma falha na conexao e/ou insercao no
	 *                   banco de dados.
	 */
	public void gravar() throws Exception {
		Cadastro c = new Cadastro();
		c.setEmail(this.email.get());
		c.setLogradouro(this.logradouro.get());
		c.setNumero(this.numero.get());
		c.setBairro(this.bairro.get());
		c.setCep(this.CEP.get());

		if (Id.get() == 0) {
			contador += 1;
			c.setId(contador);
			cadastroDAO.inserir(c);
		} else {
			c.setId(Id.get());
			cadastroDAO.atualizar(c);
		}
		pesquisarTodos();
		limparTudo();
		System.out.println("Lista tamanho: " + lista.size());
	}

	/**
	 * Metodo que limpa os campos na Tela de Cadastro.
	 */
	public void limparTudo() {
		Id.set(0);
		email.set("");
		logradouro.set("");
		numero.set(0);
		bairro.set("");
		CEP.set(0);
	}

	/**
	 * Metodo que pesquisa uma serie de Cadastro que possuam um Email digitado.
	 */
	public void pesquisarPorEmail() {
		lista.clear();
		lista.addAll(cadastroDAO.pesquisarPorEmail(email.get()));
	}

	/**
	 * Metodo que pesquisa todos os Cadastros existentes atualmente.
	 */
	public void pesquisarTodos() {
		lista.clear();
		lista.addAll(cadastroDAO.pesquisarTodos());
	}

	/**
	 * Metodo responsavel pela obtencao da lista de Cadastro.
	 * 
	 * @return Uma lista de Cadastros.
	 */
	public ObservableList<Cadastro> getLista() {
		return this.lista;
	}

	/**
	 * Metodo responsavel pela obtencao do Atributo ID.
	 * 
	 * @return Um ID de Cadastro.
	 */
	public IntegerProperty idProperty() {
		return this.Id;
	}

	/**
	 * Metodo responsavel pela obtencao do Atributo Email.
	 * 
	 * @return Um Email vinculado ao Cadastro.
	 */
	public StringProperty emailProperty() {
		return this.email;
	}

	/**
	 * Metodo responsavel pela obtencao do Atributo Lougradouro(endereco).
	 * 
	 * @return Um Logradouro vinculado ao Cadastro.
	 */
	public StringProperty logradouroPrperty() {
		return this.logradouro;
	}

	/**
	 * Metodo responsavel pela obtencao do Atributo Numero(endereco).
	 * 
	 * @return Um numero de endereco de um Cadastro.
	 */
	public IntegerProperty numeroProperty() {
		return this.numero;
	}

	/**
	 * Metodo responsavel pela obtencao do Atributo Bairro(endereco).
	 * 
	 * @return Um Bairro de endereco de um Cadastro.
	 */
	public StringProperty bairroProperty() {
		return this.bairro;
	}

	/**
	 * Metodo responsavel pela obtencao do CEP.
	 * 
	 * @return Um CEP vinculado ao Cadastro.
	 */
	public IntegerProperty cepProperty() {
		return this.CEP;
	}
}