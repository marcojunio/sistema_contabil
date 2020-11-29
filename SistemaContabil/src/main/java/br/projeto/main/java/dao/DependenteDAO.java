/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.dao;

import br.projeto.main.java.model.Dependente;
import br.projeto.main.java.model.Empregado;
import br.projeto.main.java.model.enumeration.SexoENUM;
import br.projeto.main.java.model.enumeration.situacaoAtualEmpregadoENUM;
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
public class DependenteDAO implements GenericDAO<Dependente, Integer> {

    @Override
    public void inserir(Dependente obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("INSERT INTO dependente "
                + "(id_empregado,nome,cpf,idade,data_nascimento,sexo,falecido,endereco) "
                + "VALUES (?,?,?,?,?,?,?,?)");

        pst.setInt(1, obj.getResponsavel().getId());
        pst.setString(2, obj.getNome());
        pst.setString(3, obj.getCpf());
        pst.setInt(4, obj.getIdade());
        pst.setObject(5, obj.getDataNascimento());
        pst.setString(6, obj.getSexo().name());
        pst.setBoolean(7, obj.isFalecido());
        pst.setString(8, obj.getEndereco());

        pst.executeUpdate();

    }

    @Override
    public void alterar(Dependente obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("UPDATE dependente "
                + "SET nome = ?, "
                + "cpf = ?, "
                + "idade = ?, "
                + "data_nascimento = ?, "
                + "sexo = ?, "
                + "falecido = ?, "
                + "endereco = ? "
                + "WHERE id = ?; ");

        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getCpf());
        pst.setInt(3, obj.getIdade());
        pst.setObject(4, obj.getDataNascimento());
        pst.setString(5, obj.getSexo().name());
        pst.setBoolean(6, obj.isFalecido());
        pst.setString(7, obj.getEndereco());
        pst.setInt(8, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Dependente obj) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("DELETE FROM dependente "
                + "WHERE id = ?;");

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Dependente buscarPelaChave(Integer key) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("SELECT * FROM dependente "
                + "WHERE id = ?;");

        pst.setInt(1, key);

        Dependente dep = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            dep = new Dependente();
            Empregado emp = new Empregado();

            emp.setId(rs.getInt("id_empregado"));
            dep.setId(rs.getInt("id"));
            dep.setNome(rs.getString("nome"));
            dep.setCpf(rs.getString("cpf"));
            dep.setIdade(rs.getInt("idade"));
            dep.setDataNascimento(rs.getDate("data_nascimento"));
            dep.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("sexo")));
            dep.setFalecido(rs.getBoolean("falecido"));
            dep.setEndereco(rs.getString("endereco"));
            dep.setResponsavel(emp);

        }

        return dep;
    }

    @Override
    public ArrayList<Dependente> buscarTodos() throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("select * from dependente d inner join empregado em on (d.id_empregado = em.id);");

        ArrayList<Dependente> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Dependente d = new Dependente();
            Empregado em = new Empregado();

            em.setNome(rs.getString("em.nome"));
            em.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("em.sexo")));
            em.setCpf(rs.getString("em.cpf"));
            em.setNis(rs.getString("em.nis"));
            em.setDataDeAdmissao(FormatFactory.convertToDate(rs.getString("em.data_admissao")));
            em.setEndereco(rs.getString("em.endereco"));
            em.setIdade(rs.getInt("em.idade"));
            em.setSalarioBase(rs.getDouble("em.salario"));
            em.setPossuiPlanoDeSaude(rs.getBoolean("em.plano_saude"));
            em.setPossuiValeAlimentacao(rs.getBoolean("em.vale_alimentacao"));
            em.setPossuiValeTransporte(rs.getBoolean("em.vale_transporte"));
            em.setDataNascimento(FormatFactory.convertToDate(rs.getString("em.data_nascimento")));
            em.setFalecido(rs.getBoolean("em.falecido"));
            em.setSituacao(Enum.valueOf(situacaoAtualEmpregadoENUM.class, rs.getString("em.situacao")));

            d.setId(rs.getInt("id"));
            d.setNome(rs.getString("nome"));
            d.setCpf(rs.getString("cpf"));
            d.setIdade(rs.getInt("idade"));
            d.setDataNascimento(FormatFactory.convertToDate("data_nascimento"));
            d.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("sexo")));
            d.setFalecido(rs.getBoolean("falecido"));
            d.setEndereco(rs.getString("endereco"));

            em.setId(rs.getInt("id_empregado"));

            d.setResponsavel(em);

            lista.add(d);
        }

        return lista;
    }

    public ArrayList<Dependente> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement pst = conexao.prepareStatement("select * from dependente d inner join empregado em on (d.id_empregado = em.id) where d.nome like ? ;");

        ArrayList<Dependente> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Dependente d = new Dependente();
            Empregado em = new Empregado();

            em.setNome(rs.getString("em.nome"));
            em.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("em.sexo")));
            em.setCpf(rs.getString("em.cpf"));
            em.setNis(rs.getString("em.nis"));
            em.setDataDeAdmissao(FormatFactory.convertToDate(rs.getString("em.data_admissao")));
            em.setEndereco(rs.getString("em.endereco"));
            em.setIdade(rs.getInt("em.idade"));
            em.setSalarioBase(rs.getDouble("em.salario"));
            em.setPossuiPlanoDeSaude(rs.getBoolean("em.plano_saude"));
            em.setPossuiValeAlimentacao(rs.getBoolean("em.vale_alimentacao"));
            em.setPossuiValeTransporte(rs.getBoolean("em.vale_transporte"));
            em.setDataNascimento(FormatFactory.convertToDate(rs.getString("em.data_nascimento")));
            em.setFalecido(rs.getBoolean("em.falecido"));
            em.setSituacao(Enum.valueOf(situacaoAtualEmpregadoENUM.class, rs.getString("em.situacao")));

            d.setId(rs.getInt("d.id"));
            d.setNome(rs.getString("d.nome"));
            d.setCpf(rs.getString("d.cpf"));
            d.setIdade(rs.getInt("d.idade"));
            d.setDataNascimento(FormatFactory.convertToDate("d.data_nascimento"));
            d.setSexo(Enum.valueOf(SexoENUM.class, rs.getString("d.sexo")));
            d.setFalecido(rs.getBoolean("d.falecido"));
            d.setEndereco(rs.getString("d.endereco"));

            em.setId(rs.getInt("d.id_empregado"));

            d.setResponsavel(em);

            lista.add(d);
        }

        return lista;
    }

}
