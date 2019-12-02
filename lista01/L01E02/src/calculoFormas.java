import java.util.Scanner;

public class calculoFormas {

	public static double calculaAreaRetangulo(double largura, double altura) {
		if (largura < 0 || altura < 0) {
			throw new RuntimeException("Valor negativo");
		}
		return largura * altura;
	}

	public static double calculaAreaRetangulo(String largura, String altura) {
		
		double l = Double.parseDouble(largura);
		double a = Double.parseDouble(altura);
		if (l < 0 || a < 0) {
			throw new RuntimeException("Valor negativo");
		}
		return l * a;
	}

	public static double calculaAreaCircunferencia(double raio) {
		if (raio < 0) {
			throw new RuntimeException("Valor negativo");
		}
		return (Math.pow(raio, 2.0)) * 3.14;
	}

	public static double calculaAreaCircunferencia(String raio) {
		double r = Double.parseDouble(raio);
		if (r < 0) {
			throw new RuntimeException("Valor negativo");
		}
		return (Math.pow(r, 2.0)) * 3.14;
	}

	public static double calculaAreaTriangulo(double base, double altura) {
		if (base < 0 || altura < 0) {
			throw new RuntimeException("Valor negativo");
		}
		return (base * altura) / 2;
	}

	public static double calculaAreaTriangulo(String base, String altura) {
		double b = Double.parseDouble(base);
		double a = Double.parseDouble(altura);
		if (b < 0 || a < 0) {
			throw new RuntimeException("Valor negativo");
		}
		return (b * a) / 2;
	}
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		String op = "";
		String temp;
		
		double valor1, valor2;
		char opc;
		System.out.println("Cálculo de áreas...\n\n");
		while (true) {
			System.out.println("A - Área retângulo\nB - Área circunferência\nC - Área triângulo\n");
			System.out.print("Opção >> ");
			op = scn.nextLine();
			opc = op.toUpperCase().charAt(0);
			if (opc != 'A' && opc != 'B' && opc != 'C') {
				System.out.println("\nOpção incorreta. Tente novamente\n");
			} else {
				break;
			}
		}
		switch(opc) {
			case 'A':
				System.out.println("Área do retângulo\n");
				while (true) {
					System.out.println("Digite a largura do retângulo:");
					System.out.print("Largura >> ");
					temp = scn.nextLine();
					try {
						valor1 = Double.parseDouble(temp);
						break;
					} catch (NumberFormatException err) {
						System.out.println("Valor inválido. Tente novamente\n\n");
					}
				}
				while (true) {
					System.out.println("Digite a altura do retângulo:");
					System.out.print("Altura >> ");
					temp = scn.nextLine();
					try {
						valor2 = Double.parseDouble(temp);
						break;
					} catch (NumberFormatException err) {
						System.out.println("Valor inválido. Tente novamente\n\n");
					}
				}
				System.out.println("A área do retângulo é: " + calculaAreaRetangulo(valor1, valor2));
				break;
				
			case 'B':
				System.out.println("Área da circunferência\n");
				while (true) {
					System.out.println("Digite o raio do cícrulo:");
					System.out.print("Raio >> ");
					temp = scn.nextLine();
					try {
						valor1 = Double.parseDouble(temp);
						break;
					} catch (NumberFormatException err) {
						System.out.println("Valor inválido. Tente novamente\n\n");
					}
				}
				System.out.println("A área do círculo é: " + calculaAreaCircunferencia(valor1));
				break;
				
			case 'C':
				System.out.println("Área do triângulo\n");
				while (true) {
					System.out.println("Digite a base do triângulo:");
					System.out.print("Base >> ");
					temp = scn.nextLine();
					try {
						valor1 = Double.parseDouble(temp);
						break;
					} catch (NumberFormatException err) {
						System.out.println("Valor inválido. Tente novamente\n\n");
					}
				}
				while (true) {
					System.out.println("Digite a altura do triângulo:");
					System.out.print("Altura >> ");
					temp = scn.nextLine();
					try {
						valor2 = Double.parseDouble(temp);
						break;
					} catch (NumberFormatException err) {
						System.out.println("Valor inválido. Tente novamente\n\n");
					}
				}
				System.out.println("A área do triângulo é: " + calculaAreaTriangulo(valor1, valor2));
				break;
				
				default:
					System.out.println("Fim");
		}
		
		
	}

}
