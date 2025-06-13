package corrida.models;

public class PneuNeve extends Pneu { 

    @Override 
    public String getTipo(){ 
        return "neve"; 
    } 

    @Override 
    public String toString(){ 
        return "Neve"; 
    } 
}