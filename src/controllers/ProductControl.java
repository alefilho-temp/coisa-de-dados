package controllers;


import dao.ProductDAO;
import dao.ProductDAOImpl;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ProductModel;

public class ProductControl {
    private ObservableList<ProductModel> productList = FXCollections.observableArrayList();
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleFloatProperty price = new SimpleFloatProperty(0);
    private SimpleFloatProperty discount = new SimpleFloatProperty(0);
    private SimpleStringProperty imagePath = new SimpleStringProperty("");
    private SimpleStringProperty description = new SimpleStringProperty("");
    private SimpleIntegerProperty categoryId = new SimpleIntegerProperty(0);

    private ProductDAO productDAO;

    public ProductControl() throws Exception {
        productDAO = new ProductDAOImpl();
        loadAllProducts();
    }

    public void loadAllProducts() throws Exception {
        productList.clear();
        productList.addAll(productDAO.getAllProducts());
    }

    // Add methods for CRUD operations
    public void addProduct(ProductModel product) throws Exception {
        // product.setName(name.get());
        // product.setPrice(price.get());
        // product.setDiscount(discount.get());
        // product.setImagePath(imagePath.get());
        // product.setDescription(description.get());
        // product.setCategoryId(categoryId.get());

        productDAO.addProduct(product);
        loadAllProducts();
    }

    public void deleteProduct(ProductModel product) throws Exception {
        productDAO.deleteProduct(product);
        loadAllProducts(); // Refresh the product list after deletion
    }

    public ObservableList<ProductModel> getProductList() {
        return productList;
    }

    // Getters for properties
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public SimpleFloatProperty priceProperty() {
        return price;
    }
    public SimpleFloatProperty discountProperty() {
        return discount;
    }
    public SimpleStringProperty imagePathProperty() {
        return imagePath;
    }
    public SimpleStringProperty descriptionProperty() {
        return description;
    }
    public SimpleIntegerProperty categoryIdProperty() {
        return categoryId;
    }
}
