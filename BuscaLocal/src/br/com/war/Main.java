package br.com.war;

import br.com.war.heuristicas.Monotona;

public class Main {
    private static final boolean debug = false;
    private static final int numMaquinas = 50;
    private static final double parametroTarefas = 2.0;
    private static final String heuristica = "monotona";
    private static final int replicacao = 10;
    private static final String parametroAleatorio = "NA";
    
    public static void main(String[] args){
        //MainWindow.loadWindow();
        long startTime = System.currentTimeMillis();
        Monotona monotona = new Monotona();
        monotona.instanciarMaquinas(numMaquinas, parametroTarefas);        
        monotona.executar(debug);
        long endTime = System.currentTimeMillis();
        new Log(monotona, heuristica, replicacao, endTime - startTime, parametroAleatorio);
    }
}