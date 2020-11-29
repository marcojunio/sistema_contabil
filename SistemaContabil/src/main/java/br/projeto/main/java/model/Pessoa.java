/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.model;

import br.projeto.main.java.model.enumeration.SexoENUM;
import java.util.Date;

/**
 * @author Marcos André
 * @since 29/10/
 */

public abstract class Pessoa {
    
    private String nome;
    private String cpf;
    private Integer idade;
    private Date dataNascimento;
    private SexoENUM sexo;
    private boolean falecido;
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, Integer idade, Date dataNascimento, SexoENUM sexo, boolean falecido,String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.falecido = falecido;
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
   
    public boolean isFalecido() {
        return this.falecido;
    }

    public void setFalecido(boolean falecido) {
        this.falecido = falecido;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
       this.cpf = cpf;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoENUM getSexo() {
        return sexo;
    }

    public void setSexo(SexoENUM sexo) {
        this.sexo = sexo;
    }
    
}
