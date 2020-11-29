/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.model;

import java.util.Date;

/**
 * @author Marcos André
 * @since 29/11/2020
 */
public class Lancamento {
    
    private Integer id;
    private double credito;
    private double debito;
    private String descricao;
    private double patrimonioTotal;
    private Empresa empresa;
    private Date dataLancamento;

    
    public Lancamento() {
    }
    
    public double getPatrimonioTotal() {
        return patrimonioTotal;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }
       
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    
    
    
    public void setPatrimonioTotal(double patrimonioTotal) {
        this.patrimonioTotal = patrimonioTotal;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }  
}
