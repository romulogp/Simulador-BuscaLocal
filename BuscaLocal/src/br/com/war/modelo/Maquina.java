package br.com.war.modelo;

import java.util.EmptyStackException;
import java.util.Stack;

public class Maquina implements Cloneable {

    private Stack<Tarefa> tarefas;
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

    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public Maquina clone() {
        try {
            Maquina m = (Maquina) super.clone();
            m.tarefas = (Stack) tarefas.clone();
            return m;
        } catch (CloneNotSupportedException cnse) {
            return null;
        }
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
     * @return a tarefa no topo da pilha
     */
    public Tarefa getPrimeiraTarefa() {
        return tarefas.empty() ? null : tarefas.lastElement();
    }
    
    /**
     * Remove a primeira tarefa da pilha de tarefas da máquina 
     * @return a tarefa removida
     */
    public Tarefa removePrimeiraTarefa() {
        Tarefa retorno;
        try {
            retorno = tarefas.pop();
            makespan -= retorno.getTempo();
        } catch (EmptyStackException ee) {
            return null;
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
    
    public int getNumeroTarefas(){
        return this.tarefas.size();
    }
    
    public int getIndexTarefaAleatoria() {
        int indexTarefaAleatoria = (int) (Math.random() * tarefas.size());

        return indexTarefaAleatoria;   
    }

    public Tarefa removeTarefaAleatoriaFromIndex(int indexTarefaAleatoria) {
        Tarefa retorno;
        try {
            retorno = tarefas.get(indexTarefaAleatoria);
            tarefas.remove(indexTarefaAleatoria);
            makespan -= retorno.getTempo();
        } catch (EmptyStackException ee) {
            return null;
        }
        return retorno;
    }

    public void adicionaTarefaToIndex(int indexTarefaAleatoria, Tarefa tarefaAleatoria) {
        tarefas.add(indexTarefaAleatoria, tarefaAleatoria);   
        makespan += tarefaAleatoria.getTempo();
    }
}