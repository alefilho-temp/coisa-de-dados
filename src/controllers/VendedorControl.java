package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Vendedor;
import service.VendedorDAO;
import service.VendedorDAOImpl;

public class VendedorControl {
	private ObservableList<Vendedor> lista = FXCollections.observableArrayList();
	private IntegerProperty CadastroID = new SimpleIntegerProperty(0);
	private StringProperty Nome_Loja = new SimpleStringProperty("");
	private StringProperty Informacao_Loja = new SimpleStringProperty("");
	
	private VendedorDAO vendedorDAO;
	private int contador = 0;
	
	public VendedorControl() {
		vendedorDAO = new VendedorDAOImpl();
	}
	
	public void entidadeParaTela(Vendedor v) {
		if (v!=null) {
			this.CadastroID.set(v.getCadastroId());
			this.Nome_Loja.set(v.getNomeLoja());
			this.Informacao_Loja.set(v.getInformacaoLoja());
		}
	}
	
	public void excluir(Vendedor v) throws Exception {
		System.out.println("Excluido cadastro de id: " + v.getCadastroId());
		vendedorDAO.remover(v);
		pesquisarTodos();
	}
	
	public void gravar() throws Exception {
		Vendedor v = new Vendedor();
		v.setCadastroId(this.CadastroID.get());
		v.setNomeLoja(this.Nome_Loja.get());
		v.setInformacaoLoja(this.Informacao_Loja.get());
		vendedorDAO.inserir(v);
		pesquisarTodos();
		limparTudo();
		System.out.println("Lista tamanho: " + lista.size());
	}
	
	public void limparTudo() {
		CadastroID.set(0);
		Nome_Loja.set("");
		Informacao_Loja.set("");
	}
	
	public void pesquisarPorEmail() {
		lista.clear();
		lista.addAll(vendedorDAO.pesquisarPorNome(Nome_Loja.get()));
	}
	
	public void pesquisarTodos() {
		lista.clear();
		lista.addAll(vendedorDAO.pesquisarTodos());
	}
	
	public ObservableList<Vendedor> getLista() {
		return this.lista;
	}
	
	public IntegerProperty idProperty() {
		return this.CadastroID;
	}
	
	public StringProperty nomeProperty() {
		return this.Nome_Loja;
	}
	
	public StringProperty informacaoProperty() {
		return this.Informacao_Loja;
	}	
}
