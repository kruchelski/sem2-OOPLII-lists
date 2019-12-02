package L04E02;

public class Pilha<T> {
    private T valor;
    private Pilha<T> link;
    
    public Pilha() {
        this.link = null;
        this.valor = null;
    }
    
    public Pilha(Pilha<T> link, T valor) {
        setLink(link);
        setValor(valor);
    }
    
    public void empilha(T valor) {
        Pilha<T> temp = new Pilha(this.getLink(), valor);
        setLink(temp);
    }
    
    public void desempilha() {
        if (getLink() != null) {
            Pilha<T> temp = this.getLink();
            this.setLink(temp.getLink());
        } else System.out.println("\nPilha vazia\n");
    }
    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("[ ");
        Pilha<T> topo = this.getLink();
        while (this.getLink() != null) {
            Pilha<T> temp;
            temp = this.getLink();
            res.append(temp.getValor());
            if (temp.getLink() != null) res.append(" , ");
            this.setLink(temp.getLink());
        }
        res.append(" ]");
        this.setLink(topo);
        return res.toString();
    } 

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public Pilha<T> getLink() {
        return link;
    }

    public void setLink(Pilha<T> link) {
        this.link = link;
    }
    
    
    
}
