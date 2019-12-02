package L04E03;

public class SomaArea {
    public <T extends Superficie> Double calculaArea(T array[]) {
        double soma = 0.0;
        for (T obj : array) {
            soma += obj.area();
        }
        return soma;
    }
}
