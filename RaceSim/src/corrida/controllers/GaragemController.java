package corrida.controllers;
import corrida.interfaces.Oficina;
import corrida.models.*;
import corrida.services.LogService;


public class GaragemController implements Oficina {
    @Override public void trocarPneu(Veiculo veiculo, Pneu novoPneu) {
        veiculo.setPneu(novoPneu);
        LogService.registrarLog("Pneu do veículo " + veiculo.getId() + " trocado para " + novoPneu.getTipo());
        System.out.println("Piloto " + veiculo.getPiloto().getNome() + " trocou para pneu de " + novoPneu.getTipo());
    }
    @Override public void realizarManutencao(Veiculo veiculo) {
        LogService.registrarLog("Manutenção realizada no veículo " + veiculo.getId());
        System.out.println("Manutenção geral concluída para o veículo " + veiculo.getModelo());
    }
}