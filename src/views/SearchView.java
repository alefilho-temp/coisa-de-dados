package views;

import java.util.ArrayList;

import components.ContainerComponent;
import components.MarginBottomComponent;
import components.NavComponent;
import components.ProductComponent;
import components.ProductsBox;
import components.ReturnBarComponent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.ProductModel;

/**
 * Classe responsável pela tela de pesquisa de produtos.
 * 
 * Esta classe exibe os resultados de pesquisa com base em uma frase de pesquisa
 * e um tipo de pesquisa especificado (nome, categoria ou vendedor).
 */
public class SearchView extends ContainerComponent {

    protected final HBox searchPhrase; // Caixa que contém a frase de pesquisa
    protected final Text relatedProductsTitle; // Título para produtos relacionados
    protected final ProductsBox productsBox; // Caixa que contém os produtos

    /**
     * Enumeração para os tipos de pesquisa disponíveis.
     */
    public static enum SearchType {
        name,
        category,
        seller
    }

    /**
     * Construtor da classe SearchView.
     * 
     * @param search A frase de pesquisa.
     */
    public SearchView(String search) {
        this(search, SearchType.name); // Chama o construtor com tipo de pesquisa padrão
    }

    /**
     * Construtor da classe SearchView com tipo de pesquisa especificado.
     * 
     * @param search A frase de pesquisa.
     * @param type O tipo de pesquisa (nome, categoria ou vendedor).
     */
    public SearchView(String search, SearchType type) {
        NavComponent nav = new NavComponent(); // Cria a barra de navegação
        nav.getAutoCompleteComponent().getTextField().setText(search); // Define a frase de pesquisa no campo de texto
        body.getChildren().add(nav); // Adiciona a barra de navegação ao corpo

        body.getChildren().add(new ReturnBarComponent()); // Adiciona a barra de retorno

        // Criação do título para os produtos relacionados
        relatedProductsTitle = new Text();
        relatedProductsTitle.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        relatedProductsTitle.setStrokeWidth(0.0);

        String searchTip = "Resultados para: " + search; // Título padrão

        if (type == SearchType.category) {
            searchTip = "Produtos da categoria " + search; // Título para pesquisa por categoria
        }
        if (type == SearchType.seller) {
            searchTip = "Produtos do vendedor " + search; // Título para pesquisa por vendedor
        }

        relatedProductsTitle.setText(searchTip);
        relatedProductsTitle.setFont(new Font(24.0)); // Define a fonte do título

        searchPhrase = new HBox(); // Cria a caixa para a frase de pesquisa
        searchPhrase.getChildren().add(relatedProductsTitle); // Adiciona o título à caixa
        body.getChildren().add(searchPhrase); // Adiciona a caixa ao corpo

        productsBox = new ProductsBox(); // Cria a caixa de produtos
        body.getChildren().add(productsBox); // Adiciona a caixa de produtos ao corpo

        // Criação de uma lista de produtos (exemplo)
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

        // Adiciona os produtos à interface
        products.forEach(product -> {
            productsBox.getChildren().add(new ProductComponent(product));
        });

        // Adiciona margem inferior
        body.getChildren().add(new MarginBottomComponent());
    }
}
