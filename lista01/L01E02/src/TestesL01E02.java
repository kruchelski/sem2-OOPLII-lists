import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestesL01E02 {

	@Test
	void areaRetangulo1() {
		double largura = 2.5;
		double altura = 2.0;
		double resultado = calculoFormas.calculaAreaRetangulo(largura, altura);
		assertEquals(5.0, resultado, 0.0);		
	}
	
	@Test
	void areaRetangulo2() {
		String largura = "3.0";
		String altura = "2.0";
		double resultado = calculoFormas.calculaAreaRetangulo(largura, altura);
		assertEquals(6.0, resultado, 0.0);		
	}

	@Test
	void areaRetanguloErro1() {
		double largura = -2.5;
		double altura = -2.0;
		try {
			double resultado = calculoFormas.calculaAreaRetangulo(largura, altura);
			fail("Deveria ter chamado exceção");
		} catch(RuntimeException ex) {
			assertEquals(ex.getMessage(), "Valor negativo");
		}		
	}
	
	@Test
	void areaRetanguloErro2() {
		String largura = "-2.5";
		String altura = "-2.0";
		try {
			double resultado = calculoFormas.calculaAreaRetangulo(largura, altura);
			fail("Deveria ter chamado exceção");
		} catch(RuntimeException ex) {
			assertEquals(ex.getMessage(), "Valor negativo");
		}		
	}	
	
	@Test
	void areaCirc1() {
		double raio = 2.0;
		double resultado = calculoFormas.calculaAreaCircunferencia(raio);
		assertEquals(12.56, resultado, 0.0);		
	}
	
	@Test
	void areaCirc2() {
		String raio = "4.0";
		double resultado = calculoFormas.calculaAreaCircunferencia(raio);
		assertEquals(50.24, resultado, 0.0);		
	}
	
	@Test
	void areaCircErro1() {
		double raio = -2.0;
		try {
			double resultado = calculoFormas.calculaAreaCircunferencia(raio);
			fail("Deveria ter chamado exceção");
		} catch (RuntimeException ex) {
			assertEquals(ex.getMessage(),"Valor negativo");
		}
	}

	@Test
	void areaCircErro2() {
		String raio = "-2.0";
		try {
			double resultado = calculoFormas.calculaAreaCircunferencia(raio);
			fail("Deveria ter chamado exceção");
		} catch (RuntimeException ex) {
			assertEquals(ex.getMessage(),"Valor negativo");
		}
	}
	
	@Test
	void areaTriang1() {
		double base = 10.0;
		double altura = 10.0;
		double resultado = calculoFormas.calculaAreaTriangulo(base, altura);
		assertEquals(50.0, resultado, 0.0);		
	}

	@Test
	void areaTriang2() {
		String base = "30.0";
		String altura = "3.0";
		double resultado = calculoFormas.calculaAreaTriangulo(base, altura);
		assertEquals(45.0, resultado, 0.0);		
	}
	
	@Test
	void areaTriangErro1() {
		double base = -10.0;
		double altura = -10.0;
		try {
			double resultado = calculoFormas.calculaAreaTriangulo(base, altura);
			fail("Deveria ter chamado exceção");
		} catch(RuntimeException ex) {
			assertEquals(ex.getMessage(), "Valor negativo");
		}
	}
	
	@Test
	void areaTriangErro2() {
		String base = "-30.0";
		String altura = "-3.0";
		try {
			double resultado = calculoFormas.calculaAreaTriangulo(base, altura);
			fail("Deveria ter chamado exceção");
		} catch(RuntimeException ex) {
			assertEquals(ex.getMessage(), "Valor negativo");
		}
	}

}