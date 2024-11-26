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

/**
 * Classe responsavel pelo controle de categorias.
 */
public class CategoryControl {

	private ObservableList<CategoryModel> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty name = new SimpleStringProperty("");
	private StringProperty color = new SimpleStringProperty("");
	private StringProperty imagePath = new SimpleStringProperty("");
	private CategoryDAO categoryDAO = new CategoryDAOImpl();

	private int contador = 0;

	/**
	 * Limpa todos os campos de entrada.
	 */
	public void limparTudo() {
		id.set(0);
		name.set("");
		color.set("");
		imagePath.set("");
	}

	/**
	 * Transfere os dados de uma entidade CategoryModel para os campos da tela.
	 * 
	 * @param c A categoria cujos dados serao exibidos.
	 */
	public void entidadeParaTela(CategoryModel c) {
		if (c != null) {
			id.set(c.getId());
			name.set(c.getName());
			color.set(c.getColor());
			imagePath.set(c.getImagePath());
		}
	}

	/**
	 * Realiza uma pesquisa para carregar todas as categorias.
	 * 
	 * @throws Exception Caso ocorra erro na consulta ao banco de dados.
	 */
	public void pesquisarTodos() throws Exception {
		List<CategoryModel> tempLista = categoryDAO.pesquisarTodos();
		lista.clear();
		lista.addAll(tempLista);
	}

	/**
	 * Realiza uma pesquisa por nome de categoria.
	 * 
	 * @throws Exception Caso ocorra erro na consulta ao banco de dados.
	 */
	public void pesquisarNome() throws Exception {
		List<CategoryModel> tempLista = categoryDAO.pesquisarNome(name.get());
		lista.clear();
		lista.addAll(tempLista);
	}

	/**
	 * Grava uma nova categoria ou atualiza uma existente.
	 * 
	 * @throws Exception Caso ocorra erro ao salvar os dados.
	 */
	public void gravar() throws Exception {
		CategoryModel categoria = new CategoryModel(contador, null, null, null);
		categoria.setName(name.get());
		categoria.setColor(color.get());
		categoria.setImagePath(imagePath.get());
		if (id.get() == 0) {
			categoria.setId(contador++);
			categoryDAO.inserir(categoria);
		} else {
			categoria.setId(id.get());
			categoryDAO.atualizar(categoria);
		}
		pesquisarTodos();
		limparTudo();
	}

	/**
	 * Exclui uma categoria.
	 * 
	 * @param categoria A categoria que sera excluida.
	 * @throws Exception Caso ocorra erro ao excluir os dados.
	 */
	public void excluir(CategoryModel categoria) throws Exception {
		categoryDAO.excluir(categoria);
		pesquisarTodos();
	}

	/**
	 * Retorna a lista observavel de categorias.
	 * 
	 * @return A lista de categorias.
	 */
	public ObservableList<CategoryModel> getLista() {
		return this.lista;
	}

	/**
	 * Propriedade do ID da categoria.
	 * 
	 * @return A propriedade do ID.
	 */
	public IntegerProperty idProperty() {
		return this.id;
	}

	/**
	 * Propriedade do nome da categoria.
	 * 
	 * @return A propriedade do nome.
	 */
	public StringProperty nameProperty() {
		return this.name;
	}

	/**
	 * Propriedade da cor da categoria.
	 * 
	 * @return A propriedade da cor.
	 */
	public StringProperty colorProperty() {
		return this.color;
	}

	/**
	 * Propriedade do caminho da imagem da categoria.
	 * 
	 * @return A propriedade do caminho da imagem.
	 */
	public StringProperty imagePathProperty() {
		return this.imagePath;
	}
}
