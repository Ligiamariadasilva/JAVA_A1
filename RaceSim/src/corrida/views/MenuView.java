package corrida.views;

import corrida.controllers.*;
import corrida.exceptions.EntradaInvalidaException;
import corrida.models.*;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private final Scanner sc;
    private final EquipeController equipeCtrl = new EquipeController();
    private final PilotoController pilotoCtrl = new PilotoController();
    private final VeiculoController veiculoCtrl = new VeiculoController();

    public MenuView(Scanner sc) { this.sc = sc; }

    private int lerInt() throws EntradaInvalidaException {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Entrada deve ser um número inteiro.");
        }
    }

    // --- MENU EQUIPES (CRUD COMPLETO) ---
    public void menuGerenciarEquipes() {
        while (true) {
            System.out.println("\n--- Gerenciar Equipes ---");
            System.out.println("1. Criar | 2. Listar | 3. Editar | 4. Deletar | 0. Voltar");
            System.out.print("Escolha: ");
            try {
                int opcao = lerInt();
                if (opcao == 0) break;
                switch (opcao) {
                    case 1 -> { System.out.print("Nome da equipe: "); equipeCtrl.criar(sc.nextLine()); System.out.println("Equipe criada!"); }
                    case 2 -> equipeCtrl.listar().forEach(System.out::println);
                    case 3 -> { System.out.print("ID da equipe a editar: "); int id = lerInt(); System.out.print("Novo nome: "); String nome = sc.nextLine(); if(equipeCtrl.editar(id, nome)) System.out.println("Editado com sucesso!"); else System.out.println("ID não encontrado."); }
                    case 4 -> { System.out.print("ID da equipe a deletar: "); if(equipeCtrl.deletar(lerInt())) System.out.println("Deletado com sucesso!"); else System.out.println("ID não encontrado."); }
                    default -> System.err.println("Opção inválida.");
                }
            } catch (EntradaInvalidaException e) { System.err.println("Erro: " + e.getMessage()); }
        }
    }

    // --- MENU PILOTOS (CRUD COMPLETO) ---
    public void menuGerenciarPilotos() {
        while (true) {
            System.out.println("\n--- Gerenciar Pilotos ---");
            System.out.println("1. Criar | 2. Listar | 3. Editar | 4. Deletar | 0. Voltar");
            System.out.print("Escolha: ");
            try {
                int opcao = lerInt();
                if (opcao == 0) break;
                switch (opcao) {
                    case 1 -> criarPiloto();
                    case 2 -> pilotoCtrl.listar().forEach(System.out::println);
                    case 3 -> editarPiloto();
                    case 4 -> deletarPiloto();
                    default -> System.err.println("Opção inválida.");
                }
            } catch (EntradaInvalidaException e) { System.err.println("Erro: " + e.getMessage()); }
        }
    }

    private void criarPiloto() throws EntradaInvalidaException {
        System.out.print("Nome do piloto: ");
        String nome = sc.nextLine();
        System.out.print("ID da equipe: ");
        int equipeId = lerInt();
        pilotoCtrl.criar(nome, equipeId);
        System.out.println("Piloto criado!");
    }

    private void editarPiloto() throws EntradaInvalidaException {
        System.out.print("ID do piloto a editar: ");
        int id = lerInt();
        System.out.print("Novo nome do piloto: ");
        String novoNome = sc.nextLine();
        System.out.print("Novo ID da equipe: ");
        int novaEquipeId = lerInt();
        if (pilotoCtrl.editar(id, novoNome, novaEquipeId)) {
            System.out.println("Piloto editado com sucesso!");
        } else {
            System.out.println("Piloto não encontrado.");
        }
    }

    private void deletarPiloto() throws EntradaInvalidaException {
        System.out.print("ID do piloto a deletar: ");
        int id = lerInt();
        if (pilotoCtrl.deletar(id)) {
            System.out.println("Piloto deletado com sucesso!");
        } else {
            System.out.println("Piloto não encontrado.");
        }
    }

    // --- MENU VEÍCULOS (CRUD COMPLETO) ---
    public void menuGerenciarVeiculos() {
        while (true) {
            System.out.println("\n--- Gerenciar Veículos ---");
            System.out.println("1. Criar | 2. Listar | 3. Editar | 4. Deletar | 0. Voltar");
            System.out.print("Escolha: ");
            try {
                int opcao = lerInt();
                if (opcao == 0) break;
                switch (opcao) {
                    case 1 -> criarVeiculo();
                    case 2 -> listarVeiculos();
                    case 3 -> editarVeiculo();
                    case 4 -> deletarVeiculo();
                    default -> System.err.println("Opção inválida.");
                }
            } catch (EntradaInvalidaException e) { System.err.println("Erro: " + e.getMessage()); }
        }
    }

    private void criarVeiculo() throws EntradaInvalidaException {
        System.out.print("Tipo do veículo (carro/moto): ");
        String tipo = sc.nextLine();
        System.out.print("Modelo do veículo: ");
        String modelo = sc.nextLine();
        System.out.println("Selecione um piloto para o veículo:");
        List<Piloto> pilotos = pilotoCtrl.listar();
        if (pilotos.isEmpty()) { System.out.println("Crie um piloto primeiro."); return; }
        pilotos.forEach(System.out::println);
        System.out.print("Digite o ID do piloto escolhido: ");
        int pilotoId = lerInt();
        Piloto pilotoEscolhido = pilotos.stream().filter(p -> p.getId() == pilotoId).findFirst().orElse(null);
        if (pilotoEscolhido == null) { System.err.println("Piloto com ID " + pilotoId + " não encontrado."); return; }
        System.out.print("Tipo de pneu inicial (seco/chuva/neve): ");
        String tipoPneu = sc.nextLine();
        Pneu pneu = switch (tipoPneu.toLowerCase()) {
            case "chuva" -> new PneuChuva();
            case "neve" -> new PneuNeve();
            default -> new PneuSeco();
        };
        veiculoCtrl.criar(tipo, modelo, pilotoEscolhido, pneu);
        System.out.println("Veículo criado com sucesso!");
    }
    
    private void listarVeiculos() {
        System.out.println("\n--- Lista de Veículos ---");
        List<Veiculo> veiculos = veiculoCtrl.listar();
        if (veiculos.isEmpty()) { System.out.println("Nenhum veículo cadastrado."); } 
        else { veiculos.forEach(System.out::println); }
    }

    private void editarVeiculo() throws EntradaInvalidaException {
        System.out.print("ID do veículo a editar: ");
        int id = lerInt();
        System.out.print("Novo modelo do veículo: ");
        String novoModelo = sc.nextLine();
        if (veiculoCtrl.editar(id, novoModelo)) {
            System.out.println("Veículo editado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private void deletarVeiculo() throws EntradaInvalidaException {
        System.out.print("ID do veículo a deletar: ");
        int id = lerInt();
        if (veiculoCtrl.deletar(id)) {
            System.out.println("Veículo deletado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
    
    // --- MENU CORRIDA ---
    public void menuIniciarCorrida() {
        System.out.println("\n--- Iniciar Corrida ---");
        List<Veiculo> veiculos = veiculoCtrl.listar();
        if (veiculos.size() < 2) { // Corrida precisa de pelo menos 2 veículos
            System.out.println("Cadastre pelo menos 2 veículos para iniciar uma corrida!");
            return;
        }
        CorridaController.iniciarCorrida(veiculos, new Mapa("Interlagos", "seco"), sc);
    }
}