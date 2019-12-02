package L02E03;

import java.util.List;
import java.util.ArrayList;

public class Pedido {

    private List<ItemDePedido> listaItem = new ArrayList<ItemDePedido>();
    private double maxValor;
    private String nomeCliente;

    public Pedido(String nomeCliente, double maxValor) {
        String msg = "";
        if (nomeCliente.equals("") || nomeCliente.isEmpty() || nomeCliente.equals(null)) {
            msg += "Nome vazio! - ";
        }
        if (maxValor < 0) {
            msg += "Preço menor que zero! - ";
        }
        msg += "Objeto não criado. Corrigir problemas.\n";
        if (nomeCliente.equals("") || nomeCliente.isEmpty() || nomeCliente.equals(null) || (maxValor < 0)) {
            throw new RuntimeException(msg);
        } else {
            this.setNomeCliente(nomeCliente);
            this.setMaxValor(maxValor);
        }
    }

    public void acrescentaItem(ItemDePedido item) {
        boolean itemFound = false;
        if (item == null) {
            throw new RuntimeException("\nItem vazio!!!\n");
        } else if (this.getMaxValor() == 0.0) {
            throw new RuntimeException("\nCliente " + this.getNomeCliente() + " não possui nenhum crédito\n");
        } else if (((item.getPreco() * item.getQuant()) + this.getValorTotal()) > this.getMaxValor()) {
            throw new RuntimeException("\nItem de Pedido não incluído. Valor do pedido excedido\n");
        } else {
            for (ItemDePedido i : listaItem) {
                if (i.getNome().toUpperCase().equals(item.getNome().toUpperCase())) { // toUpperCase pra ignorar case sensitive
                    i.setQuant(i.getQuant() + item.getQuant());
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                this.listaItem.add(item);
            }
        }
    }

    public void retiraItem(String nome) {
        if (listaItem.isEmpty()) {
            System.out.println("\nERRO: Lista de itens vazia!\n");
        } else {
            boolean itemFound = false;
            for (ItemDePedido i : listaItem) {
                if (i.getNome().toUpperCase().equals(nome.toUpperCase())) { // coloca tudo em upper case pra ignorar case sensitvie
                    listaItem.remove(i);
                    System.out.println("Item " + nome + " removido com sucesso\n");
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                throw new RuntimeException("\nItem não encontrado no pedido\n");
            }
        }
    }

    public String toString2() {
        String msg = "";
        msg += "\n-----------------------------------------------";
        msg += "\nNome do cliente: " + this.getNomeCliente();
        msg += "\nTotal do pedido: " + this.getValorTotal() + "| Valor máximo: " + this.getMaxValor(); //TODO tirar a parte do maxvalor
        msg += "\nItem\t\t\t|Preço";
        for (ItemDePedido i : listaItem) {
            msg += "\n" + i.getNome() + "\t\t | R$" + i.getPreco(); // TODO (?) talvez precise arrumar os \t
        }
        msg += "\n-----------------------------------------------";
        return msg;
    }

    public List<ItemDePedido> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<ItemDePedido> listaItem) {
        this.listaItem = listaItem;
    }

    public double getMaxValor() {
        return maxValor;
    }

    public void setMaxValor(double maxValor) {
        this.maxValor = maxValor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getValorTotal() {
        double total = 0.0;
        for (ItemDePedido i : listaItem) {
            total += (i.getPreco() * i.getQuant());
        }
        return total;
    }

}
