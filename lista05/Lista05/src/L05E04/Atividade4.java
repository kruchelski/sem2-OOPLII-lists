package L05E04;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Atividade4 {
    public static void main(String args[]) {
        double s, t;
        String temp;
        Scanner sc = new Scanner(System.in);
        
        while (true) { //Laço para pegar a distância pelo teclado tratando exeções
            try {
                System.out.println("Digite a distância percorrida (até 10 casas decimais)");
                System.out.println("nota: caso a quantidade de casas decimais ultrapasse 10 casas, será feito o arredondamento ");
                System.out.print("Distância: ");
                temp = sc.nextLine();
                s = Double.parseDouble(temp); //Conversao da entrada de s em String para double
                if (s >= 0) break;
                else System.out.println("\nO valor deve ser igual ou maior que 0");
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("\nValor inválido. Tente novamente");
            }
        }
        temp = "";
        while (true) { //Laço para pegar o tempo pelo teclado tratando as exceçoes
            try {
                System.out.println("Digite o tempo gasto durante o percurso (até 10 casas decimais");
                System.out.println("nota: caso a quantidade de casas decimais ultrapasse 10 casas, será feito o arredondamento");
                System.out.print("Tempo: ");
                temp = sc.nextLine();
                t = Double.parseDouble(temp); //Conversao da entrada de tempo em String para double
                if (t > 0) break;
                else System.out.println("\nO valor deve ser maior que 0");
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("\nValor inválido. Tente novamente");
            }
        }
        BigDecimal sBig = BigDecimal.valueOf(s); //Declaração da BigDecimal passando como parâmetro o valor de s
        BigDecimal tBig = BigDecimal.valueOf(t); //Declaração da BigDecimal passando como parâmetro o valor de t
        BigDecimal res; // Declaração do resultado
        if (sBig.scale() > 10) sBig = sBig.setScale(10, RoundingMode.FLOOR); // Se a quantidade de casas decimais 
        if (tBig.scale() > 10) tBig = tBig.setScale(10, RoundingMode.FLOOR); // passar de 10, retorna um BigDecimal com 10 números após a virgula
        res = sBig.divide(tBig, 3, RoundingMode.FLOOR); // Divisão pra acertar o resultado
        System.out.printf("Distância percorrida: %s\nTempo gasto: %s\nVelocidade média: %s\n", sBig.toString(), tBig.toString(), res.toString());   
    }
}
