package corrida.controllers;

import corrida.models.Piloto;
import corrida.repositories.PilotoRepository;
import java.util.List;
import java.util.Optional;

public class PilotoController {
    private PilotoRepository repository = new PilotoRepository();

    public void criar(String nome, int equipeId) {
        List<Piloto> pilotos = repository.lerTodos();
        int novoId = pilotos.isEmpty() ? 1 : pilotos.stream().mapToInt(Piloto::getId).max().orElse(0) + 1;
        pilotos.add(new Piloto(novoId, nome, equipeId));
        repository.salvar(pilotos);
    }

    public List<Piloto> listar() {
        return repository.lerTodos();
    }

    /**
     * Edita um piloto existente.
     * @return true se o piloto foi encontrado e editado, false caso contrário.
     */
    public boolean editar(int id, String novoNome, int novaEquipeId) {
        List<Piloto> pilotos = repository.lerTodos();
        Optional<Piloto> pilotoOpt = pilotos.stream().filter(p -> p.getId() == id).findFirst();

        if (pilotoOpt.isPresent()) {
            Piloto piloto = pilotoOpt.get();
            piloto.setNome(novoNome);
            piloto.setEquipeId(novaEquipeId);
            repository.salvar(pilotos);
            return true;
        }
        return false;
    }

    /**
     * Deleta um piloto pelo ID.
     * @return true se o piloto foi encontrado e deletado, false caso contrário.
     */
    public boolean deletar(int id) {
        List<Piloto> pilotos = repository.lerTodos();
        boolean removed = pilotos.removeIf(p -> p.getId() == id);
        if (removed) {
            repository.salvar(pilotos);
        }
        return removed;
    }
}