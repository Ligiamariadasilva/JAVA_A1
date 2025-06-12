package corrida.models;
import java.io.Serializable;


public class Piloto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id; private String nome; private int equipeId;

    public Piloto(int id, String nome, int equipeId){ 
        this.id = id; 
        this.nome = nome; 
        this.equipeId = equipeId; 
    }

    public int getId(){ 
        return id; 
    }

    public String getNome(){ 
        return nome; 
    }

    public int getEquipeId(){ 
        return equipeId; 
    }

    public void setNome(String nome){ 
        this.nome = nome; 
    }

    public void setEquipeId(int equipeId){ 
        this.equipeId = equipeId; 
    }

    @Override 
    public String toString(){ 
        return "Piloto ID: " + id + ", Nome: " + nome + ", Equipe ID: " + equipeId; 
    }
    
}