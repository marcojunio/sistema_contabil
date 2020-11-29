/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcos André
 * @since 29/11/2020
 * @param <C,K>
 */
public interface GenericDAO<C, K> {
    
    public void inserir(C obj) throws SQLException, ClassNotFoundException;
    public void alterar(C obj) throws SQLException, ClassNotFoundException;
    public void apagar(C obj) throws SQLException, ClassNotFoundException;
    public C buscarPelaChave(K key) throws SQLException, ClassNotFoundException;
    public ArrayList<C> buscarTodos() throws SQLException, ClassNotFoundException;
}
