package L02E02;

import java.util.Scanner;


public class UsaForma {
    
    public static double conversor(String snum) {
        double num;
        try {
            num = Double.parseDouble(snum);
        } catch(NumberFormatException e) {
            throw new RuntimeException("Valor não é um número válido.");
        }
        return num;
    }
    
    public static int conversorInt(String snum) {
        int num;
        try {
            num = Integer.parseInt(snum);
        } catch(NumberFormatException e) {
            throw new RuntimeException("Valor não é um número válido");
        }
        return num;
    }
    
    public static void printResultado(Forma[] formas, int k) {
        int i;
        for (i = 0; i < k; i++) {
            System.out.print("Entry " + (i + 1) + ": " + formas[i].printDados());
        }
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String op = new String();
        System.out.println("Quantas formas?\n");
        int i, q;
        double d1, d2;
        boolean valid = false;
        while(true) {
            try {
                System.out.println("Quant: ");
                op = scn.nextLine();
                q = conversorInt(op);
                if (q < 1) System.out.println("Valor inválido. Insira número maior que 0\n");
                else break;
            } catch (RuntimeException re) {
                System.out.println(re.getMessage() + "\n");
            }
        }
        Forma[] formas = new Forma[q];
        
        for (i = 0; i < q; i++) {
            valid = false;
            while (!valid) {
                System.out.println("Selecione a forma " + (i + 1) + " de " + q + "\n");
                System.out.println("Digite S para interromper antes de " + (q) + "\n");
                System.out.println("C - Circunferência\nR - Retângulo\nT - Triângulo\n");
                System.out.print("Opção: ");
                op = scn.nextLine();
                op = op.toUpperCase();
                switch(op) {
                    case("C"):
                        System.out.println("Circunferência escolhido");
                        while (true) { // while para o laço do construtor
                            while (true) { // while para o laço da conversao
                                try {
                                    System.out.println("Digite o raio");
                                    op = scn.nextLine();
                                    d1 = conversor(op);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println(re.getMessage());
                                }
                            }
                            try {
                                formas[i] = new Circunferencia(d1);
                                break;
                            } catch(RuntimeException re) {
                                System.out.println(re.getMessage());
                            }
                        }
                        valid = true;
                    break;
                    case("T"):
                        System.out.println("Triângulo escolhido");
                        while (true) { // while para o laço do construtor
                            while (true) { // while para o laço da conversao da base
                                try {
                                    System.out.println("Digite a base");
                                    op = scn.nextLine();
                                    d1 = conversor(op);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println(re.getMessage());
                                }
                            }
                            while (true) { // while para o laço da conversao da altura
                                try {
                                    System.out.println("Digite a altura");
                                    op = scn.nextLine();
                                    d2 = conversor(op);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println(re.getMessage());
                                }
                            }
                            try {
                                formas[i] = new Triangulo(d1, d2);
                                break;
                            } catch(RuntimeException re) {
                                System.out.println(re.getMessage());
                            }
                        }
                        valid = true;
                    break;
                    
                    case("R"):
                        System.out.println("Retângulo escolhido");
                        while (true) { // while para o laço do construtor
                            while (true) { // while para o laço da conversao da largura
                                try {
                                    System.out.println("Digite a largura");
                                    op = scn.nextLine();
                                    d1 = conversor(op);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println(re.getMessage());
                                }
                            }
                            while (true) { // while para o laço da conversao da altura
                                try {
                                    System.out.println("Digite a altura");
                                    op = scn.nextLine();
                                    d2 = conversor(op);
                                    break;
                                } catch (RuntimeException re) {
                                    System.out.println(re.getMessage());
                                }
                            }
                            try {
                                formas[i] = new Retangulo(d1, d2);
                                break;
                            } catch(RuntimeException re) {
                                System.out.println(re.getMessage());
                            }
                        }
                        valid = true;
                    break;
                    
                    case("S"):
                        System.out.println("Interrompendo...\n");
                        valid = true;
                    break;
                    
                    default:
                        System.out.println("Opção incorreta. Tente outra vez...\n\n");
                    break;      
                }
            }
            if (op.equals("S")) break;
        }
        if (i > 0) {
            System.out.println("Resultado para as " + i + " Formas\n");
            printResultado(formas, i);
        } else System.out.println("Cancelado...\n");
    }  
}
