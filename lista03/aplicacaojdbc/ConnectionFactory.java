/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaojdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class ConnectionFactory {
    
    private static ConnectionFactory factory;
    private ConnectionFactory(){
        
    }
    
    public static ConnectionFactory getConnectionFactory(){
        if(factory==null){
            factory = new ConnectionFactory();
        }
        return factory;
    }
    
    public Connection getConnection(){
        try {
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream("/aplicacaojdbc/bd.properties"));

            String server = prop.getProperty("bd.server");
            String options = prop.getProperty("bd.options");
            String instance = prop.getProperty("bd.instance");
            String user = prop.getProperty("bd.user");
            String password = prop.getProperty("bd.password");
                       
            String url="jdbc:mysql://"+server+"/"+instance+"?"+options;

            return DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco de dados! "+ ex.getMessage());
            throw new RuntimeException("Conexão com o banco falhou.");
        } catch (IOException ex) {
            System.out.println("Erro ao ler arquivo de propriedade! "+ ex.getMessage());
            throw new RuntimeException("Falha ao ler arquivo de propriedade do banco."); 
        }
    }
    
}
