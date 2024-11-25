package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import models.Vendedor_Pessoal;

public class Vendedor_PessoalDAOImpl implements Vendedor_PessoalDAO {
	
	Connection con;
	public Vendedor_PessoalDAOImpl() {
		try {
			con = DBConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void inserir(Vendedor_Pessoal v) {
		try {
			String sql = "INSERT INTO Vendedor_Pessoal (CPF, VendedorCadastro_id, Nome, Data_Nascimento, Inscricao_Estadual)  VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, v.getCPF());
            ps.setInt(2, v.getVendedorCadastro_Id());
            ps.setString(3, v.getNome());
            java.sql.Date dt = java.sql.Date.valueOf(v.getData_Nascimento());
			ps.setDate(4, dt);
            ps.setString(5, v.getInscricao_Estadual());
            int i = ps.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void atualizar(Vendedor_Pessoal v) {
		try {
			String sql = """
					UPDATE Vendedor_Pessoal SET CPF=?, Nome=?, Data_Nascimento=?, Inscricao_Estadual=?
					WHERE VendedorCadastro_id=?
					""";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, v.getCPF());
            ps.setString(2, v.getNome());
            java.sql.Date dt = java.sql.Date.valueOf(v.getData_Nascimento());
            ps.setDate(3, dt);
            ps.setString(4, v.getInscricao_Estadual());
            
            ps.setInt(5, v.getVendedorCadastro_Id());
            int i = ps.executeUpdate();
            
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void remover(Vendedor_Pessoal v) {
		try {
			String sql = """
					DELETE FROM Vendedor_Pessoal
					WHERE VendedorCadastro_id=?
					""";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getVendedorCadastro_Id());
            int i = ps.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Vendedor_Pessoal> pesquisarPorCPF(int cpf) {
		List<Vendedor_Pessoal> lista = new ArrayList<>();
        try { 
            String sql = """
                    SELECT * FROM Vendedor_Pessoal WHERE CPF=?       
                    """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cpf);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
            	Vendedor_Pessoal v = new Vendedor_Pessoal();
                v.setCPF(rs.getInt("CPF"));
                v.setVendedorCadastro_Id(rs.getInt("VendedorCadastro_id"));
                v.setNome(rs.getString("Nome"));
                v.setData_Nascimento(rs.getDate("Data_Nascimento").toLocalDate());
                v.setInscricao_Estadual(rs.getString("Inscricao_Estadual"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}

	@Override
	public List<Vendedor_Pessoal> pesquisarTodos() {
		List<Vendedor_Pessoal> lista = new ArrayList<>();
        try { 
            String SQL = """
                    SELECT * FROM Vendedor_Pessoal       
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { 
            	Vendedor_Pessoal v = new Vendedor_Pessoal();
                v.setCPF(rs.getInt("CPF"));
                v.setVendedorCadastro_Id(rs.getInt("VendedorCadastro_id"));
                v.setNome(rs.getString("Nome"));
                v.setData_Nascimento(rs.getDate("Data_Nascimento").toLocalDate());
                v.setInscricao_Estadual(rs.getString("Inscricao_Estadual"));
                lista.add(v);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return lista;
	}
}