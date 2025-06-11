package controllers;

import models.*;
import java.util.*;

// Controlador da corrida que executa a simulação completa
public class CorridaController {

    public static void iniciarCorrida(List<Carro> carros, Mapa mapa, Scanner sc) {
        String clima1 = mapa.getClimaInicial();

        System.out.println("\n🚗 Iniciando corrida no mapa " + mapa.getNome() + " (clima: " + clima1 + ")");
        for (Carro c : carros) {
            c.calcularDesempenho(clima1, true);
        }

        String clima2 = Clima.mudarClima(clima1);
        System.out.println("\n⚠️ Clima mudou para: " + clima2);

        for (Carro c : carros) {
            System.out.print("Deseja trocar o pneu do piloto " + c.getPiloto() + "? (s/n): ");
            String r = sc.nextLine();
            if (r.equalsIgnoreCase("s")) {
                System.out.print("Novo pneu (seco/chuva/neve): ");
                String novo = sc.nextLine();
                c.trocarPneuFinal(novo);
            }
        }

        for (Carro c : carros) {
            c.calcularDesempenho(clima2, false);
        }

        carros.sort((a, b) -> Integer.compare(b.getTotalDesempenho(), a.getTotalDesempenho()));

        System.out.println("\n🏁 Resultado final:");
        int posicao = 1;
        for (Carro c : carros) {
            int total = c.getTotalDesempenho();
            double perc1 = (c.getDesempenhoParcial1() * 100.0) / total;
            double perc2 = (c.getDesempenhoParcial2() * 100.0) / total;

            System.out.printf("%dº lugar: %s%n", posicao++, c.getPiloto());
            System.out.printf("  - Total: %d pontos%n", total);
            System.out.printf("  - 1ª metade: %d pontos (%.1f%%)%n", c.getDesempenhoParcial1(), perc1);
            System.out.printf("  - 2ª metade: %d pontos (%.1f%%)%n", c.getDesempenhoParcial2(), perc2);
            System.out.println("  - Pneus usados: " + c.getPneuInicial() + " ➡ " + c.getPneuFinal());

            // Sugestão
            if (!c.getPneuInicial().equalsIgnoreCase(clima1) && !c.getPneuFinal().equalsIgnoreCase(clima2)) {
                System.out.println("  - Sugestão: pneus incorretos nas duas partes.");
            } else if (!c.getPneuInicial().equalsIgnoreCase(clima1)) {
                System.out.println("  - Sugestão: use pneu ideal para " + clima1 + " na 1ª metade.");
            } else if (!c.getPneuFinal().equalsIgnoreCase(clima2)) {
                System.out.println("  - Sugestão: troque para pneu de " + clima2 + " na 2ª metade.");
            } else {
                System.out.println("  - Estratégia de pneus perfeita!");
            }
        }
    }
}