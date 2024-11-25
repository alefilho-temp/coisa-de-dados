package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cadastro;
import common.DBConnection;

public class CadastroDAOImpl implements CadastroDAO {

	public void inserir(Cadastro c) {
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String SQL = """
					Insert into Cadastro (Email, Lougradouro, Numero, Bairro, Cep)
					Values(?, ?, ?, ?, ?)
						""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, c.getEmail());
			stm.setString(2, c.getLogradouro());
			stm.setInt(3, c.getNumero());
			stm.setString(4, c.getBairro());
			stm.setInt(5, c.getCep());

			int i = stm.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cadastro> pesquisarPorEmail(String email) {
		List<Cadastro> lista = new ArrayList<>();
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String sql = """
					SELECT * FROM Cadastro WHERE Email LIKE ?
					""";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + email + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cadastro c = new Cadastro();
				c.setId(rs.getInt("id"));
				c.setEmail(rs.getString("Email"));
				c.setLogradouro(rs.getString("Lougradouro"));
				c.setNumero(rs.getInt("Numero"));
				c.setBairro(rs.getString("Bairro"));
				c.setCep(rs.getInt("Cep"));
				lista.add(c);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void atualizar(Cadastro c) {
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String SQL = """
					Update  Cliente SET Email=?, Lougradouro=?, Numero=?, Bairro=?, Cep=?
					Where id=?
						""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, c.getEmail());
			stm.setString(2, c.getLogradouro());
			stm.setInt(3, c.getNumero());
			stm.setString(4, c.getBairro());
			stm.setInt(5, c.getCep());
			stm.setInt(6, c.getId());
			int i = stm.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(Cadastro c) {
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String SQL = """
					DELETE FROM Cadastro WHERE id = ?
					""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getId());
			int i = stm.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cadastro> pesquisarTodos() {
		List<Cadastro> lista = new ArrayList<>();
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String sql = """
					SELECT * FROM Cadastro 
					""";
			PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cadastro c = new Cadastro();
				c.setId(rs.getInt("id"));
				c.setEmail(rs.getString("Email"));
				c.setLogradouro(rs.getString("Lougradouro"));
				c.setNumero(rs.getInt("Numero"));
				c.setBairro(rs.getString("Bairro"));
				c.setCep(rs.getInt("Cep"));
				lista.add(c);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lista;
	}
}