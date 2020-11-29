/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.model;


/**
 * @author Marcos André@
 * @since 29/11/2020
 */
public class Usuario {
    
    private Integer id;
    private String userName;
    private String password;
    private String nome;

    public Usuario() {
    }

    public Usuario(Integer id, String userName, String password, String nome) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nome = nome;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
