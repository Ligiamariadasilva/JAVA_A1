package corrida.models;
import java.io.Serializable;

public class Mapa implements Serializable {
    private String nome; 
    private String climaInicial;


    public Mapa(String nome, String climaInicial){ 
        this.nome = nome; this.climaInicial = climaInicial; 
    }

    public String getNome(){ 
        return nome; 
    }

    public String getClimaInicial(){ 
        return climaInicial; 
    }
}