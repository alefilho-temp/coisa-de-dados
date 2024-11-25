package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CategoryModel;
import common.DBConnection;
import models.TelefoneModel;

public class TelefoneDAOImpl implements TelefoneDAO {

		@Override
		public void excluir(TelefoneModel t) throws Exception {
			 try { 
		            String SQL = """
		                    DELETE FROM Telefone 
		                    WHERE Telefone = ?
		                    """;
		            Connection con = DBConnection.getConnection();
		            PreparedStatement stm = con.prepareStatement(SQL);
		            stm.setInt(1, t.getTelefone());
		            stm.executeUpdate();
		            con.close();
		        } catch (SQLException err) { 
		            throw new Exception(err);
		        }   
		}

		@Override
		public void inserir(TelefoneModel c) throws Exception {
			try { 
	            String SQL = """
	                    INSERT INTO Telefone (
	                        Telefone, CadastroId) VALUES 
	                        (?, ?)
	                    """;
	            Connection con = DBConnection.getConnection();
	            PreparedStatement stm = con.prepareStatement(SQL);
	            stm.setInt(1, c.getTelefone());
	            stm.setInt(2, c.getCadastroid());
	            stm.executeUpdate();
	            con.close();
	        } catch (SQLException err) { 
	            throw new Exception(err);
	        }
		}
		
		@Override
		public void atualizar(TelefoneModel c) throws Exception {
			try { 
	            String SQL = """
	                    UPDATE Telefone SET Telefone = ?, CadastroId = ?
	                    WHERE Telefone = ?
	                    """;
	            Connection con = DBConnection.getConnection();
	            PreparedStatement stm = con.prepareStatement(SQL);
	            stm.setInt(1, c.getTelefone());
	            stm.setInt(2, c.getCadastroid());
	            stm.setInt(3, c.getTelefone());
	            stm.executeUpdate();
	            con.close();
	        } catch (SQLException err) { 
	            throw new Exception(err);
	        }
		}

		@Override
		public List<TelefoneModel> pesquisarTodos() throws Exception {
			List<TelefoneModel> lista = new ArrayList<>();
	        try {
	            String sql = """
	                    SELECT * FROM Telefone
	                    """;
	            Connection con = DBConnection.getConnection();
	            PreparedStatement stm = con.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) { 
	            	TelefoneModel telefone = new TelefoneModel();
	            	telefone.setTelefone(rs.getInt("Telefone"));
	                telefone.setCadastroid(rs.getInt("CadastroId"));
	                lista.add(telefone);
	            }
	        } catch (SQLException err) { 
	            throw new Exception(err);
	        }
	        return lista;
		}

		@Override
		public List<TelefoneModel> pesquisarNumero(int numero) throws Exception {
		        List<TelefoneModel> lista = new ArrayList<>();
		        try {
		            String sql = """
		                    SELECT * FROM Telefone WHERE nome LIKE ?
		                    """;
		            Connection con = DBConnection.getConnection();
		            PreparedStatement stm = con.prepareStatement(sql);
		            stm.setString(1, "%" + numero + "%");
		            ResultSet rs = stm.executeQuery();
		            while (rs.next()) { 
		            	TelefoneModel telefone = new TelefoneModel();
		                telefone.setTelefone(rs.getInt("Telefone"));
		                telefone.setCadastroid(rs.getInt("CadastroId"));
		                lista.add( telefone );
		            }
		        } catch (SQLException err) { 
		            throw new Exception(err);
		        }
		        return lista;
		    }
		}	