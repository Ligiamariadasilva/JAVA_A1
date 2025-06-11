package models;

// Representa um carro e o desempenho do piloto na corrida
public class Carro {
    private String piloto;
    private String pneuInicial;
    private String pneuFinal;
    private int desempenhoParcial1;
    private int desempenhoParcial2;

    public Carro(String piloto, String pneuInicial) {
        this.piloto = piloto;
        this.pneuInicial = pneuInicial;
        this.pneuFinal = pneuInicial;
    }

    public String getPiloto() {
        return piloto;
    }

    public String getPneuInicial() {
        return pneuInicial;
    }

    public String getPneuFinal() {
        return pneuFinal;
    }

    public void trocarPneuFinal(String novoPneu) {
        this.pneuFinal = novoPneu;
    }

    public void calcularDesempenho(String clima, boolean primeiraParte) {
        int desempenho = (primeiraParte ? pneuInicial : pneuFinal).equalsIgnoreCase(clima)
            ? (int)(Math.random() * 11) + 45
            : (int)(Math.random() * 11) + 25;

        if (primeiraParte)
            desempenhoParcial1 = desempenho;
        else
            desempenhoParcial2 = desempenho;
    }

    public int getDesempenhoParcial1() {
        return desempenhoParcial1;
    }

    public int getDesempenhoParcial2() {
        return desempenhoParcial2;
    }

    public int getTotalDesempenho() {
        return desempenhoParcial1 + desempenhoParcial2;
    }
}