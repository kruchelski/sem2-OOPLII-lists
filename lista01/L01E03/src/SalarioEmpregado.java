import java.util.*;
public class SalarioEmpregado {

	public static double calculoBruto(double salHora, double hora) {
		return salHora * hora;
	}

	public static double calculoLiquido(double bruto, double inss) {
		return bruto - ((inss / 100.0) * bruto);
	}

	public static void verificaValores(double salHora, double hora, double inss) {
		if (salHora < 0.0 || hora < 0.0 || inss < 0.0) {
			throw new RuntimeException("Valor inválido");
		} else System.out.println("Valores OK");
	}

	public static void verificaValores(String salHora, String hora, String inss) {
		double sal, h, i;
		try {
			sal = Double.parseDouble(salHora); 
		} catch(NumberFormatException e) {
			throw new RuntimeException("Valor inválido");
		}
		try {
			h = Double.parseDouble(hora);
		} catch(NumberFormatException e) {
			throw new RuntimeException("Valor inválido");
		}
		try {
			i = Double.parseDouble(inss);
		} catch(NumberFormatException e) {
			throw new RuntimeException("Valor inválido");
		}
		if (sal < 0.0 || h < 0.0 || i < 0.0) {
			throw new RuntimeException("Valor inválido");
		} else System.out.println("Valores OK");
		
	}
	
	public static String extratoSalario(double salHora, double hora, double inss) {
		String msg = "Extrato de salário\n\n";
		double bruto = calculoBruto(salHora, hora);
		double liquido = calculoLiquido(bruto, inss);
		msg += "Valor Hora: R$" + salHora + "\n\n";
		msg += "Horas trabalhadas: " + hora + " horas\n\n";
		msg += "Salário bruto: R$" + bruto + "\n\n";
		msg += "Salário líquido: R$" + liquido;
		
		return msg;
	}
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		String salTemp, horaTemp, inssTemp;
		salTemp = "";
		horaTemp = "";
		inssTemp = "";
		System.out.println("Cálculo de salário de empregado\n--------------------------------");
		while (true) {
				System.out.println("Digite o valor do salário por hora:");
				System.out.print("Salário por hora >> ");
				salTemp = scn.nextLine();
				System.out.println("Digite a quantidade de horas trabalhadas no mês:");
				System.out.print("Quantidade de horas >> ");
				horaTemp = scn.nextLine();
				System.out.println("Digite o valor em porcentagem da contribuição do INSS:");
				System.out.print("INSS (Porcentagem) >> ");
				inssTemp = scn.nextLine();
			try {
				verificaValores(salTemp, horaTemp, inssTemp);
				break;
			} catch (RuntimeException ex) {
				System.out.println(ex.getMessage());
			}
		}
		double salHora = Double.parseDouble(salTemp);
		double hora = Double.parseDouble(horaTemp);
		double inss = Double.parseDouble(inssTemp);
	
		
		System.out.println("Calculando salários...\n\n");
		System.out.println(extratoSalario(salHora, hora, inss));	
	}
	

}
