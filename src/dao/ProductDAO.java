package dao;

import java.util.List;

import models.ProductModel;

public interface ProductDAO {
    void addProduct(ProductModel product) throws Exception;
    List<ProductModel> getAllProducts() throws Exception;
    void updateProduct(ProductModel product) throws Exception;
    void deleteProduct(ProductModel product) throws Exception;
}
