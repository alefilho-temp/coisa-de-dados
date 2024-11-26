package views;

import java.util.ArrayList;
import java.util.Arrays;

import common.ProductsQueries;
import components.BannerComponent;
import components.CategoriesBoxComponent;
import components.CategoryComponent;
import components.ContainerComponent;
import components.MarginBottomComponent;
import components.NavComponent;
import components.ProductComponent;
import components.ProductsBox;
import models.CategoryModel;
import models.ProductModel;

/**
 * Classe que representa a visualização da página inicial.
 * 
 * Esta classe gerencia a exibição das categorias e produtos disponíveis na loja.
 */
public class HomeView extends ContainerComponent {

    protected final CategoriesBoxComponent categoriesBox; // Caixa que contém as categorias
    protected final ProductsBox productsBox; // Caixa que contém os produtos

    /**
     * Construtor da classe HomeView.
     * Inicializa os componentes da página inicial.
     */
    public HomeView() {

        // Adiciona a barra de navegação
        body.getChildren().add(new NavComponent());

        // Adiciona o banner
        body.getChildren().add(new BannerComponent());

        categoriesBox = new CategoriesBoxComponent();
        body.getChildren().add(categoriesBox);

        // Cria uma lista de categorias
        ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>();

        try {
            categories = ProductsQueries.getCategorias();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Adiciona cada categoria à interface
        categories.forEach(categorie -> {
            categoriesBox.getChildren().add(new CategoryComponent(categorie));
        });

        productsBox = new ProductsBox();
        body.getChildren().add(productsBox);

        // Cria uma lista de produtos
        ArrayList<ProductModel> products = new ArrayList<ProductModel>();

        try {
            products = ProductsQueries.getProdutosTela();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Adiciona cada produto à interface
        products.forEach(product -> {
            productsBox.getChildren().add(new ProductComponent(product));
        });

        // Adiciona margem inferior
        body.getChildren().add(new MarginBottomComponent());
    }
}
