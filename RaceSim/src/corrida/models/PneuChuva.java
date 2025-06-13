package corrida.models;

public class PneuChuva extends Pneu {
    
    @Override 
    public String getTipo(){ 
        return "chuva"; 
    } 

    @Override 
    public String toString(){ 
        return "Chuva"; 
    } 
}