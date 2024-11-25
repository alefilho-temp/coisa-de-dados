package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import models.Vendedor;

public class VendedorDAOImpl implements VendedorDAO {
	
	Connection con;
	public VendedorDAOImpl() {
		try {
			con = DBConnection.getConnection();
			if (con!= null) System.out.println("Conexão realizada com sucesso!");
			else System.out.println("Falha ao conectar");
		} catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void inserir(Vendedor v) {
		try {
			String sql = "INSERT INTO Vendedor (Cadastro_id, Nome_Loja, Informacao_loja)  VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, v.getCadastroId());
            ps.setString(2, v.getNomeLoja());
            ps.setString(3, v.getInformacaoLoja());
            int i = ps.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void atualizar(Vendedor v) {
		try {
			String sql = """
					UPDATE Vendedor SET Cadastro_id=?, Nome_Loja=?, Informacao_loja=?
					WHERE Cadastro_id=?
					""";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, v.getCadastroId());
            ps.setString(2, v.getNomeLoja());
            ps.setString(3, v.getInformacaoLoja());
            int i = ps.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void remover(Vendedor v) {
		try {
			String sql = """
					DELETE FROM Vendedor
					WHERE Cadastro_id=?
					""";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getCadastroId());
            int i = ps.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Vendedor> pesquisarPorNome(String nome) {
		List<Vendedor> lista = new ArrayList<>();
        try { 
            String sql = """
                    SELECT * FROM Vendedor WHERE Nome_Loja like ?       
                    """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                Vendedor v = new Vendedor();
                v.setCadastroId(rs.getInt("Cadastro_id"));
                v.setNomeLoja(rs.getString("Nome_Loja"));
                v.setInformacaoLoja(rs.getString("Informacao_loja"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}

	@Override
	public List<Vendedor> pesquisarTodos() {
		List<Vendedor> lista = new ArrayList<>();
        try { 
            String SQL = """
                    SELECT * FROM Vendedor       
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { 
                Vendedor v = new Vendedor();
                v.setCadastroId(rs.getInt("Cadastro_id"));
                v.setNomeLoja(rs.getString("Nome_Loja"));
                v.setInformacaoLoja(rs.getString("Informacao_loja"));
                lista.add(v);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return lista;
	}
}