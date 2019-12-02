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
public class JDBCExemplo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/exercicio?user=root&password=");
        System.out.println("Conectado!");
        conexao.close();
    }
}
