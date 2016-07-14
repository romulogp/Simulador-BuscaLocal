package br.com.war;

import br.com.war.gui.MainWindow;
import br.com.war.heuristicas.Monotona;

public class Main {
    private static final int numMaquinas = 50;
    private static final double parametroTarefas = 2.0;
    private static final boolean debug = true;
    
    public static void main(String[] args) {
        //MainWindow.loadWindow();
        long startTime = System.currentTimeMillis() / 1000;
        Monotona monotona = new Monotona();
        monotona.instanciarMaquinas(numMaquinas, parametroTarefas);        
        monotona.executar(debug);
        long endTime   = System.currentTimeMillis() / 1000;
        System.out.println("ACABOU");
        
        System.out.println("Numero de Máquinas: "+ numMaquinas);
        System.out.println("Numero de Tarefas: "+ monotona.getNumeroTarefas());
        System.out.println("Iterações: "+ monotona.getIteracoes());
        System.out.println("Melhor Makespan: "+ monotona.getMaquinaMaiorMakespan().getMakespan());
        System.out.println("Tempo: "+(endTime - startTime)+" segundos");
    }
}