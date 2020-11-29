/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.dao;

import br.projeto.main.java.model.Empregado;
import br.projeto.main.java.model.Empresa;
import br.projeto.main.java.model.enumeration.SexoENUM;
import br.projeto.main.java.model.enumeration.situacaoAtualEmpregadoENUM;
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
public class EmpregadoDAO implements GenericDAO<Empregado, Integer> {

    @Override
    public void inserir(Empregado obj) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("INSERT INTO empregado "
                + "(id_empresa,cpf,nome,idade,data_admissao,sexo,nis,salario,vale_alimentacao,plano_saude,vale_transporte,situacao,endereco,data_nascimento,falecido) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        pst.setInt(1, obj.getEmpresa().getId());
        pst.setString(2, obj.getCpf());
        pst.setString(3, obj.getNome());
        pst.setInt(4, obj.getIdade());
        pst.setObject(5, obj.getDataDeAdmissao());
        pst.setString(6, obj.getSexo().toString());
        pst.setString(7, obj.getNis());
        pst.setDouble(8, obj.getSalarioBase());
        pst.setBoolean(9, obj.getPossuiValeAlimentacao());
        pst.setBoolean(10, obj.getPossuiPlanoDeSaude());
        pst.setBoolean(11, obj.getPossuiValeTransporte());
        pst.setString(12, obj.getSituacao().toString());
        pst.setString(13, obj.getEndereco());
        pst.setObject(14, obj.getDataNascimento());
        pst.setBoolean(15, obj.isFalecido());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Empregado obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("UPDATE empregado "
                + "SET cpf = ?, "
                + "nome = ?, "
                + "idade = ?, "
                + "sexo = ?, "
                + "nis = ?, "
                + "salario = ?, "
                + "vale_alimentacao = ?, "
                + "plano_saude = ?, "
                + "vale_transporte = ?, "
                + "situacao = ?, "
                + "endereco = ?, "
                + "falecido = ? "
                + "WHERE id = ?;");

        pst.setString(1, obj.getCpf());
        pst.setString(2, obj.getNome());
        pst.setInt(3, obj.getIdade());
        pst.setString(4, obj.getSexo().toString());
        pst.setString(5, obj.getNis());
        pst.setDouble(6, obj.getSalarioBase());
        pst.setBoolean(7, obj.getPossuiValeAlimentacao());
        pst.setBoolean(8, obj.getPossuiPlanoDeSaude());
        pst.setBoolean(9, obj.getPossuiValeTransporte());
        pst.setString(10, obj.getSituacao().toString());
        pst.setString(11, obj.getEndereco());
        pst.setBoolean(12, obj.isFalecido());
        pst.setInt(13, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public void apagar(Empregado obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("DELETE FROM empregado "
                + "WHERE id = ?;");

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Empregado buscarPelaChave(Integer key) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM empregado "
                + "WHERE id = ?;");

        pst.setInt(1, key);

        Empregado emp = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            emp = new Empregado();
            Empresa e = new Empresa();

            e.setId(rs.getInt("id_empresa"));

            emp.setId(rs.getInt("id"));
            emp.setNome(rs.getString("nome"));
            emp.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("sexo")));
            emp.setCpf(rs.getString("cpf"));
            emp.setNis(rs.getString("nis"));
            emp.setDataDeAdmissao(rs.getDate("data_admissao"));
            emp.setEndereco(rs.getString("endereco"));
            emp.setIdade(rs.getInt("idade"));
            emp.setSalarioBase(rs.getDouble("salario"));
            emp.setPossuiPlanoDeSaude(rs.getBoolean("plano_saude"));
            emp.setPossuiValeAlimentacao(rs.getBoolean("vale_alimentacao"));
            emp.setPossuiValeTransporte(rs.getBoolean("vale_transporte"));
            emp.setDataNascimento(rs.getDate("data_nascimento"));
            emp.setFalecido(rs.getBoolean("falecido"));
            emp.setSituacao(Enum.valueOf(situacaoAtualEmpregadoENUM.class, rs.getString("situacao")));
            emp.setEmpresa(e);
        }

        return emp;
    }

    @Override
    public ArrayList<Empregado> buscarTodos() throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("select * from empregado e "
                + "inner join empresa em on (em.id = e.id_empresa);");

        ArrayList<Empregado> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Empregado e = new Empregado();

            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("Sexo")));
            e.setCpf(rs.getString("cpf"));
            e.setNis(rs.getString("nis"));
            e.setDataDeAdmissao(rs.getTimestamp("data_admissao"));
            e.setEndereco(rs.getString("endereco"));
            e.setIdade(rs.getInt("idade"));
            e.setSalarioBase(rs.getDouble("salario"));
            e.setPossuiPlanoDeSaude(rs.getBoolean("plano_saude"));
            e.setPossuiValeAlimentacao(rs.getBoolean("vale_alimentacao"));
            e.setPossuiValeTransporte(rs.getBoolean("vale_transporte"));
            e.setDataNascimento(rs.getDate("data_nascimento"));
            e.setFalecido(rs.getBoolean("falecido"));
            e.setSituacao(Enum.valueOf(situacaoAtualEmpregadoENUM.class, rs.getString("situacao")));

            Empresa em = new Empresa();
            em.setCnpj(rs.getString("cnpj"));
            em.setEmail(rs.getString("email"));
            em.setEmpresaAtiva(rs.getBoolean("empresa_ativa"));
            em.setEndereco(rs.getString("endereco"));
            em.setNome(rs.getString("em.nome"));
            em.setResponsavel(rs.getString("responsavel"));

            em.setId(rs.getInt("id_empresa"));

            e.setEmpresa(em);

            lista.add(e);
        }

        return lista;
    }

    public ArrayList<Empregado> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("select * from empregado e "
                + "inner join empresa em on (em.id = e.id_empresa) "
                + "WHERE e.nome like ?;");

        ArrayList<Empregado> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Empregado e = new Empregado();

            Empresa em = new Empresa();
            em.setCnpj(rs.getString("cnpj"));
            em.setEmail(rs.getString("email"));
            em.setEmpresaAtiva(rs.getBoolean("empresa_ativa"));
            em.setEndereco(rs.getString("endereco"));
            em.setNome(rs.getString("em.nome"));
            em.setResponsavel(rs.getString("responsavel"));
            em.setId(rs.getInt("id_empresa"));

            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("Sexo")));
            e.setCpf(rs.getString("cpf"));
            e.setNis(rs.getString("nis"));
            e.setDataDeAdmissao(rs.getDate("data_admissao"));
            e.setEndereco(rs.getString("endereco"));
            e.setIdade(rs.getInt("idade"));
            e.setSalarioBase(rs.getDouble("salario"));
            e.setPossuiPlanoDeSaude(rs.getBoolean("plano_saude"));
            e.setPossuiValeAlimentacao(rs.getBoolean("vale_alimentacao"));
            e.setPossuiValeTransporte(rs.getBoolean("vale_transporte"));
            e.setDataNascimento(rs.getDate("data_nascimento"));
            e.setFalecido(rs.getBoolean("falecido"));
            e.setSituacao(Enum.valueOf(situacaoAtualEmpregadoENUM.class, rs.getString("situacao")));
            e.setEmpresa(em);

            lista.add(e);
        }

        return lista;
    }

}
