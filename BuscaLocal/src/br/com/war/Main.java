package br.com.war;

import br.com.war.heuristicas.Monotona;
import br.com.war.heuristicas.MonotonaRandomizada;

public class Main {
    private static final boolean debug = true;
    private static final int numMaquinas = 4;
    private static final double parametroTarefas = 1.5;
    private static final String heuristica = "monotona";
    private static final int replicacao = 10;
    private static final String parametroAleatorio = "NA";
    
    //parametro usado na busca local monótona randomizada
    private static final double frequenciaCaminhadaAleatoria = 0.9;
    
    
    public static void main(String[] args){
        //MainWindow.loadWindow();
        
        //busca local monótona
//        long startTime = System.currentTimeMillis();
//        Monotona monotona = new Monotona();
//        monotona.instanciarMaquinas(numMaquinas, parametroTarefas);        
//        monotona.executar(debug);
//        long endTime = System.currentTimeMillis();
//        new Log(monotona, heuristica, replicacao, endTime - startTime, parametroAleatorio);
                
        //busca local monótona randomizada        
        long startTime = System.currentTimeMillis();
        MonotonaRandomizada monotonaRandomizada = new MonotonaRandomizada(frequenciaCaminhadaAleatoria);
        monotonaRandomizada.instanciarMaquinas(numMaquinas, parametroTarefas);        
        monotonaRandomizada.executar(debug);
        long endTime = System.currentTimeMillis();
        new Log(monotonaRandomizada, "monótona randomizada", replicacao, endTime - startTime, parametroAleatorio);
        
    }
}