package corrida.models;
import java.io.Serializable;

public abstract class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    protected int id;
    protected String modelo;
    protected Piloto piloto;
    protected Pneu pneu;

    public Veiculo(int id, String modelo, Piloto piloto, Pneu pneu){ 
        this.id = id; 
        this.modelo = modelo; 
        this.piloto = piloto; 
        this.pneu = pneu; 
    }

    public abstract int calcularDesempenho(String clima);

    public int getId(){ 
        return id; 
    }

    public String getModelo(){ 
        return modelo; 
    }

    public Piloto getPiloto(){ 
        return piloto; 
    }

    public Pneu getPneu(){ 
        return pneu; 
    }

    public void setPneu(Pneu pneu){ 
        this.pneu = pneu; 
    }

    public void setPiloto(Piloto piloto){ 
        this.piloto = piloto; 
    }

    public void setModelo(String modelo){ 
        this.modelo = modelo; 
    }

    @Override
    public String toString() {
        String nomePiloto = (piloto != null) ? piloto.getNome() : "Sem piloto";
        return String.format("ID: %d, Modelo: %s (Piloto: %s, Pneu: %s)", id, modelo, nomePiloto, pneu.getTipo());
    }

    
}