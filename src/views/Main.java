package views;

import models.*;
import controllers.CorridaController;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Carro> carros = new ArrayList<>();

        System.out.println("🏁 Simulador de Corrida (sem JSON)");

        for (int i = 1; i <= 3; i++) {
            System.out.print("\nNome do piloto " + i + ": ");
            String nome = sc.nextLine();
            System.out.print("Pneu inicial (seco/chuva/neve): ");
            String pneu = sc.nextLine();
            carros.add(new Carro(nome, pneu));
        }

        System.out.println("\nEscolha o mapa:");
        System.out.println("1 - Deserto (seco)");
        System.out.println("2 - Floresta (chuva)");
        System.out.println("3 - Montanha (neve)");
        int opc = sc.nextInt();
        sc.nextLine();

        Mapa mapa = switch (opc) {
            case 2 -> new Mapa("Floresta", "chuva");
            case 3 -> new Mapa("Montanha", "neve");
            default -> new Mapa("Deserto", "seco");
        };

        CorridaController.iniciarCorrida(carros, mapa, sc);
    }
}