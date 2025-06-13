package corrida.models;

public class Moto extends Veiculo {

    public Moto(int id, String modelo, Piloto piloto, Pneu pneu){ 
        super(id, modelo, piloto, pneu); 
    }

    @Override 
    public int calcularDesempenho(String clima){ 

        int base = 30; 
        
        if (pneu.getTipo().equalsIgnoreCase(clima)){ 
            return base + (int)(Math.random() * 21) + 30; 
        } else { 
            return base + (int)(Math.random() * 11); 
        } 
    }
}