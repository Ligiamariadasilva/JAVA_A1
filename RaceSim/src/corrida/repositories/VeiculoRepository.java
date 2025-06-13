package corrida.repositories;

import corrida.models.Veiculo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository{

    private static final String FILE_PATH = "veiculos.ser";

    @SuppressWarnings("unchecked")
    public List<Veiculo> lerTodos(){

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            return (List<Veiculo>) ois.readObject();
        }catch (FileNotFoundException e){ 
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace(); 
            return new ArrayList<>(); 
        }
    }

    public void salvar(List<Veiculo> veiculos) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            oos.writeObject(veiculos);
        } catch (IOException e){ 
            e.printStackTrace(); 
        }
    }
}