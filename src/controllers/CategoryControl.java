package controllers;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.CategoryModel;
import service.CategoryDAO;
import service.CategoryDAOImpl;

public class CategoryControl {

	private ObservableList<CategoryModel> lista = FXCollections.observableArrayList();
    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty color = new SimpleStringProperty("");
    private StringProperty imagePath = new SimpleStringProperty("");
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    private int contador = 0;
    
    public void limparTudo() { 
        id.set(0);
        name.set("");
        color.set("");
        imagePath.set("");
    }
    
    public void entidadeParaTela(CategoryModel c) { 
        if (c != null) { 
            id.set(c.getId());
            name.set(c.getName());
            color.set(c.getColor());
            imagePath.set(c.getImagePath());
        }
    }
    
    public void pesquisarTodos() throws Exception { 
        List<CategoryModel> tempLista = categoryDAO.pesquisarTodos();
        lista.clear();
        lista.addAll(tempLista);
    }

    public void pesquisarNome() throws Exception { 
        List<CategoryModel> tempLista = 
                categoryDAO.pesquisarNome(name.get());
        lista.clear();
        lista.addAll(tempLista);
    }

    public void gravar() throws Exception { 
        CategoryModel categoria = new CategoryModel(contador, null, null, null);
        categoria.setName(name.get());
        categoria.setColor(color.get());
        categoria.setImagePath(imagePath.get());
        if (id.get() == 0)  {
            categoria.setId(contador++);
            categoryDAO.inserir(categoria);
        } else { 
            categoria.setId( id.get() );
            categoryDAO.atualizar(categoria);
        }
        pesquisarTodos();
        limparTudo();
    }
    
    public void excluir(CategoryModel categoria) throws Exception { 
        categoryDAO.excluir(categoria);
        pesquisarTodos();
    }

    public ObservableList<CategoryModel> getLista() { 
        return this.lista;
    }

    public IntegerProperty idProperty() { 
        return this.id;
    }
    public StringProperty nameProperty() { 
        return this.name;
    }
    public StringProperty colorProperty() { 
        return this.color;
    }
    public StringProperty imagePathProperty() { 
        return this.imagePath;
    }
}