package controllers;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TelefoneModel;
import service.TelefoneDAO;
import service.TelefoneDAOImpl;

/**
 * Classe responsável pelo controle de telefones.
 */
public class TelefoneControl {

	private ObservableList<TelefoneModel> lista = FXCollections.observableArrayList();
	private IntegerProperty fone = new SimpleIntegerProperty(0);
	private IntegerProperty cadastroId = new SimpleIntegerProperty(0);
	private TelefoneDAO telefoneDAO = new TelefoneDAOImpl();



	/**
	 * Limpa os dados do telefone na interface.
	 */
	public void limparTudo() {
		fone.set(0);
		cadastroId.set(0);
	}

	/**
	 * Transfere os dados de um modelo de telefone para os campos da interface.
	 * 
	 * @param c o modelo de telefone a ser exibido.
	 */
	public void entidadeParaTela(TelefoneModel c) {
		if (c != null) {
			fone.set(c.getTelefone());
			cadastroId.set(c.getCadastroid());
		}
	}

	/**
	 * Pesquisa todos os telefones na base de dados.
	 * 
	 * @throws Exception se ocorrer um erro ao buscar os telefones.
	 */
	public void pesquisarTodos() throws Exception {
		List<TelefoneModel> tempLista = telefoneDAO.pesquisarTodos();
		lista.clear();
		lista.addAll(tempLista);
	}

	/**
	 * Pesquisa telefones na base de dados pelo número.
	 * 
	 * @throws Exception se ocorrer um erro ao buscar os telefones.
	 */
	public void pesquisarNumero() throws Exception {
		List<TelefoneModel> tempLista = telefoneDAO.pesquisarNumero(fone.get());
		lista.clear();
		lista.addAll(tempLista);
	}

	/**
	 * Grava um novo telefone na base de dados.
	 * 
	 * @throws Exception se ocorrer um erro ao inserir o telefone.
	 */
	public void gravar() throws Exception {
		TelefoneModel telefone = new TelefoneModel();
		telefone.setTelefone(fone.get());
		telefone.setCadastroid(cadastroId.get());
		telefoneDAO.inserir(telefone);
		pesquisarTodos();
		limparTudo();
	}

	/**
	 * Exclui um telefone da base de dados.
	 * 
	 * @param telefone o telefone a ser excluído.
	 * @throws Exception se ocorrer um erro ao excluir o telefone.
	 */
	public void excluir(TelefoneModel telefone) throws Exception {
		telefoneDAO.excluir(telefone);
		pesquisarTodos();
	}

	/**
	 * Retorna a lista observável de telefones.
	 * 
	 * @return a lista de telefones.
	 */
	public ObservableList<TelefoneModel> getLista() {
		return this.lista;
	}

	/**
	 * Retorna a propriedade do número de telefone.
	 * 
	 * @return a propriedade do número de telefone.
	 */
	public IntegerProperty foneProperty() {
		return this.fone;
	}

	/**
	 * Retorna a propriedade do ID do cadastro.
	 * 
	 * @return a propriedade do ID do cadastro.
	 */
	public IntegerProperty cadastroIdProperty() {
		return this.cadastroId;
	}

}