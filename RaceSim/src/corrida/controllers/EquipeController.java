package corrida.controllers;
import corrida.models.Equipe;
import corrida.repositories.EquipeRepository;
import java.util.List;
import java.util.Optional;

public class EquipeController {
    private EquipeRepository repository = new EquipeRepository();
    public void criar(String nome) {
        List<Equipe> equipes = repository.lerTodos();
        int novoId = equipes.isEmpty() ? 1 : equipes.get(equipes.size() - 1).getId() + 1;
        equipes.add(new Equipe(novoId, nome));
        repository.salvar(equipes);
    }

    public List<Equipe> listar() {
        return repository.lerTodos();
    }

    public boolean editar(int id, String novoNome) {
        List<Equipe> equipes = repository.lerTodos();
        Optional<Equipe> equipeOpt = equipes.stream().filter(e -> e.getId() == id).findFirst();
        if (equipeOpt.isPresent()) { equipeOpt.get().setNome(novoNome); repository.salvar(equipes); return true; }
        return false;
    }
    public boolean deletar(int id) {
        List<Equipe> equipes = repository.lerTodos();
        boolean removed = equipes.removeIf(e -> e.getId() == id);
        if (removed) repository.salvar(equipes);
        return removed;
    }

    public Optional<Equipe> buscarPorId(int id) {
        return repository.lerTodos().stream()
                .filter(equipe -> equipe.getId() == id)
                .findFirst();
    }
}