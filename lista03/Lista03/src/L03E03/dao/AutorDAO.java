package L03E03.dao;

import L03E03.model.Autor;
import L03E03.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO { //Criar metodo lerLivros (semelhante ao lerAutores de LivroDAO

    private final String stmtInserir = "INSERT INTO autor VALUES(DEFAULT, ?)";
    private final String stmtConsultar = "SELECT * FROM autor WHERE id = ?";
    private final String stmtListar = "SELECT * FROM autor ORDER BY nome";
    private final String stmtInserirLivroAutor = "INSERT INTO livro_autor values (? , ?)";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public void inserirAutor(Autor autor) {
        try {
            conn = new ConnectionFactory().getConn();
            stmt = conn.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
            System.out.println("\nUpdate frealizado com sucesso!\n");
            autor.setId(lerIdAutor(stmt));
            if (autor.getLivros().size() > 0) {
                for (Livro l : autor.getLivros()) {
                    stmt = conn.prepareStatement(stmtInserirLivroAutor);
                    stmt.setInt(1, l.getId());
                    stmt.setInt(2, autor.getId());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
                System.err.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.err.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

    private int lerIdAutor(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Autor consultarAutor(int id) {
        Autor autorLido = null;
        try {
            conn = new ConnectionFactory().getConn();
            stmt = conn.prepareStatement(stmtConsultar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            
            List<Livro> listaLivros = lerLivros(id);
            autorLido = new Autor(rs.getString("nome"));
            autorLido.setId(rs.getInt("id"));
            autorLido.setLivros(listaLivros); 
            return autorLido;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um autor no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            }
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

    public List<Autor> listarAutores() throws Exception {
        List<Autor> lista = new ArrayList();
        try {
            conn = new ConnectionFactory().getConn();
            stmt = conn.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("nome"));
                autor.setId(rs.getInt("id"));
                lista.add(autor);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            }
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                conn.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

    public List<Livro> lerLivros(int id) throws SQLException {
        //Select para pegar os livros de um autor
        String sql = "SELECT livro.id,livro.nome"
                + " FROM livro"
                + " INNER JOIN livro_autor"
                + " 	ON (livro.id = livro_autor.livro)"
                + " WHERE livro_autor.autor = ? ";
        List<Livro> livros = null;
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet resultado = stmt.executeQuery();
        livros = new ArrayList<Livro>();
        while (resultado.next()) {
            Livro livroLido = new Livro();
            livroLido.setTitulo(resultado.getString("nome"));
            livroLido.setId(resultado.getInt("id"));
            livros.add(livroLido);
        }
        return livros;
    }
}
