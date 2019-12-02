package L04E03;

public class Circunferencia implements Superficie{
    double r;
    
    public Circunferencia() {
        r = 0.0;
    }
    
    public Circunferencia(double r) {
        this.r = r;
    }
    
    @Override
    public double area() {
        return Math.PI * (Math.pow(r, 2));
    }
    
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }    
}
