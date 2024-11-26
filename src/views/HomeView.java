package views;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>(Arrays.asList(
            new CategoryModel(0, "Celulares", "products/celular.png", "rgb(255, 255, 140)"),
            new CategoryModel(1, "Video Games", "products/xbox.png", "rgb(255, 255, 140)"),
            new CategoryModel(2, "Eletro-domesticos", "products/air-fryer.png", "rgb(255, 255, 140)"),
            new CategoryModel(3, "Televisores", "products/tv.png", "rgb(255, 255, 140)"),
            new CategoryModel(4, "Livros", "products/livro.png", "rgb(255, 255, 140)"),
            new CategoryModel(5, "Cozinha", "products/batedeira.png", "rgb(255, 255, 140)")
        ));

        // Adiciona cada categoria à interface
        categories.forEach(categorie -> {
            categoriesBox.getChildren().add(new CategoryComponent(categorie));
        });

        productsBox = new ProductsBox();
        body.getChildren().add(productsBox);

        // Cria uma lista de produtos
        ArrayList<ProductModel> products = new ArrayList<ProductModel>();

        for (int i = 0; i < 8; i++) {
            products.add(new ProductModel(
                0,
                "Samsung Galaxy Z Fold5 512GB Tela dobrável de 7.6\" Dual Chip 12GB RAM Câm. Tripla até 50MP + Selfie 10MP – Creme",
                5299.00f,
                0.10f,
                "products/galaxy-fold.png",
                "<div>\r\n" + 
                "    <h2>O que você precisa saber sobre este produto</h2>\r\n" + 
                "    <ul>\r\n" + 
                "        <li>Memória RAM: 12 GB</li>\r\n" + 
                "        <li>Compatível com redes 5G</li>\r\n" + 
                "        <li>Tela AMOLED de 6.8\"</li>\r\n" + 
                "        <li>Tem 4 câmeras traseiras de 200Mpx/10Mpx/12Mpx/10Mpx</li>\r\n" + 
                "        <li>Câmeras frontais de 12mp</li>\r\n" + 
                "        <li>Processador Snapdragon 8 Gen 2 Octa-Core de 3.36GHz com 12GB de RAM</li>\r\n" + 
                "        <li>Bateria de 5000mAh com carregamento sem fio</li>\r\n" + 
                "        <li>Memória interna de 256GB</li>\r\n" + 
                "        <li>Tipo de Slot de Chip: Chip 1 + Chip 2 ou eSIM</li>\r\n" + 
                "    </ul>\r\n" + 
                "</div>",
                0,
                0
            ));
        }

        // Adiciona cada produto à interface
        products.forEach(product -> {
            productsBox.getChildren().add(new ProductComponent(product));
        });

        // Adiciona margem inferior
        body.getChildren().add(new MarginBottomComponent());
    }
}
// cadastro 
// cadastro cliente
// cadastro vendedor
// cadastro vendedor pessoal
// cadastro vendedor empresarial
// produto
// cartao
// 
