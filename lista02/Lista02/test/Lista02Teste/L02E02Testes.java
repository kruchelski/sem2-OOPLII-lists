package Lista02Teste;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import L02E02.*; //Importando o package dos exercícios 2

public class L02E02Testes {
    
    public L02E02Testes() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {    
    }

    //==== Testes UsaForma =====
    
    @Test
    public void conversor1() { // número positivo
        String snum = "3.0";
        double resultado = UsaForma.conversor(snum);
        assertEquals(3.0, resultado, 0.0);      
    }
    
    @Test
    public void conversor2() { // número negativo
        String snum = "-44.0";
        double resultado = UsaForma.conversor(snum);
        assertEquals(-44.0, resultado, 0.0);      
    }

    @Test
    public void conversor3() { // número sem casa decimal
        String snum = "6";
        double resultado = UsaForma.conversor(snum);
        assertEquals(6.0, resultado, 0.0);      
    }

    @Test
    public void conversor4() { // pegar NumberFormatException
        String snum = "oloco";
        String esperado = "Valor não é um número válido.";
        try{
            double resultado = UsaForma.conversor(snum);
            assertEquals(0.0, resultado, 0.0); 
            fail("Deveria pegar uma exceção");
        } catch (RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }     
    }
    
    @Test
    public void conversorInt1() { // Mostra a conversao corretamente (numero positivo)
        String snum = "3";
        int resultado = UsaForma.conversorInt(snum);
        assertEquals(3, resultado);
    }
    
    @Test
    public void conversorInt2() { // Mostra a conversao corretament (numero negativo)
        String snum = "-10";
        int resultado = UsaForma.conversorInt(snum);
        assertEquals(-10, resultado);
    }
    
    @Test
    public void conversorInt3() { // Mostra mensagem de conversão errada
        String snum = "eita";
        String esperado = "Valor não é um número válido";
        int resultado;
        try {
            resultado = UsaForma.conversorInt(snum);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }
    }
    
    //===== Teste Circunferencia ======
    
    @Test
    public void circConstrutor1() { // Mostrar msg de falha por numero negativo
        Circunferencia circ;
        double num = -5.0;
        String esperado = "Valor inválido - Número negativo";
        try {
            circ = new Circunferencia(num);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }

    @Test
    public void circArea1() { // Mostrará área corretamente
        Circunferencia circ;
        double num = 5.0;
        circ = new Circunferencia(num);
        double resultado = circ.area();
        assertEquals((Math.PI * (Math.pow(num, 2))), resultado, 0.0);
    }
    
    @Test
    public void circPrint1() { // Verifica a string do printDados da circunferência
        double raio = 4.0;
        Circunferencia circ = new Circunferencia(raio);
        double valor = circ.area();
        String resultado = circ.printDados();
        String esperado = "Forma: Circunferência - Área: " + valor + "\n";
        assertEquals(resultado, esperado);
    }
    
    //====== Teste Retângulo =========
    
    @Test
    public void retConstrutor1() { // Mostrar msg de falha por largura negativa
        Retangulo ret;
        double larg = -5.0;
        double alt = 10.0;
        String esperado = "Largura inválida - Número(s) negativo(s)\n";
        try {
            ret = new Retangulo(larg, alt);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }

    @Test
    public void retConstrutor2() { // Mostrar msg de falha por altura negativa
        Retangulo ret;
        double larg = 5.0;
        double alt = -10.0;
        String esperado = "Altura inválida - Número(s) negativo(s)\n";
        try {
            ret = new Retangulo(larg, alt);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }
    
    @Test
    public void retConstrutor3() { // Mostrar msg de falha por altura e largura negativa
        Retangulo ret;
        double larg = -5.0;
        double alt = -10.0;
        String esperado = "Largura inválida - Altura inválida - Número(s) negativo(s)\n";
        try {
            ret = new Retangulo(larg, alt);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }
    
    @Test
    public void retArea1() { // Mostra a área do retângulo corretamente
        Retangulo ret;
        double larg = 10.0;
        double alt = 5.0;
        ret = new Retangulo(larg, alt);
        double resultado = ret.area();
        assertEquals(50.0, resultado, 0.0);
    }

    @Test
    public void retArea2() { // Mostra a área do retângulo corretamente (com números inteiros);
        Retangulo ret;
        double larg = 30;
        double alt = 50;
        ret = new Retangulo(larg, alt);
        double resultado = ret.area();
        assertEquals(1500.0, resultado, 0.0);
    }
    
    @Test
    public void retPerim1() { // Mostra o perímetro do retângulo corretamente
        Retangulo ret;
        double larg = 10.0;
        double alt = 5.0;
        ret = new Retangulo(larg, alt);
        double resultado = ret.perimetro();
        assertEquals(30.0, resultado, 0.0);  
    }
    
    @Test
    public void retPerim2() { // Mostra o perímetro do retângulo corretamente (com números inteiros)
        Retangulo ret;
        double larg = 30;
        double alt = 15;
        ret = new Retangulo(larg, alt);
        double resultado = ret.perimetro();
        assertEquals(90.0, resultado, 0.0);  
    }
    
    @Test
    public void retPrint1() { // String do print do retângulo
        double larg = 10.0;
        double alt = 20.0;
        Retangulo ret = new Retangulo(larg, alt);
        String resultado = ret.printDados();
        String esperado = "Forma: Retângulo - Área: 200.0 - Perímetro: 60.0\n";
        assertEquals(resultado, esperado);
    }
    
    //====== Testes triângulo =======
    @Test
    public void triConstrutor1() { // Mostrar msg de falha por base negativa
        Triangulo tri;
        double bas = -5.0;
        double alt = 10.0;
        String esperado = "Base inválida - Número(s) negativo(s)\n";
        try {
            tri = new Triangulo(bas, alt);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }
    
    @Test
    public void triConstrutor2() { // Mostrar msg de falha por altura negativa
        Triangulo tri;
        double bas = 5.0;
        double alt = -10.0;
        String esperado = "Altura inválida - Número(s) negativo(s)\n";
        try {
            tri = new Triangulo(bas, alt);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }
    
    @Test
    public void triConstrutor3() { // Mostrar msg de falha por altura negativa
        Triangulo tri;
        double bas = -5.0;
        double alt = -10.0;
        String esperado = "Base inválida - Altura inválida - Número(s) negativo(s)\n";
        try {
            tri = new Triangulo(bas, alt);
            fail("Deveria ter pego uma exceção");
        } catch(RuntimeException re) {
            assertEquals(re.getMessage(), esperado);
        }   
    }
    
    @Test
    public void triArea1() { // Mostrar o restulado certo
        Triangulo tri;
        double bas = 30.0;
        double alt = 10.0;
        tri = new Triangulo(bas, alt);
        double resultado = tri.area();
        assertEquals(150.0, resultado, 0.0);
    }
    
    @Test
    public void triArea2() { // Mostrar o restulado certo (número inteiro)
        Triangulo tri;
        double bas = 100;
        double alt = 20;
        tri = new Triangulo(bas, alt);
        double resultado = tri.area();
        assertEquals(1000.0, resultado, 0.0);
    }
    
    @Test
    public void triPrint1() { // Mostrar o print para triângulo
        double bas = 10.0;
        double alt = 30.0;
        Triangulo tri = new Triangulo(bas, alt);
        String resultado = tri.printDados();
        String esperado = "Forma: Triângulo - Área: 150.0\n";
        assertEquals(resultado, esperado);
    }
}