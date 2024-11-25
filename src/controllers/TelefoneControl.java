package controllers;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TelefoneModel;
import service.TelefoneDAO;
import service.TelefoneDAOImpl;

public class TelefoneControl {

	private ObservableList<TelefoneModel> lista = FXCollections.observableArrayList();
    private IntegerProperty fone = new SimpleIntegerProperty(0);
    private IntegerProperty cadastroId = new SimpleIntegerProperty(0);
    private TelefoneDAO telefoneDAO = new TelefoneDAOImpl();

    private int contador = 0;
    
    public void limparTudo() { 
    	fone.set(0);
        cadastroId.set(0);
	    }
	    
	    public void entidadeParaTela(TelefoneModel c) { 
	        if (c != null) { 
	        	fone.set(c.getTelefone());
	            cadastroId.set(c.getCadastroid());
	        }
	    }
	    
	    public void pesquisarTodos() throws Exception { 
	        List<TelefoneModel> tempLista = telefoneDAO.pesquisarTodos();
	        lista.clear();
	        lista.addAll(tempLista);
	    }

	    public void pesquisarNumero() throws Exception { 
	        List<TelefoneModel> tempLista = 
	        		telefoneDAO.pesquisarNumero(fone.get());
	        lista.clear();
	        lista.addAll(tempLista);
	    }

	    public void gravar() throws Exception { 
	    	TelefoneModel telefone = new TelefoneModel();
	    	telefone.setTelefone(fone.get());
	    	telefone.setCadastroid(cadastroId.get());
	    	telefoneDAO.inserir(telefone);
	    	telefoneDAO.inserir(telefone);
	   
	        pesquisarTodos();
	        limparTudo();
	    }
	    
	    public void excluir(TelefoneModel telefone) throws Exception { 
	    	telefoneDAO.excluir(telefone);
	        pesquisarTodos();
	    }

	    public ObservableList<TelefoneModel> getLista() { 
	        return this.lista;
	    }

	    public IntegerProperty foneProperty() { 
	        return this.fone;
	    }
	    public IntegerProperty cadastroIdProperty() { 
	        return this.cadastroId;
	    }

	}
