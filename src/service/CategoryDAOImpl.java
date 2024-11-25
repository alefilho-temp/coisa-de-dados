package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CategoryModel;
import common.DBConnection;
import common.Utils;

public class CategoryDAOImpl implements CategoryDAO{

	@Override
	public void excluir(CategoryModel c) throws Exception {
		 try { 
	            String SQL = """
	                    DELETE FROM Categoria 
	                    WHERE id = ?
	                    """;
	            Connection con = DBConnection.getConnection();
	            PreparedStatement stm = con.prepareStatement(SQL);
	            stm.setInt(1, c.getId());
	            stm.executeUpdate();
	            con.close();
	        } catch (SQLException err) { 
	            throw new Exception(err);
	        }   
		
	}

	@Override
	public void inserir(CategoryModel c) throws Exception {
		try { 
            String SQL = """
                    INSERT INTO Categoria (
                        nome, cor, imagem) VALUES 
                        (?, ?, ?)
                    """;
            Connection con = DBConnection.getConnection();
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, c.getName());
            stm.setString(2, c.getColor());
            stm.setString(3, Utils.saveImage(c.getImagePath()));
            stm.executeUpdate();
            con.close();
        } catch (SQLException err) { 
            throw new Exception(err);
        }
		
	}
	
	@Override
	public void atualizar(CategoryModel c) throws Exception {
		try { 
            String SQL = """
                    UPDATE Categoria SET nome = ?, cor = ?, 
                        imagem = ?
                    WHERE id = ?
                    """;
            Connection con = DBConnection.getConnection();
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, c.getName());
            stm.setString(2, c.getColor());
            stm.setString(3, Utils.saveImage(c.getImagePath()));
            stm.setInt(4, c.getId());
            stm.executeUpdate();
            con.close();
        } catch (SQLException err) { 
            throw new Exception(err);
        }
	}

	@Override
	public List<CategoryModel> pesquisarTodos() throws Exception {
		List<CategoryModel> lista = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM Categoria
                    """;
            Connection con = DBConnection.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { 
                CategoryModel categoria = new CategoryModel(0, sql, sql, sql);
                categoria.setId(rs.getInt("id") );
                categoria.setName(rs.getString("nome") );
                categoria.setColor(rs.getString("cor") );
                categoria.setImagePath(rs.getString("imagem") );;
                lista.add(categoria);
            }
        } catch (SQLException err) { 
            throw new Exception(err);
        }
        return lista;
	}

	@Override
	public List<CategoryModel> pesquisarNome(String nome) throws Exception {
	        List<CategoryModel> lista = new ArrayList<>();
	        try {
	            String sql = """
	                    SELECT * FROM Categoria WHERE nome LIKE ?
	                    """;
	            Connection con = DBConnection.getConnection();
	            PreparedStatement stm = con.prepareStatement(sql);
	            stm.setString(1, "%" + nome + "%");
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) { 
	                CategoryModel categoria = new CategoryModel(0, sql, sql, sql);
	                categoria.setId( rs.getInt("id") );
	                categoria.setName( rs.getString("nome") );
	                categoria.setColor( rs.getString("cor") );
	                categoria.setImagePath( rs.getString("imagem") );
	                lista.add( categoria );
	            }
	        } catch (SQLException err) { 
	            throw new Exception(err);
	        }
	        return lista;
	    }
	}	
