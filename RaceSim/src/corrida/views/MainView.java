package corrida.views;

import corrida.services.LogService;
import java.util.Scanner;

public class MainView {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            MenuView menu = new MenuView(sc);
            LogService.registrarLog("Sistema iniciado.");
            
            while (true) {
                System.out.println("\n--- MENU PRINCIPAL ---");
                System.out.println("1. Gerenciar Equipes");
                System.out.println("2. Gerenciar Pilotos");
                System.out.println("3. Gerenciar Veículos");
                System.out.println("4. Iniciar Corrida");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                String opcao = sc.nextLine();

                if ("0".equals(opcao)) {
                    break;
                }
                
                
                switch (opcao) {
                    case "1" -> menu.menuGerenciarEquipes();
                    case "2" -> menu.menuGerenciarPilotos();
                    case "3" -> menu.menuGerenciarVeiculos();
                    case "4" -> menu.menuIniciarCorrida();
                    default -> System.err.println("Opção inválida.");
                }
            }
            
            LogService.registrarLog("Sistema finalizado.");
            System.out.println("Até logo!");
        }
    }
}