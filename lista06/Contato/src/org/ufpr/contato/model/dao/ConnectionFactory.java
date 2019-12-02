package org.ufpr.contato.model.dao;

//import posjava.semana07_jdbc.exemplos.livroautor.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/exercicio?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "root");//"rrw@and14"
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
