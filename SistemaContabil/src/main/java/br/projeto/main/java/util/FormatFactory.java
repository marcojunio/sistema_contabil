/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.main.java.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Marcos André
 * @since 29/11/2020
 */
public class FormatFactory {
    
    
    public static String formatDate(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        return sdf.format(data);
    }
    
    public static Date convertToDate(String data){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            return sdf.parse(data);
        } catch (ParseException ex) {
            return null;
        }
    }
          
    
    public static String formatDecimal(double valor ){
        DecimalFormat df = new DecimalFormat("#0.00");
        
        return df.format(valor);
    }
}
