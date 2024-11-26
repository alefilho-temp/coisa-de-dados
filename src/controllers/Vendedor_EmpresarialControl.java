package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Vendedor_Empresarial;
import service.Vendedor_EmpresarialDAO;
import service.Vendedor_EmpresarialDAOImpl;

/**
 * Classe responsavel pelo controle de vendedores empresariais.
 */
public class Vendedor_EmpresarialControl {

	private IntegerProperty vendedorCadastroId = new SimpleIntegerProperty(0);
	private IntegerProperty cnpj = new SimpleIntegerProperty(0);
	private StringProperty razaoSocial = new SimpleStringProperty("");
	private StringProperty informacaoCobranca = new SimpleStringProperty("");
	private ObservableList<Vendedor_Empresarial> lista = FXCollections.observableArrayList();
	private Vendedor_EmpresarialDAO vendedorEmpresarialDao;

	/**
	 * Construtor da classe Vendedor_EmpresarialControl.
	 */
	public Vendedor_EmpresarialControl() {
		vendedorEmpresarialDao = new Vendedor_EmpresarialDAOImpl();
	}

	/**
	 * Transfere os dados de um modelo de vendedor empresarial para os campos da
	 * interface.
	 * 
	 * @param v o modelo de vendedor empresarial a ser exibido.
	 */
	public void entidadeParaTela(Vendedor_Empresarial v) {
		if (v != null) {
			this.vendedorCadastroId.set(v.getVendedorCadastro_Id());
			this.cnpj.set(v.getCNPJ());
			this.razaoSocial.set(v.getRazao_Social());
			this.informacaoCobranca.set(v.getInformacao_cobranca());
		}
	}

	/**
	 * Transfere os dados da interface para um modelo de vendedor empresarial.
	 * 
	 * @return o modelo de vendedor empresarial preenchido com os dados da
	 *         interface.
	 */
	public Vendedor_Empresarial telaParaEntidade() {
		Vendedor_Empresarial v = new Vendedor_Empresarial();
		v.setVendedorCadastro_Id(this.vendedorCadastroId.get());
		v.setCNPJ(this.cnpj.get());
		v.setRazao_Social(this.razaoSocial.get());
		v.setInformacao_cobranca(this.informacaoCobranca.get());
		return v;
	}

	/**
	 * Grava um novo vendedor empresarial na base de dados.
	 * 
	 * @throws Exception se ocorrer um erro ao inserir o vendedor empresarial.
	 */
	public void gravar() throws Exception {
		Vendedor_Empresarial v = telaParaEntidade();
		vendedorEmpresarialDao.inserir(v);
		pesquisarTodos();
		limparTudo();
	}

	/**
	 * Pesquisa vendedores empresariais por CNPJ.
	 * 
	 * @throws Exception se ocorrer um erro ao buscar os vendedores empresariais.
	 */
	public void pesquisarPorCNPJ() throws Exception {
		lista.clear();
		lista.addAll(vendedorEmpresarialDao.pesquisarPorCNPJ(cnpj.get()));
	}

	/**
	 * Pesquisa todos os vendedores empresariais na base de dados.
	 * 
	 * @throws Exception se ocorrer um erro ao buscar os vendedores empresariais.
	 */
	public void pesquisarTodos() throws Exception {
		lista.clear();
		lista.addAll(vendedorEmpresarialDao.pesquisarTodos());
	}

	/**
	 * Remove um vendedor empresarial da base de dados.
	 * 
	 * @param v o vendedor empresarial a ser removido.
	 * @throws Exception se ocorrer um erro ao remover o vendedor empresarial.
	 */
	public void remover(Vendedor_Empresarial v) throws Exception {
		vendedorEmpresarialDao.remover(v);
		pesquisarTodos();
	}

	/**
	 * Limpa os campos da interface.
	 */
	public void limparTudo() {
		vendedorCadastroId.set(0);
		cnpj.set(0);
		razaoSocial.set("");
		informacaoCobranca.set("");
	}

	/**
	 * Retorna a propriedade do ID do cadastro do vendedor.
	 * 
	 * @return a propriedade do ID do cadastro.
	 */
	public IntegerProperty vendedorCadastroIdProperty() {
		return this.vendedorCadastroId;
	}

	/**
	 * Retorna a propriedade do CNPJ.
	 * 
	 * @return a propriedade do CNPJ.
	 */
	public IntegerProperty cnpjProperty() {
		return this.cnpj;
	}

	/**
	 * Retorna a propriedade da razão social.
	 * 
	 * @return a propriedade da razão social.
	 */
	public StringProperty razaoSocialProperty() {
		return this.razaoSocial;
	}

	/**
	 * Retorna a propriedade das informações de cobrança.
	 * 
	 * @return a propriedade das informações de cobrança.
	 */
	public StringProperty informacaoCobrancaProperty() {
		return this.informacaoCobranca;
	}

	/**
	 * Retorna a lista observavel de vendedores empresariais.
	 * 
	 * @return a lista de vendedores empresariais.
	 */
	public ObservableList<Vendedor_Empresarial> getLista() {
		return this.lista;
	}
}