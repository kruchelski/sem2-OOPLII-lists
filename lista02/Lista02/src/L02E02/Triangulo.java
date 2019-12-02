package L02E02;

public class Triangulo extends Forma {

    public Triangulo(double bas, double alt) {
        String msg = "";
        if (bas < 0) msg += "Base inválida - ";
        if (alt < 0) msg += "Altura inválida - ";
        if ((bas < 0) || (alt < 0)) {
            msg += "Número(s) negativo(s)\n";
            throw new RuntimeException(msg);
        } else {
            this.dim1 = bas;
            this.dim2 = alt;
            this.nome = "Triângulo";
            this.area = this.area();
            this.perimetro = 0.0;
        }
    }

    public double area() {
        return (this.dim1 * this.dim2) / 2.0;
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
