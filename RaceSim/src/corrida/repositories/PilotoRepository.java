package corrida.repositories;

import corrida.models.Piloto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PilotoRepository {

    private static final String FILE_PATH = "pilotos.ser";

    @SuppressWarnings("unchecked")
    public List<Piloto> lerTodos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            return (List<Piloto>) ois.readObject();
        }catch (FileNotFoundException e) { return new ArrayList<>();
        }catch (IOException | ClassNotFoundException e){ 
            e.printStackTrace(); return new ArrayList<>(); 
        }
    }

    public void salvar(List<Piloto> pilotos){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(pilotos);
        } catch (IOException e){ 
            e.printStackTrace(); 
        }
    }
}