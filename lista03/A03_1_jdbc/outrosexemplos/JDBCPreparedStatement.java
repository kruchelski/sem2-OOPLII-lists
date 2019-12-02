/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package A03_1_jdbc.outrosexemplos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Rafael
 */
public class JDBCPreparedStatement {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conexao = new ConnectionFactoryComProperties().getConnection();
        PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO CLIENTE2 (NUMERO,NOME,SOBRENOME) VALUES(?,?,?)");
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Insira numero, nome e sobrenome (Para encerrar digite \"S\" no nome ou sobrenome:");
            int numero = sc.nextInt();
            String nome = sc.next();
            String sobrenome = sc.next();
            if(nome.equals("S")||sobrenome.equals("S"))
                break;
            pstmt.setInt(1, numero);
            pstmt.setString(2, nome);
            pstmt.setString(3, sobrenome);
            pstmt.executeUpdate();
        }
        pstmt.close();
        System.out.println("Fim da inclusão");
        conexao.close();
    }
}
