package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cupon;
import service.CuponDAO;
import service.CuponDAOImpl;

public class CuponControl {
	
	private IntegerProperty numero_cupon = new SimpleIntegerProperty();
	private IntegerProperty porcentagem_Desconto = new SimpleIntegerProperty();
	private IntegerProperty ClienteCPF = new SimpleIntegerProperty();
	private IntegerProperty ClienteCadastroid = new SimpleIntegerProperty();
	
	private CuponDAO cuponDAO;
	private ObservableList<Cupon> lista = FXCollections.observableArrayList();
	
	public CuponControl() throws Exception{
		cuponDAO = new CuponDAOImpl();
	}
	
	public void entidadeParaTela(Cupon c) {
		if(c!=null) {
			this.numero_cupon.set(c.getNumero_cupon());
			this.porcentagem_Desconto.set(c.getPorcentagem_Desconto());
			this.ClienteCPF.set(c.getClienteCPF());
			this.ClienteCadastroid.set(c.getClienteCadastroid());
		}
	}
	
	public void excluir(Cupon c) throws Exception{
		System.err.println("Cupon excluido: " + c.getNumero_cupon());
		cuponDAO .remover(c);
		pesquisarPorPorcen();
	}
	
	public void gravar() throws Exception{
		Cupon c = new Cupon();
		
		c.setNumero_cupon(this.numero_cupon.get());
		c.setPorcentagem_Desconto(this.porcentagem_Desconto.get());
		c.setClienteCPF(this.ClienteCPF.get());
		c.setClienteCadastroid(this.ClienteCadastroid.get());
		
		cuponDAO.inserir(c);
		pesquisarTodos();
		limparTudo();
		System.out.println("Lista de cartaos: " + lista.size());
	}
	
	public void limparTudo() {
		this.numero_cupon.set(0);
		this.porcentagem_Desconto.set(0);
		this.ClienteCPF.set(0);
		this.ClienteCadastroid.set(0);
	}
	
	public void pesquisarPorPorcen() throws Exception {
		lista.clear();
		lista.addAll(cuponDAO.pesquisarPorNome( porcentagem_Desconto.get()));
	}

	public void pesquisarTodos() throws Exception {
		lista.clear();
		lista.addAll(cuponDAO.pesquisarTodos());
	}

	public IntegerProperty getNumero_cupon() {
		return numero_cupon;
	}

	public IntegerProperty getPorcentagem_Desconto() {
		return porcentagem_Desconto;
	}

	public IntegerProperty getClienteCPF() {
		return ClienteCPF;
	}

	public IntegerProperty getClienteCadastroid() {
		return ClienteCadastroid;
	}

	public CuponDAO getCuponDAO() {
		return cuponDAO;
	}

	public ObservableList<Cupon> getLista() {
		return lista;
	}

}