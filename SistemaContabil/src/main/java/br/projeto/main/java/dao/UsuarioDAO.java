/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.dao;

import br.projeto.main.java.model.Usuario;
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
public class UsuarioDAO implements GenericDAO<Usuario, Integer> {

    @Override
    public void inserir(Usuario obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("INSERT INTO usuario "
                + "(user_name,senha,nome)VALUES "
                + "(?,?,?)");

        pst.setString(1, obj.getUserName());
        pst.setString(2, obj.getPassword());
        pst.setString(3, obj.getNome());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Usuario obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("UPDATE usuario "
                + "SET user_name = ?, "
                + "senha = ?, "
                + "nome = ?, "
                + "WHERE id = ?, ");

        pst.setInt(1, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public void apagar(Usuario obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("DELETE FROM usuario "
                + "WHERE user_name = ?;");

        pst.setString(1, obj.getUserName());

        pst.executeUpdate();
    }

    @Override
    public Usuario buscarPelaChave(Integer key) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM usuario "
                + "WHERE id = ?;");

        pst.setInt(1, key);

        Usuario usu = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            usu = new Usuario();

            usu.setId(rs.getInt("id"));
            usu.setNome(rs.getNString("nome"));
            usu.setPassword(rs.getString("senha"));
            usu.setUserName(rs.getString("user_name"));
        }

        return usu;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM usuario;");

        ArrayList<Usuario> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            Usuario usu = new Usuario();

            usu.setId(rs.getInt("id"));
            usu.setNome(rs.getNString("nome"));
            usu.setPassword(rs.getString("senha"));
            usu.setUserName(rs.getString("user_name"));

            lista.add(usu);
        }

        return lista;

    }

    public Usuario buscarPorLoginESenha(String login, String senha) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnection();

        String sql = "select * from usuario WHERE user_name = ? and senha = ?;";

        //preparar a execução
        PreparedStatement pst = c.prepareStatement(sql);

        //troca de valores
        pst.setString(1, login);
        pst.setString(2, senha);

        //execução
        ResultSet rs = pst.executeQuery();

        Usuario p = null;

        if (rs.next()) {
            p = new Usuario(0, rs.getString("user_name"), rs.getString("senha"), rs.getString("nome"));
        }

        return p;
    }
}
