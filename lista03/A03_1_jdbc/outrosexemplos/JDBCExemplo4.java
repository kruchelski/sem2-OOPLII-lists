/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package A03_1_jdbc.outrosexemplos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rafael
 */
public class JDBCExemplo4 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conexao = new ConnectionFactoryComProperties().getConnection();
        java.sql.Statement stmt = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE2");
        while(rs.next()){
            System.out.println("------");
            System.out.println(rs.getString("nome"));
            System.out.println(rs.getString("sobrenome"));
        }
        System.out.println("------");
        System.out.println("Fim Lista!");
        //Volta ao primeiro da lista
        if(rs.first()){
            //Atualiza o primeiro da lista
            rs.updateString("nome", "Rafael");
            rs.updateRow();
            //Imprime o primeiro da lista
            System.out.println("Nome do Primeiro da lista:"+rs.getString(1)+" " +rs.getString(2));
        }
        conexao.close();
    }
}
