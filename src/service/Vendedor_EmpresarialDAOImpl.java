package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import models.Vendedor_Empresarial;

public class Vendedor_EmpresarialDAOImpl implements Vendedor_EmpresarialDAO {
    
    Connection con;

    public Vendedor_EmpresarialDAOImpl() {
        try {
            con = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Vendedor_Empresarial v) {
        try {
            String sql = """
                INSERT INTO Vendedor_Empresarial (CNPJ, VendedorCadastro_id, Razao_Social, Informacao_cobranca) 
                VALUES (?, ?, ?, ?)
            """;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, v.getCNPJ());
            ps.setInt(2, v.getVendedorCadastro_Id());
            ps.setString(3, v.getRazao_Social());
            ps.setString(4, v.getInformacao_cobranca());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Vendedor_Empresarial v) {
        try {
            String sql = """
                UPDATE Vendedor_Empresarial 
                SET CNPJ = ?, Razao_Social = ?, Informacao_cobranca = ? 
                WHERE VendedorCadastro_id = ?
            """;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, v.getCNPJ());
            ps.setString(2, v.getRazao_Social());
            ps.setString(3, v.getInformacao_cobranca());
            ps.setInt(4, v.getVendedorCadastro_Id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(Vendedor_Empresarial v) {
        try {
            String sql = """
                DELETE FROM Vendedor_Empresarial 
                WHERE VendedorCadastro_id = ?
            """;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, v.getVendedorCadastro_Id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vendedor_Empresarial> pesquisarPorCNPJ(int cnpj) {
        List<Vendedor_Empresarial> lista = new ArrayList<>();
        try {
            String sql = """
                SELECT * FROM Vendedor_Empresarial 
                WHERE CNPJ = ?
            """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cnpj);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor_Empresarial v = new Vendedor_Empresarial();
                v.setCNPJ(rs.getInt("CNPJ"));
                v.setVendedorCadastro_Id(rs.getInt("VendedorCadastro_id"));
                v.setRazao_Social(rs.getString("Razao_Social"));
                v.setInformacao_cobranca(rs.getString("Informacao_cobranca"));

                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Vendedor_Empresarial> pesquisarTodos() {
        List<Vendedor_Empresarial> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Vendedor_Empresarial";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor_Empresarial v = new Vendedor_Empresarial();
                v.setCNPJ(rs.getInt("CNPJ"));
                v.setVendedorCadastro_Id(rs.getInt("VendedorCadastro_id"));
                v.setRazao_Social(rs.getString("Razao_Social"));
                v.setInformacao_cobranca(rs.getString("Informacao_cobranca"));

                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
