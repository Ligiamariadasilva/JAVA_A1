package corrida.models;
public class Carro extends Veiculo {
    public Carro(int id, String modelo, Piloto piloto, Pneu pneu){ 
        super(id, modelo, piloto, pneu); 
    }


    @Override 
    public int calcularDesempenho(String clima){ 

        int base = 35; 
        
        if (pneu.getTipo().equalsIgnoreCase(clima)){ 
            return base + (int)(Math.random() * 21) + 20; 
        }else { 
            return base + (int)(Math.random() * 21); 
        } 
    }
}