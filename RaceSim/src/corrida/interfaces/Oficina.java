package corrida.interfaces;

import corrida.models.Pneu;
import corrida.models.Veiculo;

public interface Oficina {
    void trocarPneu(Veiculo veiculo, Pneu novoPneu);
    void realizarManutencao(Veiculo veiculo);
}