/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.dao;

import br.projeto.main.java.model.Empresa;
import br.projeto.main.java.model.Lancamento;
import br.projeto.main.java.util.ConnectionFactory;
import br.projeto.main.java.util.FormatFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 29/11/2020
 */
public class LancamentoDAO implements GenericDAO<Lancamento, Integer> {

    @Override
    public void inserir(Lancamento obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("INSERT INTO lancamento "
                + "(id_empresa,descricao,credito,debito,patrimonio_total,data_lancamento) VALUES "
                + "(?,?,?,?,?,?)");

        pst.setInt(1, obj.getEmpresa().getId());
        pst.setString(2, obj.getDescricao());
        pst.setDouble(3, obj.getCredito());
        pst.setDouble(4, obj.getDebito());
        pst.setDouble(5, obj.getPatrimonioTotal());
        pst.setObject(6, obj.getDataLancamento());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Lancamento obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("UPDATE lancamento "
                + "SET descricao = ?, "
                + "credito = ?, "
                + "debito = ?, "
                + "patrimonio_total = ?, "
                + "data_lancamento = ? "
                + "WHERE id = ?;");

        pst.setString(1, obj.getDescricao());
        pst.setDouble(2, obj.getCredito());
        pst.setDouble(3, obj.getDebito());
        pst.setDouble(4, obj.getPatrimonioTotal());
        pst.setObject(5, obj.getDataLancamento());
        pst.setInt(6, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Lancamento obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("DELETE FROM lancamento "
                + "WHERE id = ?;");

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Lancamento buscarPelaChave(Integer key) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM lancamento "
                + "WHERE id = ?;");

        pst.setInt(1, key);

        Lancamento lanc = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            lanc = new Lancamento();
            Empresa e = new Empresa();

            e.setId(rs.getInt("id_empresa"));

            lanc.setId(rs.getInt("id"));
            lanc.setEmpresa(e);
            lanc.setCredito(rs.getDouble("credito"));
            lanc.setDebito(rs.getDouble("debito"));
            lanc.setDescricao(rs.getString("descricao"));
            lanc.setPatrimonioTotal(rs.getDouble("patrimonio_total"));
            lanc.setDataLancamento(rs.getDate("data_lancamento"));
        }

        return lanc;
    }

    @Override
    public ArrayList<Lancamento> buscarTodos() throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("select * from lancamento l "
                + "inner join empresa e on (e.id = l.id_empresa);");

        //execução
        ResultSet rs = pst.executeQuery();

        ArrayList<Lancamento> lista = new ArrayList<>();

        while (rs.next()) {

            Lancamento l = new Lancamento();
            l.setId(rs.getInt("id"));
            l.setDescricao(rs.getString("descricao"));
            l.setDebito(rs.getDouble("debito"));
            l.setCredito(rs.getDouble("credito"));
            l.setPatrimonioTotal(rs.getDouble("patrimonio_total"));
            l.setDataLancamento(FormatFactory.convertToDate("data_lancamento"));

            Empresa e = new Empresa();
            e.setCnpj(rs.getString("cnpj"));
            e.setEmail(rs.getString("email"));
            e.setEmpresaAtiva(rs.getBoolean("empresa_ativa"));
            e.setEndereco(rs.getString("endereco"));
            e.setNome(rs.getString("nome"));
            e.setResponsavel(rs.getString("responsavel"));

            e.setId(rs.getInt("id_empresa"));

            l.setEmpresa(e);

            lista.add(l);

        }

        return lista;
    }

    public ArrayList<Lancamento> buscarPeloNome(String descricao) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("select * from lancamento l "
                + "inner join empresa e on (e.id = l.id_empresa) "
                + "WHERE descricao like ?;");

        ArrayList<Lancamento> lista = new ArrayList<>();
        pst.setString(1, "%" + descricao + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Lancamento l = new Lancamento();
            Empresa e = new Empresa();
            e.setId(rs.getInt("id_empresa"));
            e.setNome(rs.getString("nome"));

            l.setId(rs.getInt("id"));
            l.setDescricao(rs.getString("descricao"));
            l.setDebito(rs.getDouble("debito"));
            l.setCredito(rs.getDouble("credito"));
            l.setPatrimonioTotal(rs.getDouble("patrimonio_total"));
            l.setDataLancamento(FormatFactory.convertToDate("data_lancamento"));
            l.setEmpresa(e);

            lista.add(l);
        }

        return lista;
    }
}
