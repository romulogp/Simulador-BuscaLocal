package br.com.war.modelo;

public class Tarefa implements Cloneable {
    
    private static final int TEMPO_MAXIMO = 100; // medido em milisegundos
    
    private final int PID; // numero da tarefa
    private final int tempo;
    
    public Tarefa(int PID) {
        this.PID = PID;
        this.tempo = (int) (Math.random() * TEMPO_MAXIMO) + 1;
//        System.out.println("Tarefa criada. PID: "+ PID +"  Tempo: " + tempo + " ms");
    }

    public Tarefa(int PID, int tempo) {
        this.PID = PID;
        this.tempo = tempo;
    }

    public int getPID() {
        return PID;
    }
    
    public int getTempo() {
        return tempo;
    }

    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public Object clone() {
        try {
            return super.clone();
            // return new Tarefa(PID, tempo);
        } catch (CloneNotSupportedException cnse) {
            return null;
        }
    }

}