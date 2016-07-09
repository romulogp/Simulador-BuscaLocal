package br.com.war.modelo;

public class Tarefa {
    
    private static final int TEMPO_MAXIMO = 100; // medido em milisegundos

    private final int tempo;

    public Tarefa() {
        tempo = (int) (Math.random() * TEMPO_MAXIMO) + 1;
        System.out.println("Tarefa Criada. Tempo: " + tempo + " ms");
    }

    public int getTempo() {
        return tempo;
    }

}
