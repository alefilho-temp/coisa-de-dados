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
import models.Vendedor_Pessoal;
import service.Vendedor_PessoalDAO;
import service.Vendedor_PessoalDAOImpl;

/**
 * Classe responsavel pelo controle das informacoes do vendedor pessoal
 */
public class Vendedor_PessoalControl {

	private IntegerProperty vendedorCadastroId = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty inscricaoEstadual = new SimpleStringProperty("");
	private IntegerProperty cpf = new SimpleIntegerProperty(0);

	private ObservableList<Vendedor_Pessoal> lista = FXCollections.observableArrayList();
	private Vendedor_PessoalDAO vendedorPessoalDao;


	/**
	 * Construtor da classe inicializa a classe que realiza as interacoes com o
	 * banco de dados
	 */
	public Vendedor_PessoalControl() {
		vendedorPessoalDao = new Vendedor_PessoalDAOImpl();
	}

	/**
	 * Insere as informacoes de um vendedor pessoal na tela
	 * 
	 * @param v O vendedor pessoal que tera as informacoes exibidas na tela
	 */
	public void entidadeParaTela(Vendedor_Pessoal v) {
		if (v != null) {
			this.vendedorCadastroId.set(v.getVendedorCadastro_Id());
			this.nome.set(v.getNome());
			this.dataNascimento.set(v.getData_Nascimento());
			this.inscricaoEstadual.set(v.getInscricao_Estadual());
			this.cpf.set(v.getCPF());
		}
	}

	/**
	 * Converte os dados da tela para um objeto Vendedor_Pessoal
	 * 
	 * @return O objeto Vendedor_Pessoal com os dados preenchidos a partir da tela
	 */
	public Vendedor_Pessoal telaParaEntidade() {
		Vendedor_Pessoal v = new Vendedor_Pessoal();
		v.setVendedorCadastro_Id(this.vendedorCadastroId.get());
		v.setNome(this.nome.get());
		v.setData_Nascimento(this.dataNascimento.get());
		v.setInscricao_Estadual(this.inscricaoEstadual.get());
		v.setCPF(this.cpf.get());
		return v;
	}

	/**
	 * Grava as informacoes de um vendedor pessoal no banco de dados
	 * 
	 * @throws Exception Caso ocorra uma falha na insercao no banco de dados
	 */
	public void gravar() throws Exception {
		Vendedor_Pessoal v = telaParaEntidade();
		vendedorPessoalDao.inserir(v);

		pesquisarTodos();
		limparTudo();
	}

	/**
	 * Pesquisa um vendedor pessoal pelo CPF
	 * 
	 * @throws Exception Caso ocorra uma falha na pesquisa
	 */
	public void pesquisarPorCPF() throws Exception {
		lista.clear();
		lista.addAll(vendedorPessoalDao.pesquisarPorCPF(cpf.get()));
	}

	/**
	 * Pesquisa todos os vendedores pessoais
	 * 
	 * @throws Exception Caso ocorra uma falha na pesquisa
	 */
	public void pesquisarTodos() throws Exception {
		lista.clear();
		lista.addAll(vendedorPessoalDao.pesquisarTodos());
	}

	/**
	 * Remove um vendedor pessoal do banco de dados
	 * 
	 * @param v O vendedor pessoal a ser removido
	 * @throws Exception Caso ocorra uma falha na remocao
	 */
	public void remover(Vendedor_Pessoal v) throws Exception {
		vendedorPessoalDao.remover(v);
		pesquisarTodos();
	}

	/**
	 * Limpa os campos da tela
	 */
	public void limparTudo() {
		vendedorCadastroId.set(0);
		nome.set("");
		dataNascimento.set(LocalDate.now());
		inscricaoEstadual.set("");
		cpf.set(0);
	}

	/**
	 * Obtem a propriedade de cadastro do vendedor
	 * 
	 * @return A propriedade IntegerProperty do cadastro do vendedor
	 */
	public IntegerProperty vendedorCadastroIdProperty() {
		return this.vendedorCadastroId;
	}

	/**
	 * Obtem a propriedade do nome do vendedor
	 * 
	 * @return A propriedade StringProperty do nome do vendedor
	 */
	public StringProperty nomeProperty() {
		return this.nome;
	}

	/**
	 * Obtem a propriedade da data de nascimento do vendedor
	 * 
	 * @return A propriedade ObjectProperty da data de nascimento do vendedor
	 */
	public ObjectProperty<LocalDate> dataNascimentoProperty() {
		return this.dataNascimento;
	}

	/**
	 * Obtem a propriedade da inscricao estadual do vendedor
	 * 
	 * @return A propriedade StringProperty da inscricao estadual do vendedor
	 */
	public StringProperty inscricaoEstadualProperty() {
		return this.inscricaoEstadual;
	}

	/**
	 * Obtem a propriedade do CPF do vendedor
	 * 
	 * @return A propriedade IntegerProperty do CPF do vendedor
	 */
	public IntegerProperty cpfProperty() {
		return this.cpf;
	}

	/**
	 * Obtem a lista de vendedores pessoais
	 * 
	 * @return A lista observavel de vendedores pessoais
	 */
	public ObservableList<Vendedor_Pessoal> getLista() {
		return this.lista;
	}
}