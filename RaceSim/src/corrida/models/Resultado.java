package corrida.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultado implements Serializable {
    private Veiculo veiculo;
    private List<Integer> desempenhosPorEtapa;
    private int desempenhoTotal;

    public Resultado(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.desempenhosPorEtapa = new ArrayList<>();
        this.desempenhoTotal = 0;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public int getDesempenhoTotal() {
        return desempenhoTotal;
    }

    public List<Integer> getDesempenhosPorEtapa() {
        return desempenhosPorEtapa;
    }
    
    
    public void adicionarDesempenhoEtapa(int desempenho) {
        this.desempenhosPorEtapa.add(desempenho);
        this.desempenhoTotal = this.desempenhosPorEtapa.stream().mapToInt(Integer::intValue).sum();
    }
}