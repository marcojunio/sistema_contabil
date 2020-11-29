/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.dao;

import br.projeto.main.java.model.Empresa;
import br.projeto.main.java.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 29/11/2020
 */
public class EmpresaDAO implements GenericDAO<Empresa, Integer> {

    @Override
    public void inserir(Empresa obj) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("INSERT INTO empresa "
                + "(id,nome,endereco,cnpj,email,responsavel,empresa_ativa) VALUES "
                + "(?,?,?,?,?,?,?)");

        pst.setInt(1, obj.getId());
        pst.setString(2, obj.getNome());
        pst.setString(3, obj.getEndereco());
        pst.setString(4, obj.getCnpj());
        pst.setString(5, obj.getEmail());
        pst.setObject(6, obj.getResponsavel());
        pst.setBoolean(7, obj.getEmpresaAtiva());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Empresa obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("UPDATE empresa "
                + "SET nome = ?, "
                + "endereco = ?, "
                + "cnpj = ?, "
                + "email = ?, "
                + "responsavel = ?, "
                + "empresa_ativa = ? "
                + "WHERE id = ?;");

        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getEndereco());
        pst.setString(3, obj.getCnpj());
        pst.setString(4, obj.getEmail());
        pst.setString(5, obj.getResponsavel());
        pst.setBoolean(6, obj.getEmpresaAtiva());
        pst.setInt(7, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public void apagar(Empresa obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("DELETE FROM empresa "
                + "WHERE id = ?;");

        pst.setInt(1, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public Empresa buscarPelaChave(Integer key) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM empresa "
                + "WHERE id = ?;");

        pst.setInt(1, key);

        Empresa empresa = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            empresa = new Empresa(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("cnpj"), rs.getString("email"), rs.getString("responsavel"), rs.getBoolean("empresa_ativa"));
        }

        return empresa;
    }

    @Override
    public ArrayList<Empresa> buscarTodos() throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM empresa;");

        ArrayList<Empresa> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Empresa empresa = new Empresa(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("cnpj"), rs.getString("email"), rs.getString("responsavel"), rs.getBoolean("empresa_ativa"));

            lista.add(empresa);
        }

        return lista;
    }

    public ArrayList<Empresa> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM empresa "
                + "WHERE nome like ?;");

        ArrayList<Empresa> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Empresa empresa = new Empresa(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("cnpj"), rs.getString("email"), rs.getString("responsavel"), rs.getBoolean("empresa_ativa"));

            lista.add(empresa);
        }

        return lista;
    }

    public int count(Integer id) throws ClassNotFoundException, SQLException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "select count(emp.id) from empresa e inner join empregado emp on (emp.id_empresa = e.id) where e.id = ? ;";

        PreparedStatement pst = c.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        rs.next();

        return rs.getInt(1);

    }

    public double salarioFuncionarios(Integer id) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnection();

        String sql = "select sum(emp.salario) from empresa e inner join empregado emp on emp.id_empresa = e.id where e.id = ? ;";

        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        rs.next();

        return rs.getInt(1);

    }

}
