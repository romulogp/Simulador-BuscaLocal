package br.com.war.modelo;

import java.util.Stack;

public class Maquina {

    private final Stack<Tarefa> tarefas;
    private int makespan;

    /**
     * Instancia uma nova máquina sem menhuma tarefa.
     */
    public Maquina() {
        tarefas = new Stack<>();
        makespan = 0;
    }

    /**
     * Instancia uma nova máquina com uma lista predefinida de tarefas.
     * @param tarefas a serem executadas
     */
    public Maquina(Stack<Tarefa> tarefas) {
        this.tarefas = tarefas;
        calculaMakespan();
    }

    /**
     * Realiza o cálculo do makespan atual da máquina.
     * Utilizado quando a máquina é instanciada com N tarefas
     */
    private void calculaMakespan() {
        tarefas.stream().forEach((tarefa) -> {
            this.makespan += tarefa.getTempo();
        });
    }

    /**
     * Adiciona uma tarefa na pilha de tarefas da máquina
     * @param tarefa a ser inserida
     */
    public void adicionaTarefa(Tarefa tarefa) {
        tarefas.push(tarefa);
        makespan += tarefa.getTempo();
    }
    
    /**
     * 
     * @return a tarefa no topo da pilha
     */
    public Tarefa getPrimeiraTarefa() {
        return tarefas.empty() ? null : tarefas.lastElement();
    }
    
    /**
     * Remove a primeira tarefa da pilha de tarefas da máquina 
     * @return 
     */
    public Tarefa removePrimeiraTarefa() {
        Tarefa retorno = tarefas.pop();
        if (retorno != null) {
            makespan -= retorno.getTempo();
        }
        return retorno;
    }
    
    /**
     * Envia a tarefa do topo da pilha para a máquina destino
     * @param destino
     * @return envio bem sucedido
     */
    public boolean enviaTarefa(Maquina destino) {
        if (getPrimeiraTarefa() != null) {
            destino.adicionaTarefa(removePrimeiraTarefa());
            return true;
        }
        return false;
    }
    
    public Stack<Tarefa> getTarefas() {
        return tarefas;
    }

    public int getMakespan() {
        return makespan;
    }
    
}