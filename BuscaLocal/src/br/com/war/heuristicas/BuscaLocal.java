package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.ArrayList;
import java.util.Stack;

public abstract class BuscaLocal {

    private ArrayList<Maquina> instanciaInicial;
    private ArrayList<Maquina> maquinas;
    private int numTarefas;
    private int iteracoes = 0; // iterações são incrementadas apenas quando a melhora no makespan
    private int contadorIteracoesDebug = 1;
    private String parametro;
    private long tempoExecucao;
    
    public BuscaLocal() {
        this.maquinas = new ArrayList<>();
        this.instanciaInicial = new ArrayList<>();
    }
    
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
        instanciaInicial = new ArrayList<>();
        this.instanciaInicial.add(new Maquina(instanciarTarefasIniciais(numMaquinas, parametroTarefa))); // ADICIONA UM PILHA DE TAREFAS NA PRIMEIRA MAQUINA
        for (int i = 0; i < numMaquinas - 1; i++) {
            this.instanciaInicial.add(new Maquina());
        }
        restaurarInstanciaInicial();
    }

    public void restaurarInstanciaInicial() {
        maquinas = new ArrayList<>();
        for (Maquina m : instanciaInicial) {
            Maquina novaMaquina = (Maquina) m.clone();
            maquinas.add(novaMaquina);
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
        return maquinas;
    }

    public Maquina getProximaMaquina(Maquina maquinaAtual, int numeroMovimentosSemMelhora) {
        int proximoIndex = maquinas.indexOf(maquinaAtual) + 1 + numeroMovimentosSemMelhora;

        if (proximoIndex >= maquinas.size()) {
            proximoIndex = 0 + numeroMovimentosSemMelhora;
        }

        return maquinas.get(proximoIndex);
    }
    
    public void incrementaIteracao(){
        this.iteracoes++;
    }

    public void debug(ArrayList<Maquina> maquinas, int numeroMovimentosSemMelhora, int numeroMovimentosSemMelhoraRandomizada) {
        int numeroMaquina = 1;
        
        System.out.println("\n"+this.contadorIteracoesDebug+ "° ITERACAO");
        
        for (Maquina maquina : maquinas) {
            String debugMsg = "";
            //numero da maquina
            debugMsg += Integer.toString(numeroMaquina) + " - ";
            //makespan da maquina
            debugMsg += "MS(" + maquina.getMakespan() + ")";
            
            int numeroTarefa = 1;
            for(Tarefa tarefa : maquina.getTarefas() ){
                debugMsg += " - " + "T" + Integer.toString(numeroTarefa);
                debugMsg += "(" + Integer.toString(tarefa.getTempo()) + ")";
                numeroTarefa++;
            }
            
            System.out.println(debugMsg);            
            numeroMaquina++;            
        }     
        System.out.println("movimentos sem melhora: " + numeroMovimentosSemMelhora);
        System.out.println("movimentos sem melhora randomizada: " + numeroMovimentosSemMelhoraRandomizada);
        this.contadorIteracoesDebug++;
    }
    
    public Maquina getMaquinaAleatoria(boolean maquinaPrecisaTerTarefas){
        Maquina maquinaAleatoria;
        int indexMaquinaAleatoria;
        
        do{
            indexMaquinaAleatoria = (int) (Math.random() * maquinas.size());        
            maquinaAleatoria = maquinas.get(indexMaquinaAleatoria);
        }while(maquinaPrecisaTerTarefas && maquinaAleatoria.getNumeroTarefas() == 0); 
        
        return maquinaAleatoria;        
    }
        
    public int getIteracoes(){
        return this.iteracoes;
    }
    
    public int getNumeroTarefas(){
        return this.numTarefas;
    }
    
    public int getNumeroMaquinas(){
        return this.maquinas.size();
    }
    
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    public String getParametro() {
        return parametro;
    }
    
    public long getTempoExecucao() {
        return tempoExecucao;
    }
    
    public void setTempoExecucao(long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }
    
}
