/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.contato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.ufpr.contato.model.Contact;

/**
 *
 * @author Rafael
 */
public class ContactDao{
// a conexão com o banco de dados

    private final ConnectionFactory connectionFactory;
    private final String insert = "insert into contatos (nome,email,endereco,dataNascimento) values (?,?,?,?)";
    private final String select = "select * from contatos";
    private final String update = "update contatos set nome=?, email=?, endereco=?, dataNascimento=? WHERE id=?";
    private final String delete = "delete from contatos WHERE id=?";

    public ContactDao(ConnectionFactory conFactory) {
        this.connectionFactory = conFactory;
        connectionFactory.getConnection();
    }

    public void insert(Contact contact) {
        Connection connection=connectionFactory.getConnection();
        try {
            // prepared statement para inserção
            PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            // seta os valores
            stmtAdiciona.setString(1, contact.getName());
            stmtAdiciona.setString(2, contact.getEmail());
            stmtAdiciona.setString(3, contact.getAddress());
            stmtAdiciona.setDate(4, Date.valueOf(contact.getDataNascimento()));
            // executa
            stmtAdiciona.execute();
            //Seta o id do contato
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            contact.setId(i);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

    public List<Contact> getList() throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(select);
        try {
            rs = stmtLista.executeQuery();
            List<Contact> contatos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Contato
                //Contato contato = new Contato();
                long id = rs.getLong("id");
                String nome= rs.getString("nome");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");
                LocalDate dataNascimento = rs.getDate("dataNascimento").toLocalDate();
                
                // adicionando o objeto à lista
                contatos.add(new Contact(id,nome,email,endereco,dataNascimento));
            }
            
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmtLista.close();
        }

    }


    public void update(Contact contact) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        PreparedStatement stmtAtualiza = connection.prepareStatement(update);
        try {
            stmtAtualiza.setString(1, contact.getName());
            stmtAtualiza.setString(2, contact.getEmail());
            stmtAtualiza.setString(3, contact.getAddress());
            stmtAtualiza.setDate(4,Date.valueOf(contact.getDataNascimento()));
            stmtAtualiza.setLong(5, contact.getId());
            stmtAtualiza.executeUpdate();
        } finally{
            stmtAtualiza.close();
        }

    }

    public void delete(Contact contact) throws SQLException {
        Connection connection=connectionFactory.getConnection();
        PreparedStatement stmtExcluir;
        stmtExcluir = connection.prepareStatement(delete);
        try {
            stmtExcluir.setLong(1, contact.getId());
            stmtExcluir.executeUpdate();
        } finally{
            stmtExcluir.close();
        }

    }
}
