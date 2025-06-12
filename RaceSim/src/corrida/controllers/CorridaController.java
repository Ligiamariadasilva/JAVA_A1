package corrida.controllers;

import corrida.models.*;
import corrida.services.LogService;

import java.util.*;
import java.util.stream.Collectors;

public class CorridaController {

    private static final int NUMERO_DE_ETAPAS = 4;

    public static void iniciarCorrida(List<Veiculo> veiculos, Mapa mapa, Scanner sc) {
        LogService.registrarLog("Iniciando nova simulação de corrida.");
        System.out.println("\n=============================================");
        System.out.println("🏁 A CORRIDA VAI COMEÇAR! 🏁");
        System.out.println("Mapa: " + mapa.getNome() + " | Etapas: " + NUMERO_DE_ETAPAS);
        System.out.println("=============================================");

        String climaAtual = mapa.getClimaInicial();
        Map<Veiculo, Resultado> resultados = new HashMap<>();
        veiculos.forEach(v -> resultados.put(v, new Resultado(v)));

        // Loop principal da corrida, uma iteração por etapa
        for (int i = 1; i <= NUMERO_DE_ETAPAS; i++) {
            System.out.println("\n--- ETAPA " + i + " / " + NUMERO_DE_ETAPAS + " ---");
            System.out.println("☀️  Clima atual: " + climaAtual.toUpperCase());

            // Calcula o desempenho de cada veículo na etapa
            for (Veiculo v : veiculos) {
                // Gera um imprevisto (ou não)
                Imprevisto imprevisto = (i > 1) ? Imprevisto.gerar() : new Imprevisto("Largada limpa!", 0);
                
                int desempenhoBase = v.calcularDesempenho(climaAtual);
                int desempenhoFinalEtapa = desempenhoBase + imprevisto.getModificadorDeDesempenho();

                System.out.printf("Piloto %s (Pneu - %s): %d pontos", v.getPiloto().getNome(), v.getPneu().getTipo(), desempenhoBase);
                if (imprevisto.getModificadorDeDesempenho() != 0) {
                    System.out.printf(" | Imprevisto: %s (%+d pts)", imprevisto.getDescricao(), imprevisto.getModificadorDeDesempenho());
                }
                System.out.println();
                
                resultados.get(v).adicionarDesempenhoEtapa(desempenhoFinalEtapa);
            }

            // Exibe placar parcial
            exibirPlacarParcial(resultados);

            // Se não for a última etapa, prepara para a próxima
            if (i < NUMERO_DE_ETAPAS) {
                System.out.println("\n--- PIT STOP DA ETAPA " + i + " ---");

                // Troca de pneus
                GaragemController garagem = new GaragemController();
                for (Veiculo v : veiculos) {
                    System.out.print("Deseja trocar o pneu de " + v.getPiloto().getNome() + "? (s/n): ");
                    if (sc.nextLine().equalsIgnoreCase("s")) {
                        System.out.print("Novo pneu (seco/chuva/neve): ");
                        String tipoPneu = sc.nextLine();
                        Pneu novoPneu = switch (tipoPneu.toLowerCase()) {
                            case "chuva" -> new PneuChuva();
                            case "neve" -> new PneuNeve();
                            default -> new PneuSeco();
                        };
                        garagem.trocarPneu(v, novoPneu);
                    }
                }
                // Mudança de clima para a próxima etapa
                climaAtual = Clima.mudarClima(climaAtual);
                LogService.registrarLog("Clima mudou para " + climaAtual + " para a etapa " + (i + 1));
            }
        }

        // Fim da corrida
        System.out.println("\n=============================================");
        System.out.println("🏆🏁 RESULTADO FINAL DA CORRIDA 🏁🏆");
        exibirPlacarFinal(resultados);
        System.out.println("=============================================");
        LogService.registrarLog("Simulação de corrida finalizada.");
    }

    private static void exibirPlacarParcial(Map<Veiculo, Resultado> resultados) {
        System.out.println("\n--- Placar Parcial ---");
        List<Resultado> placar = new ArrayList<>(resultados.values());
        placar.sort((r1, r2) -> Integer.compare(r2.getDesempenhoTotal(), r1.getDesempenhoTotal()));
        int pos = 1;
        for (Resultado r : placar) {
            System.out.printf("%dº: %s - %d pontos\n", pos++, r.getVeiculo().getPiloto().getNome(), r.getDesempenhoTotal());
        }
    }

    private static void exibirPlacarFinal(Map<Veiculo, Resultado> resultados) {
        List<Resultado> placarFinal = new ArrayList<>(resultados.values());
        placarFinal.sort((r1, r2) -> Integer.compare(r2.getDesempenhoTotal(), r1.getDesempenhoTotal()));

        int posicao = 1;
        for (Resultado r : placarFinal) {
            String detalhesEtapas = r.getDesempenhosPorEtapa().stream()
                                     .map(String::valueOf)
                                     .collect(Collectors.joining(" + "));

            System.out.printf("%dº Lugar: %s (%s) - TOTAL: %d pontos\n",
                    posicao++,
                    r.getVeiculo().getPiloto().getNome(),
                    r.getVeiculo().getModelo(),
                    r.getDesempenhoTotal());
            System.out.printf("   (Detalhes: %s)\n", detalhesEtapas);
        }
    }
}