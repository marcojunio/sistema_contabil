/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.model;

import br.projeto.main.java.model.enumeration.SexoENUM;
import br.projeto.main.java.model.enumeration.situacaoAtualEmpregadoENUM;
import java.util.Date;

/**
 * @author Marcos André
 * @Since 29/10/2020
 */
public class Empregado extends Pessoa {

    private Integer id;
    private double salarioBase;
    private String nis;
    private Empresa empresa;
    private Date dataDeAdmissao;
    private Boolean possuiPlanoDeSaude;
    private Boolean possuiValeAlimentacao;
    private Boolean possuiValeTransporte;
    private situacaoAtualEmpregadoENUM situacao;

    public Empregado(Integer id, double salarioBase, String nis, Date dataDeAdmissao, Boolean possuiPlanoDeSaude, Boolean possuiValeAlimentacao, Boolean possuiValeTransporte, situacaoAtualEmpregadoENUM situacao, String nome, String cpf, Integer idade, Date dataNascimento, SexoENUM sexo, boolean falecido, String endereco) {
        super(nome, cpf, idade, dataNascimento, sexo, falecido, endereco);
        this.id = id;
        this.salarioBase = salarioBase;
        this.nis = nis;
        this.dataDeAdmissao = dataDeAdmissao;
        this.possuiPlanoDeSaude = possuiPlanoDeSaude;
        this.possuiValeAlimentacao = possuiValeAlimentacao;
        this.possuiValeTransporte = possuiValeTransporte;
        this.situacao = situacao;
    }

    public Empregado() {
    }
    
    

    public situacaoAtualEmpregadoENUM getSituacao() {
        return situacao;
    }

    public void setSituacao(situacaoAtualEmpregadoENUM situacao) {
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salario) {
        this.salarioBase = salario;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public Boolean getPossuiPlanoDeSaude() {
        return possuiPlanoDeSaude;
    }

    public void setPossuiPlanoDeSaude(Boolean possuiPlanoDeSaude) {
        this.possuiPlanoDeSaude = possuiPlanoDeSaude;
    }

    public Boolean getPossuiValeAlimentacao() {
        return possuiValeAlimentacao;
    }

    public void setPossuiValeAlimentacao(Boolean possuiValeAlimentacao) {
        this.possuiValeAlimentacao = possuiValeAlimentacao;
    }

    public Boolean getPossuiValeTransporte() {
        return possuiValeTransporte;
    }

    public void setPossuiValeTransporte(Boolean possuiValeTransporte) {
        this.possuiValeTransporte = possuiValeTransporte;
    }

}
