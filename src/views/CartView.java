package views;

import java.util.ArrayList;

import components.CartItemComponent;
import components.ContainerComponent;
import components.MarginBottomComponent;
import components.NavComponent;
import components.ReturnBarComponent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.CartItemModel;
import models.ProductModel;

/**
 * Classe que representa a visualização do carrinho de compras.
 * 
 * Esta classe gerencia a exibição dos itens no carrinho, o preço total e as interações
 * do usuário relacionadas à finalização da compra.
 */
public class CartView extends ContainerComponent {
    protected final HBox infoBox; // Caixa que contém informações do carrinho
    protected final Text finalPrice; // Texto que exibe o preço final
    protected final AnchorPane anchorPane; // Painel para layout
    protected final Button finishCart; // Botão para finalizar a compra
    protected final VBox cartItensBox; // Caixa que contém os itens do carrinho
    
    // Lista para armazenar os itens do carrinho
    private final ArrayList<CartItemModel> itens;

    /**
     * Construtor da classe CartView.
     * Inicializa os componentes da visualização do carrinho.
     */
    public CartView() {
        itens = new ArrayList<>(); // Inicializa a lista de itens

        body.getChildren().add(new NavComponent());
        body.getChildren().add(new ReturnBarComponent());

        infoBox = new HBox();
        infoBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        infoBox.setPrefHeight(40.0);
        infoBox.setPadding(new Insets(20.0));

        body.getChildren().add(infoBox);

        finalPrice = new Text();
        finalPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        finalPrice.setStrokeWidth(0.0);
        finalPrice.setText("Total: R$1000.00");
        finalPrice.setFont(new Font(24.0));

        anchorPane = new AnchorPane();
        HBox.setHgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefHeight(32.0);

        finishCart = new Button();
        finishCart.setMnemonicParsing(false);
        finishCart.setStyle("-fx-background-color: rgb(255, 222, 89); -fx-background-radius: 10;");
        finishCart.setText("Finalizar Compra");
        finishCart.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        finishCart.setFont(new Font(14.0));
        finishCart.setCursor(Cursor.HAND);

        infoBox.getChildren().add(finalPrice);
        infoBox.getChildren().add(anchorPane);
        infoBox.getChildren().add(finishCart);

        cartItensBox = new VBox();
        cartItensBox.setSpacing(20.0);
        VBox.setMargin(cartItensBox, new Insets(0.0, 20.0, 0.0, 20.0));
        body.getChildren().add(cartItensBox);

        getItensFromDB(); // Carrega os itens do carrinho
        body.getChildren().add(new MarginBottomComponent());
    }

    /**
     * Obtém os itens do carrinho a partir de um banco de dados simulado.
     * Adiciona os itens à lista e à interface do usuário.
     */
    public void getItensFromDB() {
        // Adiciona itens ao carrinho
        for (int i = 0; i < 8; i++) {
            CartItemModel item = new CartItemModel(
                0, 
                new ProductModel(
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
                ),
                10
            );
            itens.add(item);
            cartItensBox.getChildren().add(new CartItemComponent(this, item));
        }

        updateFinalPrice(); // Atualiza o preço final após adicionar os itens
    }

    /**
     * Remove um item do carrinho com base no seu ID.
     * 
     * @param id O identificador do item a ser removido.
     */
    public void removeItem(int id) {
        // Procura o item pelo ID e remove da lista e da interface
        for (int i = 0; i < itens.size(); i++) {
            CartItemModel item = itens.get(i);

            if (item.getProduct().getId() == id) {
                itens.remove(i); // Remove o item da lista
                cartItensBox.getChildren().remove(i); // Remove o componente visual
                break; // Sai do loop após encontrar e remover o item
            }
        }
        // Atualize o preço final se necessário
        updateFinalPrice();
    }

    /**
     * Atualiza a quantidade de um item no carrinho.
     * 
     * @param targetItem O item com a nova quantidade.
     */
    public void updateItemQuantity(CartItemModel targetItem) {
        for (int i = 0; i < itens.size(); i++) {
            CartItemModel item = itens.get(i);

            if (item.getId() == targetItem.getId()) {
                item = targetItem; // Atualiza o item na lista
                break; // Sai do loop após encontrar e atualizar o item
            }
        }
        // Atualize o preço final se necessário
        updateFinalPrice();
    }

    /**
     * Atualiza o preço final do carrinho com base nos itens restantes.
     */
    public void updateFinalPrice() {
        // Lógica para atualizar o preço final com base nos itens restantes
        float total = 0;

        for (CartItemModel item : itens) {
            total += (item.getProduct().getPrice() - (item.getProduct().getPrice() * item.getProduct().getDiscount())) * item.getQuantity();
        }

        finalPrice.setText("Total: R$" + total); // Atualiza o texto do preço final
    }
}