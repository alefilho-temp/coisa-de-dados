package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import common.DBConnection;

public class ClienteDAOImpl implements ClienteDao {

	public void gravar(Cliente c) throws ClienteException {
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String SQL = """
					Insert into Cliente (CPF, Nome_Usuario, Nome, Genero, Data_Nascimento, CadastroId, CarrinhoCodigo)
					Values(?, ?, ?, ?, ?, ?, ?)
						""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getCpf());
			stm.setString(2, c.getUsuario());
			stm.setString(3, c.getNome());
			stm.setString(4, c.getGenero());
			java.sql.Date dt = java.sql.Date.valueOf(c.getNascimento());
			stm.setDate(5, dt);
			stm.setInt(6, c.getCadastroId());
			stm.setInt(7, c.getCarrinhoid());
			
			int i = stm.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClienteException(e);
		}
	}

	@Override
	public List<Cliente> pesquisar(String nome) throws ClienteException {
		List<Cliente> lista = new ArrayList<>();
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String sql = """
					SELECT * FROM Cliente WHERE Nome_Usuario LIKE ?
					""";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nome + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCpf(rs.getInt("CPF"));
				c.setUsuario(rs.getString("Nome_Usuario"));
				c.setUsuario(rs.getString("Nome"));
				c.setGenero(rs.getString("Genero"));
				c.setNascimento(rs.getDate("Data_Nascimento").toLocalDate());
				c.setCadastroId(rs.getInt("CadastroId"));
				c.setCarrinhoid(rs.getInt("CarrinhoCodigo"));
				lista.add(c);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClienteException(e);
		}
		return lista;
	}

	@Override
	public void atualizar(Cliente c) throws ClienteException {
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String SQL = """
					Update  Cliente SET Nome_Usuario=?, Nome=?, Genero=?, Data_Nascimento=?, CarrinhoCodigo=?
					Where CadastroId=?
						""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, c.getUsuario());
			stm.setString(2, c.getNome());
			stm.setString(3, c.getGenero());
			java.sql.Date dt = java.sql.Date.valueOf(c.getNascimento());
			stm.setDate(4, dt);
			stm.setInt(5, c.getCarrinhoid());
			stm.setInt(6, c.getCadastroId());
			int i = stm.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClienteException(e);
		}
	}

	@Override
	public void remover(Cliente c) throws ClienteException {
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String SQL = """
					DELETE FROM Cliente WHERE CPF = ?
					""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, c.getCpf());
			int i = stm.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClienteException(e);
		}
	}

	@Override
	public List<Cliente> pesquisarTodos() throws ClienteException {
		List<Cliente> lista = new ArrayList<>();
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String sql = """
					SELECT * FROM Cliente 
					""";
			PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCpf(rs.getInt("CPF"));
				c.setUsuario(rs.getString("Nome_Usuario"));
				c.setNome(rs.getString("Nome"));
				c.setGenero(rs.getString("Genero"));
				c.setNascimento(rs.getDate("Data_Nascimento").toLocalDate());
				c.setCadastroId(rs.getInt("CadastroId"));
				c.setCarrinhoid(rs.getInt("CarrinhoCodigo"));
				lista.add(c);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClienteException(e);
		}
		return lista;
	}
}