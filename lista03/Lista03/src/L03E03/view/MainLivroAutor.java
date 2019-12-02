package L03E03.view;

import L03E03.dao.AutorDAO;
import L03E03.dao.LivroDAO;
import L03E03.model.Autor;
import L03E03.model.Livro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainLivroAutor {

    private AutorDAO autorDAO;
    private LivroDAO livroDAO;

    public MainLivroAutor() throws Exception {
        autorDAO = new AutorDAO();
        livroDAO = new LivroDAO();
    }

    public static void main(String args[]) throws Exception {
        MainLivroAutor main = new MainLivroAutor();
        String opcao = "";
        while (true) {
            try {
                System.out.println("Escolha uma das opções e tecle <ENTER>: ");
                System.out.println("  1 - Incluir Autor");
                System.out.println("  2 - Incluir Livro");
                System.out.println("  3 - Listar Autores");
                System.out.println("  4 - Listar Livro com autores");
                System.out.println("  5 - Listar Autores de um livro");
                System.out.println("  6 - Listar Livros de um autor");
                System.out.println("  7 - Sair");
                Scanner sc = new Scanner(System.in);
                opcao = sc.nextLine();
                switch (opcao) {
                    case "1":
                        main.incluirAutor();
                        break;
                    case "2":
                        main.incluirLivro();
                        break;
                    case "3":
                        main.listarAutores();
                        break;
                    case "4":
                        main.listarLivroComAutores();
                        break;
                    case "5":
                        main.listarAutoresDeLivro();
                        break;
                    case "6":
                        main.listarLivrosdeAutor();
                        break;
                    case "7":
                        System.err.println("Encerrando...\n");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                if (opcao.equals("7")) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Falha na operação. Mensagemm=" + ex.getMessage());
            }
        }
    }

    public void incluirAutor() throws Exception { //CONCLUIDO---- TODO: Arrumar autorDAO para adicionar a inserção dos livros na tabela livro_autor
        Boolean repeated = false, addByNome = true, cadastrado = false;
        List<Livro> livroList = new ArrayList<Livro>(); //vai criar uma lista de livro para setar no objeto autor
        List<Livro> showLivros = new ArrayList<Livro>();
        String nome, op;
        Livro livroTemp = null;
        Scanner sc = new Scanner(System.in);
        int idTemp = -1;
        while (true) { //Laço para solicitar a entrada de um nome válido
            System.out.print("Digite o nome do autor para adicionar:");
            nome = sc.nextLine();
            if (nome.equals("") || (nome == null)) {
                System.err.println("Digite um nome válido\n");
            } else {
                break;
            }
        }
        Autor autor = new Autor(nome);
        while (true) {
            System.out.println("Deseja associar os livros utilizando o nome ou o ID?");
            System.out.println("1) Nome \t 2) ID\n");
            System.out.print("Opção: ");
            op = sc.nextLine();
            if (op.equals("1")) {
                addByNome = true;
                System.out.println("\nSelecionado: NOME\n");
                break;
            } else if (op.equals("2")) {
                addByNome = false;
                System.out.println("\nSelecionado: ID\n");
                break;
            } else {
                System.err.println("ERRO - Opção inválida\n");
            }
        }
        showLivros = livroDAO.listarLivroComAutores();
        while (true) {
            repeated = false;
            cadastrado = false;
            System.out.printf("Digite o %s do livro para associar ao autor (para encerrar, digite --q):\n\n", addByNome ? "nome" : "id");
            System.out.printf("--------- %20s ---------\n", "Livros disponíveis");
            showLivros(showLivros);
            System.out.printf("\n--------- %20s ---------\n", "Livros associados");
            showLivros(livroList);
            System.out.printf("\n%s do livro: ", addByNome ? "nome" : "id");
            String temp = sc.nextLine();
            if (temp.equals("--q")) {
                break;
            }
            if (!addByNome) {
                try {
                    idTemp = Integer.parseInt(temp);
                } catch (NumberFormatException ex) {
                    System.err.println("O que foi digitado não é um número válido\n\n");
                    idTemp = -99;
                }
                if (idTemp > 0) {
                    if (livroList.size() > 0) {
                        for (Livro l : livroList) {
                            if (l.getId() == idTemp) {
                                System.err.println("Livro adicionado anteriormente\n");
                                repeated = true;
                                break;
                            }
                        }
                    }
                    if ((showLivros.size() > 0) && (!repeated)) {
                        for (Livro l : showLivros) {
                            if (l.getId() == idTemp) {
                                System.err.println("Livro existente\n");
                                cadastrado = true;
                                break;
                            }
                        }
                    }

                }
            } else {
                if (livroList.size() > 0) {
                    for (Livro l : livroList) {
                        if (l.getTitulo().equals(temp)) {
                            System.err.println("Livro adicionado anteriormente\n");
                            repeated = true;
                            break;
                        }
                    }
                }
                if ((showLivros.size() > 0) && (!repeated)) {
                    for (Livro l : showLivros) {
                        if (l.getTitulo().equals(temp)) {
                            System.err.println("Livro existente\n");
                            cadastrado = true;
                            break;
                        }
                    }
                }
            }
            if (!cadastrado) {
                System.err.println("ERRO - Livro não existente. Tente outro\n");
            } else {
                if (!repeated) {
                    if (addByNome) {
                        livroTemp = livroDAO.getLivroByNome(temp);
                        livroList.add(livroTemp);
                    } else {
                        livroList.add(livroDAO.consultarLivro(idTemp));
                    }
                }
            }
        }
        autor.getLivros().clear();
        autor.setLivros(livroList);
        autorDAO.inserirAutor(autor);
    }

    public void incluirLivro() {
        List<Autor> showAutores = new ArrayList<Autor>();
        try {
            showAutores = autorDAO.listarAutores();
        } catch (Exception ex) {
            System.err.println("Erro ao resgatar autores do bd: " + ex.getMessage());
        }
        System.out.print("Digite o título do livro:");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        int numAutores = 1;
        List<Autor> listaAutores = new ArrayList();
        int idAutor = 0;
        do {
            try {
                Scanner sc2 = new Scanner(System.in);
                System.out.printf("--------- %20s ---------\n", "Autores disponíveis");
                showAutores(showAutores);
                System.out.println("Informe o ID dos autores, um a um (-1 para encerrar");
                System.out.print("ID Autor " + numAutores + ":");
                idAutor = sc2.nextInt();
                if (idAutor == -1) {
                    break;
                }
                Autor autor = autorDAO.consultarAutor(idAutor);
                if (autor != null) {
                    listaAutores.add(autor);
                    numAutores++;
                } else {
                    System.out.println("Autor não existe!");
                }
            } catch (Exception ex) {
                System.out.println("ID autor não é inteiro ou inválido!");
            }
        } while (true);

        Livro livro = new Livro(titulo, listaAutores);
        livroDAO.inserirLivro(livro);
    }

    public void listarAutores() throws Exception {
        List<Autor> listaAutores = autorDAO.listarAutores();
        Collections.sort(listaAutores, new Comparator<Autor>() {
            public int compare(Autor arg0, Autor arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
        System.out.println("ID\tNOME");
        for (Autor autor : listaAutores) {
            System.out.println(autor.getId() + " \t" + autor.getNome());
        }
    }

    public void listarLivroComAutores() throws Exception {
        List<Livro> listaLivros = livroDAO.listarLivroComAutores();
        Collections.sort(listaLivros, new Comparator<Livro>() {
            public int compare(Livro arg0, Livro arg1) {
                String titulo = arg0.getTitulo();
                int i = titulo.compareToIgnoreCase(arg1.getTitulo());
                return i;
            }
        });
        System.out.println("ID\tTitulo do Livro\tAutores");
        for (Livro livro : listaLivros) {
            System.out.print(livro.getId() + "\t" + livro.getTitulo() + "\t");
            for (Autor autor : livro.getAutores()) {
                System.out.print(autor.getNome() + ";");
            }
            System.out.print("\n");
        }

    }

    public void showLivros(List<Livro> list) {
        if (list.size() == 0) {
            System.out.println("Lista de livros vazia!\n");
        } else {
            System.out.println("+----------------------------------------------+");
            System.out.println("|              LISTAGEM DE LIVROS              |");
            System.out.println("+-----+----------------------------------------+");
            System.out.println("| id  |          nome do livro                 |");
            System.out.println("+-----+----------------------------------------+");
            for (Livro l : list) {
                System.out.printf("|%5d|%40s|\n", l.getId(), l.getTitulo());
            }
            System.out.println("+-----+----------------------------------------+\n");
        }
    }
    
    public void showAutores(List<Autor> list) {
        if (list.size() == 0) {
            System.err.println("Lista de Autores vazia!\n");
        } else {
            System.out.println("+----------------------------------------------+");
            System.out.println("|             LISTAGEM DE AUTORES              |");
            System.out.println("+-----+----------------------------------------+");
            System.out.println("| id  |          nome do autor                 |");
            System.out.println("+-----+----------------------------------------+");
            for (Autor a : list) {
                System.out.printf("|%5d|%40s|\n", a.getId(), a.getNome());
            }
            System.out.println("+-----+----------------------------------------+\n");
        }
    }

    private void listarAutoresDeLivro() { //5
        Scanner sc = new Scanner(System.in);
        String temp = null;
        List<Livro> lista = livroDAO.listarLivroComAutores();
        System.out.printf("--------- %20s ---------\n", "Livros disponíveis");
        showLivros(lista);
        System.out.println("\nInforme qual livro deseja listar os autores");
        System.out.print("Livro: ");
        temp = sc.nextLine();
        int id;
        Livro livroSelecionado = null;
        for (Livro l : lista) {
            if (l.getTitulo().equals(temp)) {
                id = l.getId();
                livroSelecionado = livroDAO.consultarLivro(id);
                break;
            }
        }
        if (livroSelecionado == null) {
            System.err.println("\nLivro não cadastrado");   
        } else {
            List<Autor> showAutor = livroSelecionado.getAutores();
            System.out.printf("Autores do livro %s\n", livroSelecionado.getTitulo());
            showAutores(showAutor);
        }        
    }

    private void listarLivrosdeAutor() { //6
        Scanner sc = new Scanner(System.in);
        String temp = null;
        List<Autor> lista = null;
        try {
            lista = autorDAO.listarAutores();
        }catch(Exception ex) {
            System.err.println("Erro ao resgatar autores do bd: " + ex.getMessage());
        }
        System.out.printf("--------- %20s ---------\n", "Autores disponíveis");
        showAutores(lista);
        System.out.println("\nInforme qual autor deseja listar os livros");
        System.out.print("Autor: ");
        temp = sc.nextLine();
        int id;
        Autor autorSelecionado = null;
        for (Autor a : lista) {
            if (a.getNome().equals(temp)) {
                id = a.getId();
                autorSelecionado = autorDAO.consultarAutor(id);
                break;
            }
        }
        if (autorSelecionado == null) {
            System.err.println("\nAutor não cadastrado");   
        } else {
            List<Livro> showLivro = autorSelecionado.getLivros();
            showLivros(showLivro);
        } 
    }
}
