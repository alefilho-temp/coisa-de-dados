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

	public CartaoControl() throws Exception {
		cartaoDAO = new CartaoDAOImpl();
	}

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

	public void excluir(Cartao c) throws Exception {
		System.err.println("Excluido cartao de apelido: " + c.getNome_Cartao());
		cartaoDAO.remover(c);
		pesquisarTodos();
	}

	public void gravar() throws Exception {
		Cartao c = new Cartao();
		
		c.setTipo_Cartao(this.Tipo_Cartao.get());
		c.setNumero(this.Numero.get());
		c.setNome_Cartao(this.Nome_Cartao.get());
		c.setData_Vencimento(this.Data_Vencimento.get());
		c.setCodigo_Seguranca(this.Codigo_Seguranca.get());
		c.setClienteCPF(this.ClienteCPF.get());
		c.setClienteCadastroid(this.ClienteCadastroid.get());
		System.out.println("INSERTANDO -> " + 
			    "Numero: " + c.getNumero() + 
			    ", Tipo: " + c.getTipo_Cartao() + 
			    ", Nome: " + c.getNome_Cartao() + 
			    ", Data: " + c.getData_Vencimento() + 
			    ", Codigo: " + c.getCodigo_Seguranca() + 
			    ", CPF: " + c.getClienteCPF() + 
			    ", CadastroId: " + c.getClienteCadastroid());
		cartaoDAO.inserir(c);
		pesquisarTodos();
		limparTudo();
		System.out.println("Lista de cartaos: " + lista.size());
	}

	public void limparTudo() {
		this.Tipo_Cartao.set("");
		this.Numero.set(0);
		this.Nome_Cartao.set("");
		this.Data_Vencimento.set(LocalDate.now());
		this.Codigo_Seguranca.set(0);
		this.ClienteCPF.set(0);
		this.ClienteCadastroid.set(0);
	}

	public void pesquisarPorNome() throws Exception {
		lista.clear();
		lista.addAll(cartaoDAO.pesquisarPorNome( Nome_Cartao.get()));
	}

	public void pesquisarTodos() throws Exception {
		lista.clear();
		lista.addAll(cartaoDAO.pesquisarTodos());
	}

	public StringProperty getTipo_Cartao() {
		return Tipo_Cartao;
	}

	public IntegerProperty getNumero() {
		return Numero;
	}

	public StringProperty getNome_Cartao() {
		return Nome_Cartao;
	}

	public ObjectProperty<LocalDate> getData_Vencimento() {
		return Data_Vencimento;
	}

	public IntegerProperty getCodigo_Seguranca() {
		return Codigo_Seguranca;
	}

	public IntegerProperty getClienteCPF() {
		return ClienteCPF;
	}

	public IntegerProperty getClienteCadastroid() {
		return ClienteCadastroid;
	}

	public ObservableList<Cartao> getLista() {
		return lista;
	}

}