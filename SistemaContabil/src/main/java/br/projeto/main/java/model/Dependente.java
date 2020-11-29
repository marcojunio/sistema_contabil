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
 * @since 29/10/2020
 */
public class Dependente extends Pessoa{
    
    private Integer id;
    private Empregado responsavel;

    public Dependente() {
    }

    public Dependente(Integer id, Empregado responsavel, String nome, String cpf, Integer idade, Date dataNascimento, SexoENUM sexo, boolean falecido, String endereco) {
        super(nome, cpf, idade, dataNascimento, sexo, falecido, endereco);
        this.id = id;
        this.responsavel = responsavel;
    }
    
    public Integer getId() {
        return id;
    }

    public Empregado getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Empregado responsavel) {
        this.responsavel = responsavel;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
