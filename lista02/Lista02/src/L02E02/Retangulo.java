package L02E02;

public class Retangulo extends Forma{

    public Retangulo(double larg, double alt) {
        String msg = "";
        if (larg < 0) msg += "Largura inválida - ";
        if (alt < 0) msg += "Altura inválida - ";
        if ((larg < 0) || (alt < 0)) {
            msg += "Número(s) negativo(s)\n";
            throw new RuntimeException(msg);
        } else {
            this.dim1 = larg;
            this.dim2 = alt;
            this.nome = "Retângulo";
            this.area = this.area();
            this.perimetro = this.perimetro(); 
        }
    }

    public double area() {
        return this.dim1 * this.dim2;
    }

    public double perimetro() {
        return this.dim1 * 2 + this.dim2 * 2;
    }
    
    @Override
    public String printDados() {
        String msg = "";
        msg += "Forma: " + this.getNome();
        msg += " - Área: " + this.getArea();
        msg += " - Perímetro: " + this.getPerimetro();
        msg += "\n";
        return msg;
    }
}
