package models;

// Representa o mapa da corrida com nome e clima inicial
public class Mapa {
    private String nome;
    private String climaInicial;

    public Mapa(String nome, String climaInicial) {
        this.nome = nome;
        this.climaInicial = climaInicial;
    }

    public String getNome() {
        return nome;
    }

    public String getClimaInicial() {
        return climaInicial;
    }
}