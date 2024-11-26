
package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ArrayList;

import models.CategoryModel;
import models.ProductModel;
import models.Vendedor;

public class ProductsQueries {

	public static ArrayList<ProductModel> getRecomendados(int IdVendedor) throws Exception {
		ArrayList<ProductModel> products = new ArrayList<>();
		String sql = "SELECT *\r\n" + "FROM Produto pr\r\n" + "INNER JOIN Vendedor ve\r\n"
				+ "ON pr.VendedorCadastro_id = ve.Cadastro_id\r\n" + "WHERE pr.VendedorCadastro_id = ?\r\n"
				+ "ORDER BY pr.Id_Produto DESC";
		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, IdVendedor);
			ResultSet rs = pst.executeQuery();
			int n = 0;
			while (rs.next() && n < 4) {
				n++;
				ProductModel product = new ProductModel();
				product.setId(rs.getInt("Id_Produto"));
				// product.setQuantity(rs.getInt("quantidade"));
				product.setPrice(rs.getFloat("preco_Unitario"));
				product.setName(rs.getString("nome"));
				product.setDescription(rs.getString("descricao"));
				product.setSellerId(rs.getInt("VendedorCadastro_id"));
				product.setCategoryId(rs.getInt("Categoriaid"));
				product.setImagePath(rs.getString("Imagem"));
				product.setDiscount(rs.getFloat("Desconto"));

				products.add(product);
			}
		}
		return products;
	}

	public static ArrayList<ProductModel> getProdutosTela() throws Exception {
		ArrayList<ProductModel> products = new ArrayList<>();
		String sql = "SELECT * FROM Produto ORDER BY Id_Produto DESC";
		int n = 0;
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next() && n < 8) {
				n++;
				ProductModel product = new ProductModel();
				product.setId(rs.getInt("Id_Produto"));
				// product.setQuantity(rs.getInt("quantidade"));
				product.setPrice(rs.getFloat("preco_Unitario"));
				product.setName(rs.getString("nome"));
				product.setDescription(rs.getString("descricao"));
				product.setSellerId(rs.getInt("VendedorCadastro_id"));
				product.setCategoryId(rs.getInt("Categoriaid"));
				product.setImagePath(rs.getString("Imagem"));
				product.setDiscount(rs.getFloat("Desconto"));
				products.add(product);
			}
		}
		return products;
	}

	public static ArrayList<ProductModel> getProdutosCategoria(int Idcategory) throws Exception {
		ArrayList<ProductModel> products = new ArrayList<>();
		String sql = "SELECT * FROM Produto pr INNER JOIN Categoria ca\r\n"
				+ "	ON ca.Id = pr.CategoriaId\r\n" + "	WHERE pr.CategoriaId = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, Idcategory);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getInt("Id_Produto"));
				// product.setQuantity(rs.getInt("quantidade"));
				product.setPrice(rs.getFloat("preco_Unitario"));
				product.setName(rs.getString("nome"));
				product.setDescription(rs.getString("descricao"));
				product.setSellerId(rs.getInt("VendedorCadastro_id"));
				product.setCategoryId(rs.getInt("Categoriaid"));
				product.setImagePath(rs.getString("Imagem"));
				product.setDiscount(rs.getFloat("Desconto"));
				products.add(product);
			}
		}
		return products;
	}

	public static ArrayList<ProductModel> getProdutosNome(String nomeProduto) throws Exception {
		ArrayList<ProductModel> products = new ArrayList<>();
		String sql = "SELECT * FROM Produto WHERE nome LIKE '%' + '?' + '%'";
		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomeProduto);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getInt("Id_Produto"));
				// product.setQuantity(rs.getInt("quantidade"));
				product.setPrice(rs.getFloat("preco_Unitario"));
				product.setName(rs.getString("nome"));
				product.setDescription(rs.getString("descricao"));
				product.setSellerId(rs.getInt("VendedorCadastro_id"));
				product.setCategoryId(rs.getInt("Categoriaid"));
				product.setImagePath(rs.getString("Imagem"));
				product.setDiscount(rs.getFloat("Desconto"));
				products.add(product);
			}
		}
		return products;
	}

	public static Vendedor getVendedorPorId(int id) throws Exception {
		Vendedor vendedor = null;
		System.out.println("getVendedorPorId(" + id + ")");
		String sql = "Select * from Vendedor Where Cadastro_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			vendedor = new Vendedor();
			vendedor.setCadastroId(rs.getInt("Cadastro_id"));
			vendedor.setInformacaoLoja(rs.getString("Informacao_loja"));
			vendedor.setNomeLoja(rs.getString("Nome_Loja"));
		}

		return vendedor;
	}

	public static ArrayList<ProductModel> getProdutosNomeLoja(String nomeLoja) throws Exception {
		ArrayList<ProductModel> products = new ArrayList<>();
		String sql = "SELECT * FROM Produto pr INNER JOIN Vendedor ve\r\n"
				+ "	ON pr.VendedorCadastro_id = ve.Cadastro_id\r\n" + "	WHERE ve.Nome_Loja LIKE '%' + '?' + '%'";
		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomeLoja);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getInt("Id_Produto"));
				// product.setQuantity(rs.getInt("quantidade"));
				product.setPrice(rs.getFloat("preco_Unitario"));
				product.setName(rs.getString("nome"));
				product.setDescription(rs.getString("descricao"));
				product.setSellerId(rs.getInt("VendedorCadastro_id"));
				product.setCategoryId(rs.getInt("Categoriaid"));
				product.setImagePath(rs.getString("Imagem"));
				product.setDiscount(rs.getFloat("Desconto"));
				products.add(product);
			}
		}
		return products;
	}
	
	public static ArrayList<CategoryModel> getCategorias() throws Exception {
		ArrayList<CategoryModel> categories = new ArrayList<>();
		String sql = "SELECT * FROM Categoria ORDER BY Id DESC";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
					int n = 0;
			while (rs.next() && n < 6) {
				n++;
				CategoryModel category = new CategoryModel();
				category.setId(rs.getInt("Id"));
				category.setName(rs.getString("Nome"));
				category.setColor(rs.getString("Cor"));
				category.setImagePath(rs.getString("Imagem"));
				categories.add(category);
			}
		}
		return categories;
	}

}
