package views;

import java.io.File;
import java.util.List;

import controllers.ProductControl;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import models.CategoryModel;
import models.ProductModel;
import service.CategoryDAOImpl;

/**
 * Classe que representa a visualização de gerenciamento de produtos.
 * 
 * Esta classe permite a adição, edição e remoção de produtos na aplicação.
 */
public class ProductManagementView extends BorderPane {
    private TextField nameField; // Campo para o nome do produto
    private TextField priceField; // Campo para o preço do produto
    private TextField discountField; // Campo para o desconto do produto
    private TextField imagePathField; // Campo para o caminho da imagem do produto
    private HTMLEditor descriptionEditor; // Editor HTML para a descrição do produto
    private ComboBox<Integer> categoryIdComboBox; // ComboBox para seleção da categoria
    private TableView<ProductModel> productTableView; // Tabela para exibição dos produtos
    private ProductControl control; // Controle de produtos
    private ProductModel selectedProduct; // Produto atualmente selecionado
    private List<CategoryModel> categorias;
    private CategoryDAOImpl DaoImpl = new CategoryDAOImpl();

    /**
     * Construtor da classe ProductManagementView.
     * Inicializa os componentes da visualização de gerenciamento de produtos.
     */
    public ProductManagementView() {
        try {
            control = new ProductControl(); // Inicializa o controle de produtos
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Falha na inicialização do ProductControl.");
        }

        setPadding(new Insets(10));
        GridPane formPane = createFormPane(); // Cria o painel de formulário
        productTableView = new TableView<>();
        productTableView.setItems(control.getProductList()); // Define a lista de produtos na tabela
        generateColumns(); // Gera as colunas da tabela

        // Adiciona um listener para a seleção de itens na tabela
        productTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedProduct = newSelection; // Atualiza o produto selecionado
                populateFields(selectedProduct); // Popula os campos com os dados do produto selecionado
            } else {
                clearFields(); // Limpa os campos se nenhuma seleção
            }
        });

        setTop(formPane); // Define o painel de formulário na parte superior
        setCenter(productTableView); // Define a tabela de produtos no centro
    }

    /**
     * Cria o painel de formulário para entrada de dados do produto.
     * 
     * @return O painel de formulário configurado.
     */
    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setVgap(8);
        formPane.setHgap(10);

        // Criação dos rótulos e campos de entrada
        Label nameLabel = new Label("Nome:");
        nameField = new TextField();

        Label priceLabel = new Label("Preço:");
        priceField = new TextField();

        Label discountLabel = new Label("Desconto (%):");
        discountField = new TextField();
        discountField.setText("0");

        Label imagePathLabel = new Label("Imagem:");
        imagePathField = new TextField();
        Button imageButton = new Button("Escolha");

        Label descriptionLabel = new Label("Descrição:");
        descriptionEditor = new HTMLEditor();

        Label categoryIdLabel = new Label("Categoria:");
        categoryIdComboBox = new ComboBox<>();
        try {
			categorias = DaoImpl.pesquisarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
        for(CategoryModel c : categorias) {
        	categoryIdComboBox.getItems().add(c.getId()); // IDs de categorias
        	}

        Button submitButton = new Button("Salvar");
        submitButton.setOnAction(e -> saveProduct()); // Ação ao clicar no botão de salvar

        // Configuração do seletor de arquivos para seleção de imagem
        FileChooser fileChooser = new FileChooser();
        imageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                imagePathField.setText(file.getAbsolutePath()); // Define o caminho da imagem selecionada
            }
        });

        // Adiciona os componentes ao grid
        formPane.add(nameLabel, 0, 0);
        formPane.add(nameField, 1, 0);
        formPane.add(priceLabel, 0, 1);
        formPane.add(priceField, 1, 1);
        formPane.add(discountLabel, 0, 2);
        formPane.add(discountField, 1, 2);
        formPane.add(imagePathLabel, 0, 3);
        formPane.add(imagePathField, 1, 3);
        formPane.add(imageButton, 2, 3);
        formPane.add(descriptionLabel, 0, 4);
        formPane.add(descriptionEditor, 1, 4, 2, 1);
        formPane.add(categoryIdLabel, 0, 5);
        formPane.add(categoryIdComboBox, 1, 5);
        formPane.add(submitButton, 1, 6);

        return formPane; // Retorna o painel de formulário configurado
    }

    /**
     * Preenche os campos de entrada com os dados do produto selecionado.
     * 
     * @param product O produto a ser editado.
     */
    private void populateFields(ProductModel product) {
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        discountField.setText(String.valueOf(product.getDiscount()));
        imagePathField.setText(product.getImagePath());
        descriptionEditor.setHtmlText(product.getDescription());
        categoryIdComboBox.setValue(product.getCategoryId());
    }

    /**
     * Salva o produto com os dados fornecidos nos campos de entrada.
     */
    private void saveProduct() {
        String name = nameField.getText();
        String priceText = priceField.getText();
        String discountText = discountField.getText();
        String imagePath = imagePathField.getText();
        String description = descriptionEditor.getHtmlText();
        Integer categoryId = categoryIdComboBox.getValue();

        // Valida as entradas
        String validationMessage = validateInputs(name, priceText, discountText, description, categoryId);
        if (validationMessage != null) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", validationMessage);
            return;
        }

        if (selectedProduct != null) {
            // Atualiza o produto existente
            selectedProduct.setName(name);
            selectedProduct.setPrice(Float.parseFloat(priceText));
            selectedProduct.setDiscount(Float.parseFloat(discountText));
            selectedProduct.setImagePath(imagePath);
            selectedProduct.setDescription(description);
            selectedProduct.setCategoryId(categoryId);

            try {
                control.updateProduct(selectedProduct); // Atualiza o produto
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto atualizado!");
            } catch (Exception e) {
                System.out.println(e);
                showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao atualizar produto.");
            }
        } else {
            // Cria a instância do ProductModel
            ProductModel product = new ProductModel();
            product.setName(name);
            product.setPrice(Float.parseFloat(priceText));
            product.setDiscount(Float.parseFloat(discountText));
            product.setImagePath(imagePath);
            product.setDescription(description);
            product.setCategoryId(categoryId);
            product.setSellerId(1); // ID do vendedor fixo para exemplo

            try {
                control.addProduct(product); // Adiciona o produto
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto adicionado!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao adicionar produto.");
            }
        }
        clearFields(); // Limpa os campos após a adição ou atualização
        selectedProduct = null; // Limpa a seleção do produto
    }

    /**
     * Valida as entradas do usuário.
     * 
     * @return Mensagem de erro se houver, ou null se as entradas forem válidas.
     */
    private String validateInputs(String name, String priceText, String discountText, String description, Integer categoryId) {
        if (name.isEmpty() || name.length() > 100) {
            return "O nome deve estar entre 1 e 100 caracteres.";
        }
        if (priceText.isEmpty()) {
            return "O preço não pode ser vazio.";
        }
        try {
            float price = Float.parseFloat(priceText);
            if (price < 0) {
                return "O preço deve ser positivo.";
            }
        } catch (NumberFormatException e) {
            return "O preço deve ser um número válido.";
        }
        if (discountText.isEmpty()) {
            return "Desconto inválido.";
        }
        try {
            float discount = Float.parseFloat(discountText);
            if (discount < 0 || discount > 100) {
                return "O desconto deve estar entre 0 e 100.";
            }
        } catch (NumberFormatException e) {
            return "O desconto deve ser um número válido.";
        }
        if (description.length() > 2000) {
            return "A descrição não pode passar de 2000 caracteres, ela possui " + description.length() + " agora.";
        }
        if (categoryId == null) {
            return "Selecione a categoria do produto.";
        }
        return null; // Sem erros de validação
    }

    /**
     * Gera as colunas da tabela de produtos.
     */
    private void generateColumns() {
        TableColumn<ProductModel, Integer> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ProductModel, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductModel, Float> col3 = new TableColumn<>("Preço");
        col3.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductModel, Float> col4 = new TableColumn<>("Desconto");
        col4.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<ProductModel, String> col5 = new TableColumn<>("Ações");
        col5.setCellFactory(col -> new TableCell<ProductModel, String>() {
            private final Button deleteButton = new Button("Deletar");

            {
                deleteButton.setOnAction(e -> {
                    ProductModel product = getTableView().getItems().get(getIndex());
                    try {
                        control.deleteProduct(product); // Deleta o produto
                        productTableView.getItems().remove(product); // Remove da tabela
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao deletar produto.");
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton); // Exibe o botão de deletar
                }
            }
        });

        productTableView.getColumns().addAll(col1, col2, col3, col4, col5); // Adiciona as colunas à tabela
    }

    /**
     * Exibe um alerta com a mensagem fornecida.
     * 
     * @param alertType Tipo de alerta (informação, erro, etc.)
     * @param title Título do alerta
     * @param message Mensagem a ser exibida
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); // Exibe o alerta e espera o usuário interagir
    }

    /**
     * Limpa os campos de entrada após a adição ou atualização de um produto.
     */
    private void clearFields() {
        nameField.clear();
        priceField.clear();
        discountField.clear();
        imagePathField.clear();
        descriptionEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>"); // Reseta o editor HTML
        categoryIdComboBox.getSelectionModel().clearSelection(); // Limpa a seleção da categoria
    }
}