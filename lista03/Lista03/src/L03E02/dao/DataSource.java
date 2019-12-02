package L03E02.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    String hostname;
    int port;
    String database;
    String username;
    String password;
    String url;
    Connection conn = null;
    
    public DataSource() {
        try {
            hostname = "localhost";
            port = 5432;
            database  = "learnjavaweb";
            username = "cassianovidal";
            password = "";
            url = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.err.println("!ERRO! Ocorreu erro na transação com o BD");
        } catch (Exception ex) {
            System.err.println("!ERRO! Ocorreu erro geral");
        }
        
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.err.println("!ERRO! Ocorreu erro ao encerrar conexão " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("!ERRO! Ocorreu um erro geral " + ex.getMessage());
        }
    }
    
    
}
