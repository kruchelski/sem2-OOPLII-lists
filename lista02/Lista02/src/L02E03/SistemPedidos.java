package L02E03;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemPedidos {

    List<Pedido> listaPedido = new ArrayList<>();
    String op, temp, nome;
    double valor;
    int q;
    char o;
    boolean saindo = false;
    Scanner scn = new Scanner(System.in);
    
    public SistemPedidos() {
        menu();
    }

    void menu() {
        while (!saindo) {
            System.out.println("\n+----------+ MENU PRINCIPAL +----------+\n");
            System.out.println("1 - Incluir Pedido");
            System.out.println("2 - Excluir Pedido por nome do cliente");
            System.out.println("3 - Listar Pedidos");
            System.out.println("4 - Incluir Item de Pedido em Pedido");
            System.out.println("5 - Excluir item de Pedido em Pedido");
            System.out.println("6 - Imprimir a lista de Pedidos");
            System.out.println("\n0 - Sair da aplicação\n");
            System.out.print("Opção: ");
            op = scn.nextLine();
            o = op.toUpperCase().charAt(0);

            switch (o) {
                case ('1'): //incluir pedido
                    incluirPedido();
                    break;
                case ('2'): //Excluir edido por nome de cliente
                    excluirPedido();
                    break;
                case ('3'): //Listar pedidos
                    listarPedido();
                    break;
                case ('4'): //Inlcuir item de pedido em pedido
                    incluirItemPedido();
                    break;
                case ('5'): //Excluir item de pedido
                    excluirItemPedido();
                    break;
                case ('6'): // Imprimir lista de pedidos
                    listarTodosPedidos();
                    break;
                case ('0'):
                    System.out.println("\nSaindo da aplicação...\n");
                    saindo = true;
                    break;

                default:
                    System.out.println("\nOpção inválida. Tente novamente\n");
                    break;
            }

        }
    }

    public static double conversor(String snum) {
        double num;
        try {
            num = Double.parseDouble(snum);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Valor não é um número válido.");
        }
        return num;
    }

    public static int conversorInt(String snum) {
        int num;
        try {
            num = Integer.parseInt(snum);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Valor não é um número válido");
        }
        return num;
    }

    public void incluirPedido() {
        System.out.println("\nIncluindo um pedido\n");
        while (true) {
            try {
                System.out.println("Digite o nome do cliente");
                System.out.print("Nome: ");
                nome = scn.nextLine();
                while (true) {
                    try {
                        System.out.println("\nDigite o valor máximo do pedido");
                        System.out.print("Valor: R$");
                        temp = scn.nextLine();
                        valor = conversor(temp);
                        break;
                    } catch (RuntimeException re) {
                        System.out.println("\nERRO: " + re.getMessage() + "\n");
                    }
                }
                Pedido p = new Pedido(nome, valor);
                listaPedido.add(p);
                System.out.println("\nPedido de " + p.getNomeCliente() + " adicionado com sucesso!\n");
                break;
            } catch (RuntimeException re) {
                System.out.println("\nERRO: " + re.getMessage() + "\n");
            }
        }
    }

    public void excluirPedido() {
        if (listaPedido.isEmpty()) {
            System.out.println("ERRO: Não existem pedidos!\n");
        } else {
            boolean pedidoFound = false;
            System.out.println("\nExcluindo um pedido\n");
            System.out.println("Clientes com pedidos:");
            for (Pedido i : listaPedido) {
                System.out.println(i.getNomeCliente());
            }
            System.out.println("\nDigite o nome do cliente cujo pedido deseja excluir");
            System.out.print("Cliente: ");
            nome = scn.nextLine();
            for (Pedido i : listaPedido) {
                if (i.getNomeCliente().toUpperCase().equals(nome.toUpperCase())) {
                    listaPedido.remove(i);
                    System.out.println("Removido com sucesso o pedido do cliente " + nome + "\n");
                    pedidoFound = true;
                    break;
                }
            }
            if (!pedidoFound) {
                System.out.println("\nCliente não encontrado\n");
            }
        }
    }

    public void listarPedido() {
        if (listaPedido.isEmpty()) {
            System.out.println("ERRO: Não existem pedidos!\n");
        } else {
            boolean pedidoFound = false;
            System.out.println("\nListando um pedido\n");
            System.out.println("Clientes com pedidos:");
            for (Pedido i : listaPedido) {
                System.out.println(i.getNomeCliente());
            }
            System.out.println("\nDigite o nome do cliente cujo pedido deseja listar");
            System.out.print("Cliente: ");
            nome = scn.nextLine();
            for (Pedido i : listaPedido) {
                if (i.getNomeCliente().toUpperCase().equals(nome.toUpperCase())) {
                    System.out.println(i.toString2());
                    pedidoFound = true;
                    break;
                }
            }
            if (!pedidoFound) {
                System.out.println("\nCliente não encontrado\n");
            }
        }
    }

    public void incluirItemPedido() {
        if (listaPedido.isEmpty()) {
            System.out.println("ERRO: Não existem pedidos!\n");
        } else {
            boolean pedidoFound = false;
            System.out.println("\nIncluindo um item a um pedido\n");
            System.out.println("Clientes com pedidos:");
            for (Pedido i : listaPedido) {
                System.out.println(i.getNomeCliente());
            }
            System.out.println("\nDigite o nome do cliente cujo pedido deseja ter um item incluido");
            System.out.print("Cliente: ");
            nome = scn.nextLine();
            for (Pedido i : listaPedido) {
                if (i.getNomeCliente().toUpperCase().equals(nome.toUpperCase())) {
                    System.out.println("Cliente " + i.getNomeCliente() + " com pedido encontrado!");
                    while (true) {
                        try {
                            System.out.println("Itens na lista de pedido do cliente " + i.getNomeCliente() + "\n");
                            for (ItemDePedido p : i.getListaItem()) {
                                System.out.println(p.getNome());
                            }
                            System.out.println("Digite o nome do item que deseja inserir");
                            System.out.print("Nome do item: ");
                            nome = scn.nextLine();
                            while (true) {
                                try {
                                    System.out.println("Digite o preço do produto");
                                    System.out.print("Preço: ");
                                    temp = scn.nextLine();
                                    valor = conversor(temp);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println("ERRO: " + re.getMessage() + "\n");
                                }
                            }
                            while (true) {
                                try {
                                    System.out.println("Digite a quantidade do produto");
                                    System.out.print("Quantidade: ");
                                    temp = scn.nextLine();
                                    q = conversorInt(temp);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println("ERRO: " + re.getMessage() + "\n");
                                }
                            }
                            ItemDePedido itemPedido = new ItemDePedido(nome, valor, q);
                            i.acrescentaItem(itemPedido);
                            System.out.println("Item: " + itemPedido.getNome() + " adicionado com sucesso à lista de " + i.getNomeCliente() + "\n");
                            break;
                        } catch (RuntimeException re) {
                            System.out.println("ERRO: " + re.getMessage() + "\n");
                            if (re.getMessage().equals("\nCliente " + i.getNomeCliente() + " não possui nenhum crédito\n")) break;
                        }
                    }
                    pedidoFound = true;
                    break;
                }
            }
            if (!pedidoFound) {
                System.out.println("\nCliente não encontrado\n");
            }
        }
    }

    public void excluirItemPedido() {
        if (listaPedido.isEmpty()) {
            System.out.println("ERRO: Não existem pedidos!\n");
        } else {
            boolean pedidoFound = false;
            System.out.println("\nExcluindo um item de um pedido\n");
            System.out.println("Clientes com pedidos:");
            for (Pedido i : listaPedido) {
                System.out.println(i.getNomeCliente());
            }
            System.out.println("Digite o nome do cliente cujo pedido deseja ter um item excluido");
            System.out.print("Cliente: ");
            nome = scn.nextLine();
            for (Pedido i : listaPedido) {
                if (i.getNomeCliente().toUpperCase().equals(nome.toUpperCase())) {
                    System.out.println("Cliente " + i.getNomeCliente() + " com pedido encontrado!");
                    try {
                        System.out.println("Itens na lista de pedido do cliente " + i.getNomeCliente() + "\n");
                            for (ItemDePedido p : i.getListaItem()) {
                                System.out.println(p.getNome());
                            }
                        System.out.println("Digite o nome do item que deseja remover do pedido:");
                        System.out.print("Item: ");
                        nome = scn.nextLine();
                        i.retiraItem(nome);
                        pedidoFound = true;
                        break;
                    } catch (RuntimeException re) {
                        System.out.println("ERRO: " + re.getMessage() + "\n");
                    }
                }
            }
            if (!pedidoFound) {
                System.out.println("\nCliente não encontrado\n");
            }
        }
    }
    
    public void listarTodosPedidos() {
        int i = 1;
        if (listaPedido.isEmpty()) {
            System.out.println("ERRO: Não existem pedidos!\n");
        } else {
            for (Pedido p : listaPedido) {
                System.out.println("\nPedido " + i);
                System.out.println(p.toString2());
                i++;
            }
            System.out.println("\n+---------+ FIM DO RELATÓRIO +---------+\n");
        }        
    }

}
