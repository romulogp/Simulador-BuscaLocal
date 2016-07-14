package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.ArrayList;
import java.util.Stack;

public abstract class BuscaLocal {

    private ArrayList<Maquina> maquinas;
    private int numTarefas;

    public void executar(boolean debug) { 
        
    };

    /**
     * Criar as máquinas para busca
     *
     * @param numMaquinas - Número de máquinas a serem criadas
     * @param parametroTarefa - Parâmetro (r) definido para a obter a quantidade
     * de tarefas necessárias
     */
    public void instanciarMaquinas(int numMaquinas, double parametroTarefa) {
        maquinas = new ArrayList<>();
        this.maquinas.add(new Maquina(instanciarTarefasIniciais(numMaquinas, parametroTarefa))); // ADICIONA UM PILHA DE TAREFAS NA PRIMEIRA MAQUINA
        for (int i = 0; i < numMaquinas - 1; i++) {
            this.maquinas.add(new Maquina());
        }
    }

    /**
     * Criar a primeira carga de tarefas
     *
     * @param numMaquinas - Número de máquinas a serem criadas
     * @param parametroTarefa - Parâmetro (r) definido para a obter a quantidade
     * de tarefas necessárias
     * @return tarefas - pilha de tarefas
     */
    public Stack<Tarefa> instanciarTarefasIniciais(int numMaquinas, double parametroTarefa) {
        this.numTarefas = (int) Math.round(Math.pow(numMaquinas, parametroTarefa));
        Stack<Tarefa> tarefas = new Stack();
        for (int i = 0; i < this.numTarefas; i++) {
            tarefas.push(new Tarefa(i + 1));
        }
        return tarefas;
    }

    public Maquina getMaquinaMaiorMakespan() {
        if (this.maquinas.isEmpty()) {
            return null;
        }
        Maquina maiorMakeSpan = new Maquina();
        for (Maquina m : this.maquinas) {
            if (m.getMakespan() > maiorMakeSpan.getMakespan()) {
                maiorMakeSpan = m;
            }
        }
        return maiorMakeSpan;
    }

    public ArrayList<Maquina> getMaquinas() {
        return this.maquinas;
    }

    public Maquina getProximaMaquina(Maquina maquinaAtual, int numeroMovimentosSemMelhora) {
        ArrayList<Maquina> maquinas = this.getMaquinas();

        int proximoIndex = maquinas.indexOf(maquinaAtual) + 1 + numeroMovimentosSemMelhora;

        if (proximoIndex >= maquinas.size()) {
            proximoIndex = 0 + numeroMovimentosSemMelhora;
        }

        return maquinas.get(proximoIndex);
    }
}
