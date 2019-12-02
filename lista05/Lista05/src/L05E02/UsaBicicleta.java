package L05E02;

public class UsaBicicleta {

    public static void main(String args[]) {
        class Bike {
            Bicicleta b = new Bicicleta() {
                @Override
                public void aplicarFreios(int decremento) {
                    velocidade = velocidade - 2 * decremento;
                }
            };
        } 
        Bicicleta bici = new Bicicleta();
        Bike bike = new Bike();
        
        bici.mudarCadencia(5);
        bici.mudarMarcha(12);
        bici.aumentarVelocidade(13);
        System.out.println("Estado da bicicleta antes de aplicar os freios:");
        bici.printStates();
        bike.b.mudarCadencia(5);
        bike.b.mudarMarcha(12);
        bike.b.aumentarVelocidade(13);
        System.out.println("Estado da bike antes de aplicar os freios:");
        bike.b.printStates();
        bici.aplicarFreios(6);
        bike.b.aplicarFreios(6);
        System.out.println("Estado da bicicleta após aplicar os freios:");
        bici.printStates();
        System.out.println("Estado da bike após aplicar os freios");
        bike.b.printStates();
    }
}
