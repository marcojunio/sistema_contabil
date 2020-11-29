/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.model;

/**
 * @author Marcos André
 * @since 29/10/2020
 */
public class Empresa {

    private Integer id;
    private String nome;
    private String endereco;
    private String cnpj;
    private String email;
    private String responsavel;
    private Boolean empresaAtiva;

    public Empresa() {
    }

    public Empresa(Integer id, String nome, String endereco, String cnpj, String email, String responsavel, Boolean empresaAtiva) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.email = email;
        this.responsavel = responsavel;
        this.empresaAtiva = empresaAtiva;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmpresaAtiva() {
        return empresaAtiva;
    }

    public void setEmpresaAtiva(Boolean empresaAtiva) {
        this.empresaAtiva = empresaAtiva;
    }

}
