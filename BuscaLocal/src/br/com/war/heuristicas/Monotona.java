package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.ArrayList;

public class Monotona extends BuscaLocal {
    private int contadorIteracoesDebug = 1;
    
    
    @Override
    public void executar(boolean debug) {
        ArrayList<Maquina> maquinas = this.getMaquinas();
        Maquina maquinaMaiorMakespan;
        Maquina proximaMaquina;
        int makespanAtual;
        //ponto de parada
        int numeroMovimentosSemMelhora = 0;
                
        
        //para se tentar mover uma terefa para as outras maquinas e não ocorrer melhora
        while (numeroMovimentosSemMelhora <= (maquinas.size() -1 )){
            
            if(debug){
                this.debug(maquinas, numeroMovimentosSemMelhora);
            }
            maquinaMaiorMakespan = this.getMaquinaMaiorMakespan();
            makespanAtual        = maquinaMaiorMakespan.getMakespan();            
            proximaMaquina       = this.getProximaMaquina(maquinaMaiorMakespan, numeroMovimentosSemMelhora);
            Tarefa tarefaAtual   = maquinaMaiorMakespan.removePrimeiraTarefa(); 
            
            //ocorreu melhora, move a tarefa para a próxima maquina
            if(proximaMaquina.getMakespan() + tarefaAtual.getTempo() < makespanAtual){
                proximaMaquina.adicionaTarefa(tarefaAtual); 
                
                //zera o numero de movimentos sem melhora
                numeroMovimentosSemMelhora = 0;
                
                // aumenta o numero de iterações realizadas
                this.incrementaIteracao();
            //nao ocorreu melhora, mantem a tarefa na mesma maquina    
            }else{
                maquinaMaiorMakespan.adicionaTarefa(tarefaAtual);
                //incrementa o numero de movimentos sem melhora.
                numeroMovimentosSemMelhora++;
            }
        }   
    }

    private void debug(ArrayList<Maquina> maquinas, int numeroMovimentosSemMelhora) {
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
        this.contadorIteracoesDebug++;
    }    
}