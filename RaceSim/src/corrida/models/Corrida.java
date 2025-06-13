package corrida.models;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class Corrida implements Serializable {
    private Mapa mapa;
    private List<Veiculo> participantes;
    private Map<Veiculo, Integer> resultadoFinal;

    public Corrida(Mapa mapa, List<Veiculo> participantes){ 
        this.mapa = mapa; this.participantes = participantes; 
    }

    public Mapa getMapa(){ 
        return mapa; 
    }

    public List<Veiculo> getParticipantes(){ 
        return participantes; 
    }

    public void setResultadoFinal(Map<Veiculo, Integer> resultadoFinal){ 
        this.resultadoFinal = resultadoFinal; 
    }
}