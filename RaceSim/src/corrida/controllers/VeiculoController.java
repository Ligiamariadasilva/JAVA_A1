package corrida.controllers;

import corrida.models.*;
import corrida.repositories.VeiculoRepository;
import java.util.List;
import java.util.Optional;

public class VeiculoController {
    private VeiculoRepository repository = new VeiculoRepository();

    public void criar(String tipo, String modelo, Piloto piloto, Pneu pneu) {
        List<Veiculo> veiculos = repository.lerTodos();
        int novoId = veiculos.isEmpty() ? 1 : veiculos.stream().mapToInt(Veiculo::getId).max().orElse(0) + 1;
        Veiculo veiculo = tipo.equalsIgnoreCase("carro") ? new Carro(novoId, modelo, piloto, pneu) : new Moto(novoId, modelo, piloto, pneu);
        veiculos.add(veiculo);
        repository.salvar(veiculos);
    }

    public List<Veiculo> listar() {
        return repository.lerTodos();
    }
    
    /**
     * Edita o modelo de um veículo existente.
     * @return true se o veículo foi encontrado e editado, false caso contrário.
     */
    public boolean editar(int id, String novoModelo) {
        List<Veiculo> veiculos = repository.lerTodos();
        // A edição de objetos mais complexos como piloto ou pneu exigiria uma lógica mais elaborada.
        // Por simplicidade, vamos permitir apenas a edição do modelo.
        Optional<Veiculo> veiculoOpt = veiculos.stream().filter(v -> v.getId() == id).findFirst();
        
        if(veiculoOpt.isPresent() && veiculoOpt.get() instanceof Carro) {
             ((Carro) veiculoOpt.get()).setModelo(novoModelo); // Precisamos de um setModelo em Veiculo
             repository.salvar(veiculos);
             return true;
        } else if (veiculoOpt.isPresent() && veiculoOpt.get() instanceof Moto) {
            ((Moto) veiculoOpt.get()).setModelo(novoModelo); // Precisamos de um setModelo em Veiculo
            repository.salvar(veiculos);
            return true;
        }
        
        return false;
    }

    /**
     * Deleta um veículo pelo ID.
     * @return true se o veículo foi encontrado e deletado, false caso contrário.
     */
    public boolean deletar(int id) {
        List<Veiculo> veiculos = repository.lerTodos();
        boolean removed = veiculos.removeIf(v -> v.getId() == id);
        if (removed) {
            repository.salvar(veiculos);
        }
        return removed;
    }
}