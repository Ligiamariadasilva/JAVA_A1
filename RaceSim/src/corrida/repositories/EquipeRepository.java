package corrida.repositories;

import corrida.models.Equipe;
import corrida.services.LogService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeRepository {

    private static final String FILE_PATH = "equipes.ser";

    @SuppressWarnings("unchecked")

    public List<Equipe> lerTodos() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            return (List<Equipe>) ois.readObject();
        }catch (FileNotFoundException e) { 
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e){ 
            e.printStackTrace(); return new ArrayList<>(); 
        }
    }

    public void salvar(List<Equipe> equipes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(equipes);
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }
}