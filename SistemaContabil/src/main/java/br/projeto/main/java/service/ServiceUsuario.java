/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.service;

import br.projeto.main.java.dao.UsuarioDAO;
import br.projeto.main.java.model.Usuario;
import java.sql.SQLException;

/**
 * @author Marcos André
 * @since 29/11/2020
 */
public class ServiceUsuario {
    
     public static Usuario verificarLogin(String login, String senha) throws ClassNotFoundException, SQLException{
        
        Usuario user = new UsuarioDAO().buscarPorLoginESenha(login, senha);

        if(user != null) {
            return user;
        }else{
            return null;
        }
        
    }
}
