/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class AplicacaoJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection con = ConnectionFactory.getConnectionFactory().getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM CLIENTE where sobrenome=?");
            pst.setString(1, "Silva");
            ResultSet rs = pst.executeQuery();
            System.out.println("NOME;SOBRENOME");
            while(rs.next()){
                System.out.print(rs.getString("nome")+";");
                System.out.println(rs.getString(2));
            }
            System.out.println("Conectou!!");
        } catch (SQLException ex) {
            System.out.println("Ops. Falhou.");
            ex.printStackTrace();
        }
    }
    
}
