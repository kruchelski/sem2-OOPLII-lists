package L03E03.dao;

import L03E03.model.Autor;
import L03E03.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private final String stmtInserir = "INSERT INTO livro VALUES(DEFAULT, ?)";
    private final String stmtConsultar = "SELECT id, nome FROM livro WHERE id = ?";
    private final String stmtListaLivroAutor = "SELECT id, nome FROM livro";
    private final String stmtGetLivroNome = "SELECT id, nome FROM livro WHERE nome = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public Livro getLivroByNome(String temp) {
        Livro livroOut = null;
        try {
            conn = new ConnectionFactory().getConn();
            stmt = conn.prepareStatement(stmtGetLivroNome);
            stmt.setString(1, temp);
            rs = stmt.executeQuery();
            if (rs.next()) {
                livroOut = new Livro(rs.getString("nome"), new ArrayList<Autor>());
                livroOut.setId(rs.getInt("id"));
            }
            return livroOut;
        } catch (SQLException ex) {
            System.err.println("\nERRO no banco de dados " + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                conn.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());

            }
        }
        return null;
    }

    public void inserirLivro(Livro livro) {
        try {
            conn = new ConnectionFactory().getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, livro.getTitulo());
            stmt.executeUpdate();
            int idLivroGravado = lerIdLivro(stmt);
            livro.setId(idLivroGravado);

            conn.commit();
            this.gravarAutores(livro, conn);

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao tentar rollback. Ex=" + ex1.getMessage());
            };
            throw new RuntimeException("Erro ao inserir um livro no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                conn.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    private int lerIdLivro(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Livro consultarLivro(int id) {
        Livro livroLido = null;
        try {
            conn = new ConnectionFactory().getConn();
            stmt = conn.prepareStatement(stmtConsultar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            List<Autor> listaAutores = lerAutores(id);//
            livroLido = new Livro(rs.getString("nome"), listaAutores);
            livroLido.setId(rs.getInt("id"));
            return livroLido;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um livro no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar rs. Ex=" + ex.getMessage());
            };
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                conn.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    private void gravarAutores(Livro livro, Connection con) throws SQLException {
        String sql = "INSERT INTO livro_autor (livro, autor) VALUES ( ?, ?)";
        conn = new ConnectionFactory().getConn();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, livro.getId());
        List<Autor> autores = livro.getAutores();
        for (Autor autor : autores) {
            stmt.setLong(2, autor.getId());
            stmt.executeUpdate();
        }
    }

    private List<Autor> lerAutores(int idLivro) throws SQLException {
        //Select para pegar os autores de um livro
        String sql = "SELECT autor.id,autor.nome"
                + " FROM autor"
                + " INNER JOIN livro_autor"
                + " 	ON autor.id = livro_autor.autor"
                + " WHERE livro_autor.livro = ? ";
        List<Autor> autores = null;
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idLivro);
        ResultSet resultado = stmt.executeQuery();
        autores = new ArrayList<Autor>();
        while (resultado.next()) {
            Autor autorLido = new Autor(resultado.getString("nome"));
            autorLido.setId(resultado.getInt("id"));
            autores.add(autorLido);
        }

        return autores;
    }

    public List<Livro> listarLivroComAutores() {
        try {
            conn = new ConnectionFactory().getConn();
            stmt = conn.prepareStatement(stmtListaLivroAutor);
            rs = stmt.executeQuery();
            List<Livro> listaLivros = new ArrayList<Livro>();
            while (rs.next()) {
                List<Autor> listAutores = lerAutores(rs.getInt(1));
                Livro livro = new Livro(rs.getString(2), listAutores);
                livro.setId(rs.getInt(1));
                listaLivros.add(livro);
            }

            return listaLivros;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar um livro com autores no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar rs. Ex=" + ex.getMessage());
            };
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                conn.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }
}
