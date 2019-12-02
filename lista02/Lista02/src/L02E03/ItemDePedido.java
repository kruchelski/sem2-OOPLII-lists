package L02E03;

public class ItemDePedido {
    private String nome;
    private double preco;
    private int quant;
    
    public ItemDePedido(String nome, double preco, int quant) {
        String msg = "ERRO: ";
        if(nome.equals("") || nome.isEmpty() || nome.equals(null)) msg += "Nome vazio! - ";
        if (preco < 0) msg += "Preço menor que zero! - ";
        if (quant < 1) msg += "Quantidade menor que 1! - ";
        msg += "Objeto não criado. Corrigir problemas.\n";
        if((nome.equals("")) || (nome.isEmpty()) || (nome.equals(null)) || (preco < 0) || (quant < 1)){
            throw new RuntimeException(msg);
        } else {
            this.setNome(nome);
            this.setPreco(preco);
            this.setQuant(quant);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }  
}
