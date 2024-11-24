package views;

import java.util.ArrayList;

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

public class ProductView extends ContainerComponent {

    protected final HBox relatedProductsTitleBox;
    protected final Text relatedProductsTitle;

    public ProductView(ProductModel product) {


        body.getChildren().add(new NavComponent());


        body.getChildren().add(new ReturnBarComponent());


        ////

        body.getChildren().add(new ProductDetailsComponent(
            product, 
            new SellerModel(
                0, 
                "Samsung Loja Oficial"
            )
        ));

        ////


        relatedProductsTitle = new Text();

        relatedProductsTitle.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        relatedProductsTitle.setStrokeWidth(0.0);
        relatedProductsTitle.setText("Produtos Recomendados");
        relatedProductsTitle.setFont(new Font(24.0));
        


        relatedProductsTitleBox = new HBox();

        relatedProductsTitleBox.getChildren().add(relatedProductsTitle);
        body.getChildren().add(relatedProductsTitleBox);

        ProductsBox productsBox = new ProductsBox();
        body.getChildren().add(productsBox);

        // int id, String name, float price, float discount, String imagePath, String description, int categoryId, int sellerId
        ArrayList<ProductModel> products = new ArrayList<ProductModel>();

        for (int i = 0; i < 4; i++) {
            products.add(new ProductModel(
                0,
                "Samsung Galaxy Z Fold5 512GB Tela dobrável de 7.6\" Dual Chip 12GB RAM Câm. Tripla até 50MP + Selfie 10MP – Creme",
                5299.00f,
                0.10f,
                "products/galaxy-fold.png",
                    "<div>\r\n" + //
                    "    <h2>O que você precisa saber sobre este produto</h2>\r\n" + //
                    "    <ul>\r\n" + //
                    "        <li>Memória RAM: 12 GB</li>\r\n" + //
                    "        <li>Compatível com redes 5G</li>\r\n" + //
                    "        <li>Tela AMOLED de 6.8\"</li>\r\n" + //
                    "        <li>Tem 4 câmeras traseiras de 200Mpx/10Mpx/12Mpx/10Mpx</li>\r\n" + //
                    "        <li>Câmeras frontais de 12mp</li>\r\n" + //
                    "        <li>Processador Snapdragon 8 Gen 2 Octa-Core de 3.36GHz com 12GB de RAM</li>\r\n" + //
                    "        <li>Bateria de 5000mAh com carregamento sem fio</li>\r\n" + //
                    "        <li>Memória interna de 256GB</li>\r\n" + //
                    "        <li>Tipo de Slot de Chip: Chip 1 + Chip 2 ou eSIM</li>\r\n" + //
                    "    </ul>\r\n" + //
                    "</div>",
                0,
                0
            ));
        }

        products.forEach(product_ -> {
            productsBox.getChildren().add(new ProductComponent(product_));
        });


        // Add Margin
        body.getChildren().add(new MarginBottomComponent());
    }
}