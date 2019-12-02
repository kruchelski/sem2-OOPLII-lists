/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package A03_1_jdbc.outrosexemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rafael
 */
public class JDBCExemploComConnectionFactory {

    public static void main(String[] args) throws SQLException {
        Connection conexao = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        conexao.close();
    }
}
