package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.ArrayList;

public class Monotona extends BuscaLocal {

    public Monotona() {
        setParametro("NA");
    }
    
    @Override
    public void executar(boolean debug) {
        long tempoInicial = System.currentTimeMillis();
        
        ArrayList<Maquina> maquinas = this.getMaquinas();
        Maquina maquinaMaiorMakespan;
        Maquina proximaMaquina;
        int makespanAtual;
        //ponto de parada
        int numeroMovimentosSemMelhora = 0;
                
        
        //para se tentar mover uma terefa para as outras maquinas e não ocorrer melhora
        while (numeroMovimentosSemMelhora <= (maquinas.size() -1 )){
            
            if(debug){
                this.debug(maquinas, numeroMovimentosSemMelhora, 0);
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
                
            //nao ocorreu melhora, mantem a tarefa na mesma maquina    
            }else{
                maquinaMaiorMakespan.adicionaTarefa(tarefaAtual);
                //incrementa o numero de movimentos sem melhora.
                numeroMovimentosSemMelhora++;
            }
            
            // aumenta o numero de iterações realizadas
            this.incrementaIteracao();
        }
        
        setTempoExecucao(System.currentTimeMillis() - tempoInicial);
    } 
}