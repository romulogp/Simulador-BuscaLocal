package br.com.war;

import br.com.war.gui.MainWindow;
import br.com.war.heuristicas.Monotona;

public class Main {
    private static final int numMaquinas = 4;
    private static final double parametroTarefas = 1.5;
    private static final boolean debug = true;
    
    public static void main(String[] args) {
        MainWindow.loadWindow();
        
        Monotona monotona = new Monotona();
        monotona.instanciarMaquinas(numMaquinas, parametroTarefas);        
        monotona.executar(debug);
        System.out.println("ACABOU");
    }
}