package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Cartao;
import common.DBConnection;

public class CartaoDAOImpl implements CartaoDAO {

	@Override
	public void inserir(Cartao c) throws Exception {
		try {
			
			Connection con;
			con = DBConnection.getConnection();
			String SQL = """
					INSERT INTO Cartao (Numero, Tipo, Nome_Cartao, Data_Vencimento, Codigo_Seguranca, ClienteCPF, ClienteCadastroId)
					VALUES (?, ?, ?, ?, ?, ?, ?)
					""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getNumero());
			stm.setString(2, c.getTipo_Cartao());
			stm.setString(3, c.getNome_Cartao());
			
			java.sql.Date dt = java.sql.Date.valueOf(c.getData_Vencimento());
			stm.setDate(4, dt);
			
			stm.setInt(5, c.getCodigo_Seguranca());
			stm.setInt(6, c.getClienteCPF());
			stm.setInt(7, c.getClienteCadastroid());
			
			stm.executeUpdate();
			con.close();		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public void atualizar(Cartao c) throws Exception {
		try {
			String SQL = """
					UPDATE Cartao SET Tipo=?, Nome_Cartao=?, Data_Vencimento=?, Codigo_Seguranca=?, ClienteCPF=?, ClienteCadastroid=?
					WHERE Numero=?
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, c.getTipo_Cartao());
			stm.setString(2, c.getNome_Cartao());
			java.sql.Date dt = java.sql.Date.valueOf(c.getData_Vencimento());
			stm.setDate(3, dt);
			stm.setInt(4, c.getCodigo_Seguranca());
			stm.setInt(5, c.getClienteCPF());
			stm.setInt(6, c.getClienteCadastroid());
			stm.setInt(7, c.getNumero());
			
			int i = stm.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public void remover(Cartao c) throws Exception {
		try {
			String SQL = """
					DELETE FROM Cartao WHERE Numero=?
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getNumero());
			
			int i = stm.executeUpdate();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public List<Cartao> pesquisarPorNome(String nome) throws Exception {
		List<Cartao> lista = new ArrayList<Cartao>();

		try {
			String SQL = """
					SELECT * FROM Cartao WHERE Nome_Cartao LIKE ?
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, "%" + nome + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cartao c = new Cartao();

				c.setNumero(rs.getInt("Numero"));
				c.setTipo_Cartao(rs.getString("Tipo"));
				c.setNome_Cartao(rs.getString("Nome_Cartao"));
				c.setData_Vencimento(rs.getDate("Data_Vencimento").toLocalDate());
				c.setCodigo_Seguranca(rs.getInt("Codigo_Seguranca"));
				c.setClienteCPF(rs.getInt("ClienteCPF"));
				c.setClienteCadastroid(rs.getInt("ClienteCadastroid"));

				lista.add(c);
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return lista;
	}

	@Override
	public List<Cartao> pesquisarTodos() throws Exception {
		List<Cartao> lista = new ArrayList<Cartao>();
		try {
			String SQL = """
					SELECT * FROM Cartao
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cartao c = new Cartao();

				c.setNumero(rs.getInt("Numero"));
				c.setTipo_Cartao(rs.getString("Tipo"));
				c.setNome_Cartao(rs.getString("Nome_Cartao"));
				c.setData_Vencimento(rs.getDate("Data_Vencimento").toLocalDate());
				c.setCodigo_Seguranca(rs.getInt("Codigo_Seguranca"));
				c.setClienteCPF(rs.getInt("ClienteCPF"));
				c.setClienteCadastroid(rs.getInt("ClienteCadastroid"));

				lista.add(c);
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}

		return lista;
	}
}