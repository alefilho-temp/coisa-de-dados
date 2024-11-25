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
	
	public CadastroControl() {
		cadastroDAO = new CadastroDAOImpl();
	}
	
	public void entidadeParaTela(Cadastro c) {
		if (c!=null) {
			this.Id.set(c.getId());
			this.email.set(c.getEmail());
			this.logradouro.set(c.getLogradouro());
			this.numero.set(c.getNumero());
			this.bairro.set(c.getBairro());
			this.CEP.set(c.getCep());
		}
	}
	
	public void excluir(Cadastro c) throws Exception {
		System.out.println("Excluido cadastro de id: " + c.getId());
		cadastroDAO.remover(c);
		pesquisarTodos();
	}
	
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
	
	public void limparTudo() {
		Id.set(0);
		email.set("");
		logradouro.set("");
		numero.set(0);
		bairro.set("");
		CEP.set(0);
	}
	
	public void pesquisarPorEmail() {
		lista.clear();
		lista.addAll(cadastroDAO.pesquisarPorEmail(email.get()));
	}
	
	public void pesquisarTodos() {
		lista.clear();
		lista.addAll(cadastroDAO.pesquisarTodos());
	}
	
	public ObservableList<Cadastro> getLista() {
		return this.lista;
	}
	
	public IntegerProperty idProperty() {
		return this.Id;
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}
	
	public StringProperty logradouroPrperty() {
		return this.logradouro;
	}
	
	public IntegerProperty numeroProperty() {
		return this.numero;
	}
	
	public StringProperty bairroProperty() {
		return this.bairro;
	}
	
	public IntegerProperty cepProperty() {
		return this.CEP;
	}
}