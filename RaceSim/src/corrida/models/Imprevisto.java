package corrida.models;

import java.util.Random;

public class Imprevisto {

    private String descricao;
    private int modificadorDeDesempenho; // Pontos a serem somados ou subtraídos

    public Imprevisto(String descricao, int modificador) {
        this.descricao = descricao;
        this.modificadorDeDesempenho = modificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getModificadorDeDesempenho() {
        return modificadorDeDesempenho;
    }

    /**
     * Gera um evento aleatório para um piloto.
     * Há uma chance de 70% de nada acontecer.
     */
    public static Imprevisto gerar() {
        Random random = new Random();
        int chance = random.nextInt(100); // Gera um número de 0 a 99

        if (chance < 5) {
            // 5% de chance
            return new Imprevisto("💥 Pneu furado! Perda significativa de tempo.", -20);
        } else if (chance < 15) {
            // 10% de chance
            return new Imprevisto("🔧 Pequeno problema no motor. Desempenho reduzido.", -10);
        } else if (chance < 25) {
            // 10% de chance
            return new Imprevisto("🚀 Impulso de adrenalina! O piloto está voando na pista.", 15);
        } else if (chance < 35) {
            // 10% de chance
            return new Imprevisto("💧 Pista escorregadia em uma curva. Perdeu algumas posições.", -5);
        }
        
        // 65% de chance de não acontecer nada
        return new Imprevisto("Nenhum imprevisto.", 0);
    }
}