package br.com.war;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.List;
import java.util.Stack;

public class Main {

    private List<Maquina> maquinas;
    
    public static void main(String[] args) {
        
        Stack<Tarefa> tarefas = new Stack<>();
        tarefas.add(new Tarefa());
        tarefas.add(new Tarefa());
        tarefas.add(new Tarefa());
        
        Maquina m1 = new Maquina(tarefas);
        Maquina m2 = new Maquina();
        
        m1.enviaTarefa(m2);
        
        System.out.println(m2.getPrimeiraTarefa().getTempo());
        System.out.println(m2.getMakespan());
        
    }

}
