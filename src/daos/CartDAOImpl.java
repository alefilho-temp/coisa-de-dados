package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import common.Utils;
import models.CartItemModel;
import models.ProductModel;

public class CartDAOImpl implements CartDAO {

    @Override
    public List<CartItemModel> getProductsCar() throws Exception {
        List<CartItemModel> carrinhos = new ArrayList<>();
        String sql = """
                SELECT po.Id_Produto, po.nome, po.preco_Unitario, po.Desconto, po.Imagem, po.descricao, po.CategoriaId, po.VendedorCadastro_id, pc.Quantidade_Carrinho FROM Produto po
                INNER JOIN Produto_Carrinho pc
                ON po.Id_Produto = pc.ProdutoId_Produto
                INNER JOIN Carrinho ca
                ON pc.CarrinhoCodigo = ca.Codigo
                WHERE ca.Codigo = ?
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, 1);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CartItemModel carrinho = new CartItemModel(0, null, 0);
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("Id_Produto"));
                product.setName(rs.getString("nome"));
                product.setPrice(rs.getFloat("preco_Unitario"));
                product.setDiscount(rs.getFloat("Desconto"));
                product.setImagePath(rs.getString("Imagem"));
                product.setDescription(rs.getString("descricao"));
                product.setCategoryId(rs.getInt("Categoriaid"));
                product.setSellerId(rs.getInt("VendedorCadastro_id"));
                carrinho.setQuantity(rs.getInt("Quantidade_Carrinho"));
                carrinho.setProduct(product);
                carrinho.setId(5);
                carrinhos.add(carrinho);
            }
        }
        return carrinhos;
    }

    @Override
    public void finalizarCompra() throws Exception {
        String SQL = """
                DELETE Produto_Carrinho WHERE CarrinhoCodigo = ?
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(SQL)) {  
            pst.setInt(1,  Utils.getCartId());
            pst.executeQuery();
        } catch (Exception e) {
            System.out.println("ExcluÃ­do");
        }
    }

    @Override
    public void alterarQuantidade(int item, int idProduto) throws Exception {
        String SQL = """
                UPDATE Produto_Carrinho SET Quantidade_Carrinho = ?
                WHERE ProdutoId_Produto = ?
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(SQL)) {  
            pst.setInt(1, item);
            pst.setInt(2, idProduto);
            pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Alterado");
        }        
    }

    @Override
    public void adicionarAoCarrinho(ProductModel p) {
        String SQL = """
                INSERT INTO Produto_Carrinho (ProdutoId_Produto, CarrinhoCodigo, Quantidade_Carrinho) VALUES (?, ?, ?)
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(SQL)) {  
            pst.setInt(1, p.getId());
            pst.setInt(2, 1);
            pst.setInt(3, 1);
            pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }            
    }
    
    public static int pegarId() {
    	int n = 0;
        String SQL = """
                Select * From Cliente cl Inner Join Carrinho ca On ca.Codigo = cl.CarrinhoCodigo where ca.Codigo = cl.CarrinhoCodigo order by cl.CadastroId desc
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(SQL)) {  
        	ResultSet rs = pst.executeQuery();
        	 n = rs.getInt("CarrinhoCodigo");
            pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public static int criarId() {
    	int n = 0;
        String SQL = """
                Insert Into Carrinho (Valor_Total) Values (0)
                Select Codigo From Carrinho Order By Codigo Desc
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(SQL)) {  
        	ResultSet rs = pst.executeQuery();
        	 n = rs.getInt("CarrinhoCodigo");
            pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public void realizarCompra(ProductModel p) throws Exception {
        String SQL = """
                INSERT INTO Compra (Codigo, ProdutoId_Produto, ClienteCPF, ClienteCadastroId, DataCompra, Quantidade_comprada)  
                VALUES 
                (1, ?, 111111111, 1, ?, 1)
                """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setInt(1, p.getId());
            LocalDate dataAtual = LocalDate.now();  
            Date sqlDate = Date.valueOf(dataAtual); 
            pst.setDate(2, sqlDate);
            ResultSet rs = pst.executeQuery();    
        }    
    }
}