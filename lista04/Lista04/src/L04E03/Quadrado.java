package L04E03;
public class Quadrado implements Superficie {
    private double l;
    
    public Quadrado() {
        l = 0.0;
    }
    public Quadrado(double l) {
        this.l = l;
    }
    
    @Override
    public double area() {
        return l * l;
    }
    
    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }
}
