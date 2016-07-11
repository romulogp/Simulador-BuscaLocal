package br.com.war.modelo;

public class Tarefa {
    
    private static final int TEMPO_MAXIMO = 100; // medido em milisegundos
    
    private final int PID; // numero da tarefa
    private final int tempo;
    
    public Tarefa(int PID) {
        this.PID = PID;
        this.tempo = (int) (Math.random() * TEMPO_MAXIMO) + 1;
        System.out.println("Tarefa criada. PID: "+ PID +"  Tempo: " + tempo + " ms");
    }

    public int getPID() {
        return PID;
    }
    
    public int getTempo() {
        return tempo;
    }

}