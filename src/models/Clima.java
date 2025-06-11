package models;

import java.util.*;

// Classe que simula mudança de clima aleatória
public class Clima {
    public static String mudarClima(String climaAtual) {
        List<String> climas = new ArrayList<>(Arrays.asList("seco", "chuva", "neve"));
        climas.remove(climaAtual);
        return climas.get(new Random().nextInt(climas.size()));
    }
}