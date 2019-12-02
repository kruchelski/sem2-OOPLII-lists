package L03E02.view;

import L03E02.dao.ContatoDAO;
import L03E02.model.Contato;
import java.util.List;
import java.util.Scanner;

public class Ex03Main {
    private static ContatoDAO cDao = new ContatoDAO();
    private static Contato c = new Contato();
    public static void main(String args[]) {
        menu();
    }
    
    public static void menu() {
        Scanner scn = new Scanner(System.in);
        String op = "";
        while (!op.equals("0")) {
            listagem();
            System.out.println("O que deseja fazer?\n");
            System.out.println("1) Inserir novo contato");
            System.out.println("2) Remover um contato");
            System.out.println("3) Alterar um contato baseado no ID");
            System.out.println("\n0) Encerrar...");
            op = scn.nextLine();
            switch(op){
                case("1"):
                    inserir(c);
                    break;
                case("2"):
                    remover(c);
                    break;
                case("3"):
                    atualizar(c);
                    break;
                case("0"):
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente outra\n");
                    break;
            }
        }
    }
    
    public static void listagem() {
        List<Contato> cont = cDao.list();
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                                CONTATOS ADICIONADOS                              +");
        System.out.println("+----+------------------------------+------------------------------+---------------+");
        System.out.println("| ID |            NOME              |            EMAIL             |    TELEFONE   |");
        System.out.println("+----+------------------------------+------------------------------+---------------+");
        for (Contato ct : cont) {
            System.out.printf("|%4d|%30s|%30s|%15s|\n", ct.getId(), ct.getNome(), ct.getEmail(), ct.getTelefone());
        }
        System.out.println("+----+------------------------------+------------------------------+---------------+");    
    }
    
    public static void inserir(Contato c) {
        Scanner scn = new Scanner(System.in);
        String temp = "";
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                                    NOVO CONTATO                                  +");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("Insira o nome do novo contato:");
        System.out.print("Nome: ");
        temp = scn.nextLine();
        c.setNome(temp);
        System.out.println("Insira o email do novo contato:");
        System.out.print("email: ");
        temp = scn.nextLine();
        c.setEmail(temp);
        System.out.println("Insira o telefone do novo contato:");
        System.out.print("Telefone: ");
        temp = scn.nextLine();
        c.setTelefone(temp);
        cDao.insere(c);    
    }
    
    public static void remover(Contato c) {
        Scanner scn = new Scanner(System.in);
        String temp = "";
        int num;
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                                  REMOVER CONTATO                                 +");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("Informe o ID do contato que deseja remover");
        while(true) {
            try { 
                System.out.print("ID: ");
                temp = scn.nextLine();
                num = Integer.parseInt(temp);
                if (num >= 0) { break;}
                else {System.err.println("Valor deve ser maior ou igual a zero");}
            } catch(NumberFormatException ex) {
                System.err.println("Valor inserido não é válido");
            }
        }
        c.setNome(null);
        c.setEmail(null);
        c.setTelefone(null);
        c.setId(num);
        cDao.remove(c);
    }
    
    public static void atualizar(Contato c) {
        Scanner scn = new Scanner(System.in);
        String temp  = "", op = "";
        Boolean confirm = false;
        int num;
        c.setId(-999);
        c.setNome("");
        c.setEmail("");
        c.setTelefone("");
        while (true) {
                try {
                    System.out.println("Informe o ID do contato que deseja atualizar:");
                    System.out.print("ID: ");
                    temp = scn.nextLine();
                    num = Integer.parseInt(temp);
                    if (num < 0) {System.err.println("Insira um ID maior ou igual a zero\n");}
                    else break;
                } catch(NumberFormatException ex) {
                    System.err.println("ID informado inválido\n");
                }
            } 
            c.setId(num);
        while ((!op.equals("0")) && (!op.equals("9"))) {
            if ((!c.getNome().equals("")) || (!c.getEmail().equals("")) || (!c.getTelefone().equals(""))) {confirm = true;}
            System.out.println("+----------------------------------------------------------------------------------+");
            System.out.println("|                                 ATUALIZAR CONTATO                                +");
            System.out.println("+----------------------------------------------------------------------------------+");
            System.out.println("|                               Status da atualização                              |");
            System.out.println("+----+------------------------------+------------------------------+---------------+");
            System.out.println("| ID |            NOME              |            EMAIL             |    TELEFONE   |");
            System.out.println("+----+------------------------------+------------------------------+---------------+");
            System.out.printf("|%4d|%30s|%30s|%15s|\n", c.getId(), c.getNome(), c.getEmail(), c.getTelefone());
            System.out.println("+----+------------------------------+------------------------------+---------------+");
            System.out.println("| O que deseja fazer:                                                              |\n");
            System.out.println("| 1) Setar novo NOME                                                               |");
            System.out.println("| 2) Setar novo EMAIL                                                              |");
            System.out.println("| 3) Setar novo TELEFONE                                                           |");
            if(confirm){System.out.println("| 9) Confirmar...                                                                  |");}
            System.out.println("| 0) Cancelar...                                                                   |");
            System.out.println("+----------------------------------------------------------------------------------+");
            System.out.print("> Opção: ");
            op = scn.nextLine();
            if ((!confirm) && op.equals("9")) {op = "tralala";}
            
            switch (op) {
                case ("1"):
                    temp = null;
                    System.out.println("Digite um novo NOME:");
                    while (true){
                        System.out.print("NOME: ");
                        temp = scn.nextLine();
                        if (temp.equals("")){System.err.println("Insira um NOME válido\n");}
                        else break;
                    }
                    c.setNome(temp);
                    break;
                case ("2"):
                    temp = null;
                    System.out.println("Digite um novo EMAIL:");
                    while (true){
                        System.out.print("EMAIL: ");
                        temp = scn.nextLine();
                        if (temp.equals("")){System.err.println("Insira um EMAIL válido\n");}
                        else break;
                    }
                    c.setEmail(temp);
                    break;
                case ("3"):
                    temp = null;
                    System.out.println("Digite um novo TELEFONE:");
                    while (true){
                        System.out.print("TELEFONE: ");
                        temp = scn.nextLine();
                        if (temp.equals("")){System.err.println("Insira um TELEFONE válido\n");}
                        else break;
                    }
                    c.setTelefone(temp);
                    break;
                case ("9"):
                    System.out.println("Novos valores confirmados.");
                    cDao.altera(c);
                    break;
                case ("0"):
                    System.out.println("Cancelando a atualização");
                    break;
                default:
                    System.err.println("Opção inválida\n");
                    break;
            }
        }      
    }   
}
