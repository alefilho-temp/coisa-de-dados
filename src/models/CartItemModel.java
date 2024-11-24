package models;

public class CartItemModel {
    int id = -1;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    ProductModel product;
    public ProductModel getProduct() {
        return product;
    }
    public void setProduct(ProductModel product) {
        this.product = product;
    }

    int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItemModel(int id, ProductModel product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
}
