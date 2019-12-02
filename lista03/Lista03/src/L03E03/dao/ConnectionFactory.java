package L03E03.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String hostname = null;
    private int port;
    private String database = null;
    private String username = null;
    private String password = null;
    
    private Connection conn = null;
    private String url = null;

    public ConnectionFactory(){
        try {
            hostname = "localhost";
            port = 5432;
            database = "learnjavaweb";
            username = "cassianovidal";
            password = "";
            url = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;
            conn = DriverManager.getConnection(url, username, password);
            
        } catch (SQLException ex) {
            System.err.println("Erro ao interagir com banco de dados " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Ocorreu erro geral ao interagir com banco de dados " + ex.getMessage());
        }
    }
    
    public Connection getConn() {
        return this.conn;
    }
    
    public void closeConn() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexão encerrada com sucesso\n");
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao encerrar conexão " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro geral ao encerrar conexão " + ex.getMessage());
        }
    }
    
}
