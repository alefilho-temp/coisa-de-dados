/*
package components;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import models.ProductModel;

import java.io.File;

public class ProductRegistrationComponent extends GridPane {

    private TextField nameField;
    private TextField priceField;
    private TextField discountField;
    private TextField imagePathField;
    private HTMLEditor descriptionEditor;
    private ComboBox<Integer> categoryIdComboBox;

    public ProductRegistrationComponent() {
        setPadding(new Insets(10));
        setVgap(8);
        setHgap(10);

        // Create UI components
        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label priceLabel = new Label("Price:");
        priceField = new TextField();

        Label discountLabel = new Label("Discount (%):");
        discountField = new TextField();

        Label imagePathLabel = new Label("Image Path:");
        imagePathField = new TextField();
        Button imageButton = new Button("Browse");

        Label descriptionLabel = new Label("Description:");
        descriptionEditor = new HTMLEditor();

        Label categoryIdLabel = new Label("Category ID:");
        categoryIdComboBox = new ComboBox<>();
        // Populate the ComboBox with some example category IDs
        categoryIdComboBox.getItems().addAll(1, 2, 3, 4, 5);

        Button submitButton = new Button("Register Product");

        // Set up the file chooser for image selection
        FileChooser fileChooser = new FileChooser();
        imageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                imagePathField.setText(file.getAbsolutePath());
            }
        });

        // Submit button action
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String priceText = priceField.getText();
            String discountText = discountField.getText();
            String imagePath = imagePathField.getText();
            String description = descriptionEditor.getHtmlText();
            Integer categoryId = categoryIdComboBox.getValue();

            // Validate inputs
            String validationMessage = validateInputs(name, priceText, discountText, description, categoryId);
            if (validationMessage != null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", validationMessage);
            } else {
                // Create the ProductModel instance
                ProductModel product = new ProductModel(
                        -1, // ID is auto-generated
                        name,
                        Float.parseFloat(priceText),
                        Float.parseFloat(discountText),
                        imagePath,
                        description,
                        categoryId,
                        -1 // Seller ID is not set manually
                );
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product registered successfully!");
                // Clear fields after successful registration
                clearFields();
            }
        });

        // Add components to the grid
        add(nameLabel, 0, 0);
        add(nameField, 1, 0);
        add(priceLabel, 0, 1);
        add(priceField, 1, 1);
        add(discountLabel, 0, 2);
        add(discountField, 1, 2);
        add(imagePathLabel, 0, 3);
        add(imagePathField, 1, 3);
        add(imageButton, 2, 3);
        add(descriptionLabel, 0, 4);
        add(descriptionEditor, 1, 4, 2, 1);
        add(categoryIdLabel, 0, 5);
        add(categoryIdComboBox, 1, 5);
        add(submitButton, 1, 6);
    }

    private String validateInputs(String name, String priceText, String discountText, String description, Integer categoryId) {
        if (name.isEmpty() || name.length() > 100) {
            return "Name must be between 1 and 100 characters.";
        }
        if (priceText.isEmpty()) {
            return "Price is required.";
        }
        try {
            float price = Float.parseFloat(priceText);
            if (price < 0) {
                return "Price must be a positive number.";
            }
        } catch (NumberFormatException e) {
            return "Price must be a valid number.";
        }
        if (discountText.isEmpty()) {
            return "Discount is required.";
        }
        try {
            float discount = Float.parseFloat(discountText);
            if (discount < 0 || discount > 100) {
                return "Discount must be between 0 and 100.";
            }
        } catch (NumberFormatException e) {
            return "Discount must be a valid number.";
        }
        if (description.length() > 500) {
            return "Description must be 500 characters or less.";
        }
        if (categoryId == null) {
            return "Category ID must be selected.";
        }
        return null; // No validation errors
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        nameField.clear();
        priceField.clear();
        discountField.clear();
        imagePathField.clear();
        descriptionEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>");
        categoryIdComboBox.getSelectionModel().clearSelection();
    }
}
*/

// https://github.com/antoniorcn/fatec-2024-2s/blob/main/poo-tarde/aula16/

package cruds;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import models.ProductModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductManagementCrud extends BorderPane {

    private TextField nameField;
    private TextField priceField;
    private TextField discountField;
    private TextField imagePathField;
    private HTMLEditor descriptionEditor;
    private ComboBox<Integer> categoryIdComboBox;
    private TableView<ProductModel> productTableView;

    private List<ProductModel> productList = new ArrayList<>();

    public ProductManagementCrud() {
        for (int i = 0; i < 8; i++) {
            productList.add(new ProductModel(
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

        setPadding(new Insets(10));

        // Create UI components
        GridPane formPane = createFormPane();
        productTableView = new TableView<>();
        for (ProductModel productModel : productList) {
            productTableView.getItems().add(productModel);
        }
        generateColumns();
        loadProducts(); // Load initial products if any

        setTop(formPane);
        setCenter(productTableView);
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setVgap(8);
        formPane.setHgap(10);

        Label nameLabel = new Label("Nome:");
        nameField = new TextField();

        Label priceLabel = new Label("Preço:");
        priceField = new TextField();

        Label discountLabel = new Label("Disconto (%):");
        discountField = new TextField();

        Label imagePathLabel = new Label("Image:");
        imagePathField = new TextField();
        Button imageButton = new Button("Escolher");

        Label descriptionLabel = new Label("Descrição:");
        descriptionEditor = new HTMLEditor();

        Label categoryIdLabel = new Label("Categoria:");
        categoryIdComboBox = new ComboBox<>();
        categoryIdComboBox.getItems().addAll(1, 2, 3, 4, 5); // Example category IDs

        Button submitButton = new Button("Salvar");
        submitButton.setOnAction(e -> saveProduct());

        // Set up the file chooser for image selection
        FileChooser fileChooser = new FileChooser();
        imageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                imagePathField.setText(file.getAbsolutePath());
            }
        });

        // Add components to the grid
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

        return formPane;
    }

    private void saveProduct() {
        String name = nameField.getText();
        String priceText = priceField.getText();
        String discountText = discountField.getText();
        String imagePath = imagePathField.getText();
        String description = descriptionEditor.getHtmlText();
        Integer categoryId = categoryIdComboBox.getValue();

        // Validate inputs
        String validationMessage = validateInputs(name, priceText, discountText, description, categoryId);
        if (validationMessage != null) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", validationMessage);
            return;
        }

        // Create the ProductModel instance
        ProductModel product = new ProductModel(
                -1, // ID is auto-generated
                name,
                Float.parseFloat(priceText),
                Float.parseFloat(discountText),
                imagePath,
                description,
                categoryId,
                -1 // Seller ID is not set manually
        );

        productList.add(product);
        productTableView.getItems().add(product);
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto Adicionado!");
        clearFields();
    }

    private String validateInputs(String name, String priceText, String discountText, String description, Integer categoryId) {
        if (name.isEmpty() || name.length() > 100) {
            return "O nome deve ter entre 1 e 100 caracteres";
        }
        if (priceText.isEmpty()) {
            return "O preço não pode ser vazio";
        }
        try {
            float price = Float.parseFloat(priceText);
            if (price < 0) {
                return "O preço deve ser positivo";
            }
        } catch (NumberFormatException e) {
            return "O preço deve ser um numero valido";
        }
        if (discountText.isEmpty()) {
            return "o disconto invalido";
        }
        try {
            float discount = Float.parseFloat(discountText);
            if (discount < 0 || discount > 100) {
                return "O disconto deve ser entre 0 e 100";
            }
        } catch (NumberFormatException e) {
            return "O disconto deve ser numero valido";
        }
        if (description.length() > 500) {
            return "A descrição não pode passar de 500 caracteres";
        }
        if (categoryId == null) {
            return "Selecione a categoria do produto";
        }
        return null; // No validation errors
    }

    private void generateColumns() {
        TableColumn<ProductModel, Integer> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ProductModel, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductModel, Float> col3 = new TableColumn<>("Preço");
        col3.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductModel, Float> col4 = new TableColumn<>("Disconto");
        col4.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<ProductModel, String> col5 = new TableColumn<>("Acções");
        col5.setCellFactory(col -> new TableCell<ProductModel, String>() {
            private final Button deleteButton = new Button("Deletar");

            {
                deleteButton.setOnAction(e -> {
                    ProductModel product = getTableView().getItems().get(getIndex());
                    productList.remove(product);
                    productTableView.getItems().remove(product);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        productTableView.getColumns().addAll(col1, col2, col3, col4, col5);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        nameField.clear();
        priceField.clear();
        discountField.clear();
        imagePathField.clear();
        descriptionEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>");
        categoryIdComboBox.getSelectionModel().clearSelection();
    }

    private void loadProducts() {
        
    }
}
