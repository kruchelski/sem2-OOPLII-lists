package L02E02;
public class Circunferencia extends Forma{

    public Circunferencia(double num) {
        if (num < 0) {
            throw new RuntimeException("Valor inválido - Número negativo");
        } else {
            this.dim1 = num;
            this.nome = "Circunferência";
            this.area = this.area();
            this.perimetro = 0.0;
            this.dim2 = 0.0;
        }
    }

    public double area() {
        return Math.PI * (Math.pow(this.dim1, 2));
    }
    
    @Override
    public String printDados() {
        String msg = "";
        msg += "Forma: " + this.getNome();
        msg += " - Área: " + this.getArea();
        msg += "\n";
        return msg;
    }

    

    
}
