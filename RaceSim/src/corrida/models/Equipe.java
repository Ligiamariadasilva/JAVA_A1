package corrida.models;
import java.io.Serializable;


public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id; private String nome;

    public Equipe(int id, String nome){ 
        this.id = id; this.nome = nome; 
    }

    public int getId(){ 
        return id; 
    }

    public String getNome(){ 
        return nome; 
    }

    public void setNome(String nome){ 
        this.nome = nome; 
    }

    @Override 
    public String toString(){ 
        return "ID: " + id + ", Nome: " + nome; 
    }
}