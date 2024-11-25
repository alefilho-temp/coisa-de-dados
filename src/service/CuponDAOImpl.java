package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import models.Cupon;



public class CuponDAOImpl implements CuponDAO{

	@Override
	public void inserir(Cupon c) throws Exception {
		try {
			String SQL = """
					INSERT INTO Cupon (numero_cupon, porcentagem_Desconto, ClienteCPF, ClienteCadastroid)
					VALUES (?, ?, ?, ?)
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getNumero_cupon());
			stm.setInt(2, c.getPorcentagem_Desconto());
			stm.setInt(3, c.getClienteCPF());
			stm.setInt(4, c.getClienteCadastroid());
			
			int i = stm.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public void atualizar(Cupon c) throws Exception {
		try {
			String SQL = """
					UPDATE Cupon SET porcentagem_Desconto=?, ClienteCPF=?, ClienteCadastroid=?
					WHERE numero_cupon=?
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			
			stm.setInt(1, c.getPorcentagem_Desconto());
			stm.setInt(2, c.getClienteCPF());
			stm.setInt(3, c.getClienteCadastroid());
			stm.setInt(4, c.getNumero_cupon());
			
			int i = stm.executeUpdate();
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public void remover(Cupon c) throws Exception {
		try {
			String SQL = """
					DELETE FROM Cupon WHERE numero_cupon=?
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getNumero_cupon());
			int i = stm.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public List<Cupon> pesquisarPorNome(int porc) throws Exception {
		List<Cupon> lista = new ArrayList<Cupon>();
		try {
			String SQL = """
					SELECT * FROM Cupon WHERE porcentagem_Desconto = ?
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, porc);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Cupon c = new Cupon();
				
				c.setNumero_cupon(rs.getInt("numero_cupon"));
				c.setPorcentagem_Desconto(rs.getInt("porcentagem_Desconto"));
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
	public List<Cupon> pesquisarTodos() throws Exception {
		List<Cupon> lista = new ArrayList<Cupon>();
		
		try {
			String SQL = """
					SELECT * FROM Cupon
					""";
			
			Connection con;
			con = DBConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Cupon c = new Cupon();
				
				c.setNumero_cupon(rs.getInt("numero_cupon"));
				c.setPorcentagem_Desconto(rs.getInt("porcentagem_Desconto"));
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
}