import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestesL01E03 {

	@Test
	void setValoresErro1() {
		double salHora = -10.0;
		double hora = -200.0;
		double inss = -10.0;
		try {
			SalarioEmpregado.verificaValores(salHora, hora, inss);
			fail("Deveria ter pegado exceção");
		} catch (RuntimeException ex) {
			assertEquals("Valor inválido", ex.getMessage());
		}	
	}

	@Test
	void setValoresErro2() {
		String salHora = "-10.0";
		String hora = "-200.0";
		String inss = "-10.0";
		try {
			SalarioEmpregado.verificaValores(salHora, hora, inss);
			fail("Deveria ter pegado exceção");
		} catch (RuntimeException ex) {
			assertEquals("Valor inválido",ex.getMessage());
		}	
	}
	
	@Test
	void calculoBruto() {
		double salHora = 100.0;
		double hora = 120.0;
		double bruto = SalarioEmpregado.calculoBruto(salHora, hora);
		assertEquals(12000.0, bruto, 0.0);
	}
	
	@Test
	void calculoLiquido() {
		double salHora = 100.0;
		double hora = 120.0;
		double inss = 10.0;
		double bruto = SalarioEmpregado.calculoBruto(salHora, hora);
		double liquido = SalarioEmpregado.calculoLiquido(bruto, inss);
		assertEquals(10800.0, liquido, 0.0);
	}
	
	@Test
	void extratoSalarioTeste() {
		double salHora = 100.0;
		double hora = 120.0;
		double inss = 10.0;
		String resultado = SalarioEmpregado.extratoSalario(salHora, hora, inss);
		assertEquals("Extrato de salário\n\nValor Hora: R$100.0\n\nHoras trabalhadas: 120.0 horas\n\nSalário bruto: R$12000.0\n\nSalário líquido: R$10800.0", resultado);
	}

}

