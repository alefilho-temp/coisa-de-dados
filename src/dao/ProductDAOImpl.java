package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import common.Utils;
import models.ProductModel;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public void addProduct(ProductModel product) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Produto (nome, preco_Unitario, Imagem, descricao, VendedorCadastro_id, Categoriaid, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, product.getName());
            pst.setBigDecimal(2, new java.math.BigDecimal(product.getPrice())); // Segundo placeholder (preco)
            pst.setString(3, Utils.saveImage(product.getImagePath()));
            pst.setString(4, product.getDescription());
            pst.setInt(5, product.getCategoryId());
            pst.setInt(6, product.getSellerId());
            pst.setInt(7, 10);
            pst.executeUpdate();
        }
    }

    @Override
    public List<ProductModel> getAllProducts() throws Exception {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("Id_Produto"));
                product.setName(rs.getString("nome"));
                product.setPrice(rs.getFloat("preco_Unitario"));
                product.setDiscount(0.1f);
                // product.setDiscount(rs.getFloat("discount"));
                product.setImagePath(rs.getString("Imagem"));
                product.setDescription(rs.getString("descricao"));
                product.setCategoryId(rs.getInt("Categoriaid"));
                product.setSellerId(rs.getInt("VendedorCadastro_id"));
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public void updateProduct(ProductModel product) throws Exception {
        String sql = "UPDATE Produto SET nome=?, preco_Unitario=?, Imagem=?, descricao=?, Categoriaid=?, VendedorCadastro_id=? WHERE Id_Produto=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, product.getName());
            // pst.setFloat(2, product.getPrice());
            pst.setBigDecimal(2, new java.math.BigDecimal( product.getPrice())); // Segundo placeholder (preco)
            // pst.setFloat(3, product.getDiscount());
            pst.setString(3, product.getImagePath());
            try {
                Utils.saveImage(product.getImagePath());
            } catch (Exception e) {
                // TODO: handle exception
            }
            pst.setString(4, product.getDescription());
            pst.setInt(5, product.getCategoryId());
            pst.setInt(6, product.getSellerId());
            pst.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(ProductModel product) throws Exception {
        String sql = "DELETE FROM Produto WHERE Id_Produto=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, product.getId());
            pst.executeUpdate();
        }
    }
}
