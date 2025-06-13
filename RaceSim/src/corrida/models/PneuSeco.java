package corrida.models;

public class PneuSeco extends Pneu { 

    @Override 
    public String getTipo(){ 
        return "seco"; 
    } 

    @Override 
    public String toString(){ 
        return "Pista Seca"; 
    } 
}