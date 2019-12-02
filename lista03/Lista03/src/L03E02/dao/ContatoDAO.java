package L03E02.dao;

import L03E02.model.Contato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Contato> list() {
        DataSource datasource = new DataSource();
        List<Contato> lista = new ArrayList<Contato>();
        try {
            String SQL = "SELECT id, nome, email, telefone FROM contato;";
            ps = datasource.getConnection().prepareStatement(SQL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Contato conta = new Contato();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("nome"));
                conta.setEmail(rs.getString("email"));
                conta.setTelefone(rs.getString("telefone"));
                lista.add(conta);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("!ERRO! - ContatoDAO - Interação com BD " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("!ERRO! - ContatoDAO - Erro geral 1 " + ex.getMessage());
        } finally {
            datasource.closeConnection();
        }
        return lista;
    }
    
    public void altera(Contato c) {
        DataSource datasource = new DataSource();
        try {
            String SQL = "SELECT id, nome, email, telefone FROM contato WHERE id = " + c.getId();
            ps = datasource.getConnection().prepareStatement(SQL);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("!ERRO! - Contato não encontrado pelo ID informado");
            } else {
                Contato cont = new Contato();
                cont.setId(rs.getInt("id"));
                cont.setNome(rs.getString("nome"));
                cont.setEmail(rs.getString("email"));
                cont.setTelefone(rs.getString("telefone"));
                if (!c.getNome().equals("")) {cont.setNome(c.getNome());}
                if (!c.getEmail().equals("")) {cont.setEmail(c.getEmail());}
                if (!c.getTelefone().equals("")) {cont.setTelefone(c.getTelefone());}
                SQL = "UPDATE contato SET nome = ?, email = ?, telefone = ? WHERE id = ?";
                ps = datasource.getConnection().prepareStatement(SQL);
                ps.setString(1, cont.getNome());
                ps.setString(2, cont.getEmail());
                ps.setString(3, cont.getTelefone());
                ps.setInt(4, cont.getId());
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    System.err.println("!ERRO! - Update não ocorreu. Contato não encontrado");
                } else {
                    System.out.println("Update realizado com sucesso!");
                }
            }
        } catch (SQLException ex) {
            System.err.println("!ERRO! - ContatoDAO - Interação com BD " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("!ERRO! - ContatoDAO - Erro geral " + ex.getMessage());
        } finally {
            datasource.closeConnection();
        }
    }
    
    public void insere(Contato c) {
        DataSource datasource = new DataSource();
        try {
            String SQL = "INSERT INTO contato values(DEFAULT, ?, ?, ?)";
            ps = datasource.getConnection().prepareStatement(SQL);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefone());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.err.println("!ERRO! - ContatoDAO - Contato não adicionado");
            } else {
                System.out.println("Contato adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            System.err.println("!ERRO! - ContatoDAO - Interação com BD " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("!ERRO! - ContatoDAO - Erro geral " + ex.getMessage());
        } finally {
            datasource.closeConnection();
        }  
    }
    
    public void remove(Contato c) {
        DataSource datasource = new DataSource();
        try {
            String SQL = "DELETE FROM contato WHERE id = " + c.getId();
            ps = datasource.getConnection().prepareStatement(SQL);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.err.println("!ERRO! - ContatoDAO - Contato não excluido");
            } else {
                System.out.println("Contato removido com sucesso!");
            }
        } catch (SQLException ex) {
            System.err.println("!ERRO! - ContatoDAO - Interação com BD " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("!ERRO! - ContatoDAO - Erro geral " + ex.getMessage());
        } finally {
            datasource.closeConnection();
        }
    }   
}