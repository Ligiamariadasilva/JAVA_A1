package corrida.models;
import java.util.*;

public class Clima {
    public static String mudarClima(String climaAtual) {

        List<String> climas = new ArrayList<>(Arrays.asList("seco", "chuva", "neve"));

        climas.remove(climaAtual);
        
        return climas.get(new Random().nextInt(climas.size()));
    }
}