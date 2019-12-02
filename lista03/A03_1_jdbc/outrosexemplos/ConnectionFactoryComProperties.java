/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package A03_1_jdbc.outrosexemplos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class ConnectionFactoryComProperties {
    public Connection getConnection() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream("/A09_jdbc/exemplos/outrosexemplos/bancoDeDados.properties"));
            String dbDriver = prop.getProperty("db.driver");
            String dbUrl = prop.getProperty("db.url");
            String dbUser = prop.getProperty("db.user");
            String dbPwd = prop.getProperty("db.pwd");
            Class.forName(dbDriver);
            System.out.println("URL="+dbUrl+" User"+dbUser+" pwd="+ dbPwd);
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            //con.setAutoCommit(false);
            return con;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
