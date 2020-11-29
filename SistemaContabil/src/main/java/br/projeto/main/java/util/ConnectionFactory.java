/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Marcos André
 * @since 29/11/2020
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        String host = "jdbc:mysql://localhost:3306/sistem_contabil?useTimezone=true&serverTimezone=UTC";
        
        //PASSAR OS PARÂMETROS DA CONEXÃO COM O USER E SENHA,CASO USE PORTA DIFERENTE PASSAR ACIMA TAMBÉM.
        
        return DriverManager.getConnection(host,"","");
    }
}
