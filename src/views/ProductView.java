package views;

import java.util.ArrayList;

import common.ProductsQueries;
import common.ViewController;
import components.ContainerComponent;
import components.MarginBottomComponent;
import components.NavComponent;
import components.ProductComponent;
import components.ProductDetailsComponent;
import components.ProductsBox;
import components.ReturnBarComponent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.ProductModel;
import models.SellerModel;
import models.Vendedor;

/**
 * Classe que representa a visualização de um produto específico.
 * 
 * Esta classe exibe os detalhes do produto selecionado, juntamente com produtos recomendados.
 */
public class ProductView extends ContainerComponent {

    protected final HBox relatedProductsTitleBox; // Caixa para o título de produtos recomendados
    protected final Text relatedProductsTitle; // Texto do título de produtos recomendados

    /**
     * Construtor da classe ProductView.
     * 
     * @param product O modelo do produto a ser exibido.
     */
    public ProductView(ProductModel product) {
        body.getChildren().add(new NavComponent()); // Adiciona a barra de navegação
        body.getChildren().add(new ReturnBarComponent()); // Adiciona a barra de retorno

        Vendedor seller = new Vendedor();

        try {
            seller = ProductsQueries.getVendedorPorId(product.getSellerId());
        } catch (Exception e) {
            // TODO: handle exception
            seller.setNomeLoja("Vendedor Desconhecido");
        }
        System.out.println(seller.getCadastroId());
        System.out.println(seller.getInformacaoLoja());
        System.out.println(seller.getNomeLoja());

        // Adiciona os detalhes do produto
        body.getChildren().add(new ProductDetailsComponent(
            product, 
            new SellerModel(product.getSellerId(), seller.getNomeLoja()) // Informações do vendedor
        ));

        // Cria o título para produtos recomendados
        relatedProductsTitle = new Text();
        relatedProductsTitle.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        relatedProductsTitle.setStrokeWidth(0.0);
        relatedProductsTitle.setText("Produtos Recomendados");
        relatedProductsTitle.setFont(new Font(24.0));

        relatedProductsTitleBox = new HBox();
        relatedProductsTitleBox.getChildren().add(relatedProductsTitle); // Adiciona o título à caixa
        body.getChildren().add(relatedProductsTitleBox); // Adiciona a caixa ao corpo

        ProductsBox productsBox = new ProductsBox();
        body.getChildren().add(productsBox); // Adiciona a caixa de produtos ao corpo

        // Cria uma lista de produtos recomendados
        ArrayList<ProductModel> products = new ArrayList<ProductModel>();

        try {
            products = ProductsQueries.getRecomendados(product.getSellerId());
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Adiciona os produtos recomendados à interface
        products.forEach(product_ -> {
            productsBox.getChildren().add(new ProductComponent(product_));
        });

        // Adiciona margem inferior
        body.getChildren().add(new MarginBottomComponent());
    }
}
