package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.ArrayList;

public class MonotonaRandomizada extends BuscaLocal {

    private final double frequenciaCaminhadaAleatoria; 
    private final boolean verificarMelhora; 
    private static final int MAXIMO_ITERACOES_SEM_MELHORA = 1000;
    
    public MonotonaRandomizada(double frequenciaCaminhadaAleatoria, boolean verificarMelhora) {
        this.frequenciaCaminhadaAleatoria = frequenciaCaminhadaAleatoria;
        this.verificarMelhora = verificarMelhora;
        setParametro(String.valueOf(frequenciaCaminhadaAleatoria));
    }
    
    @Override
    public void executar(boolean debug) {
        ArrayList<Maquina> maquinas = this.getMaquinas();
        Maquina maquinaMaiorMakespan;
        Maquina maquinaAleatoria1;
        Maquina maquinaAleatoria2;
        Maquina proximaMaquina;
        int makespanAtual;
        //ponto de parada
        int numeroMovimentosSemMelhora = 0;
        int numeroMovimentosSemMelhoraRandomizada = 0;     
        
        
        
        //ponto de parada
        while(numeroMovimentosSemMelhoraRandomizada < MAXIMO_ITERACOES_SEM_MELHORA){
            if(debug){
                this.debug(maquinas, numeroMovimentosSemMelhora, numeroMovimentosSemMelhoraRandomizada);
            }


            int chanceAleatória = (int) (Math.random() * 100) + 1;  


            //random
            if(chanceAleatória <= (this.frequenciaCaminhadaAleatoria * 100)){    
                maquinaMaiorMakespan      = this.getMaquinaMaiorMakespan();
                makespanAtual             = maquinaMaiorMakespan.getMakespan();    
                
                maquinaAleatoria1         = this.getMaquinaAleatoria(true);
                int indexTarefaAleatoria  = maquinaAleatoria1.getIndexTarefaAleatoria();
                Tarefa tarefaAleatoria    = maquinaAleatoria1.removeTarefaAleatoriaFromIndex(indexTarefaAleatoria);
                maquinaAleatoria2         = this.getMaquinaAleatoria(false);                              
              
                //adiciona a tarefa aleatoria em outra maquina.
                maquinaAleatoria2.adicionaTarefa(tarefaAleatoria); 
                
                if(this.verificarMelhora == false){
                    numeroMovimentosSemMelhora = 0; 
                }
                
                if(this.verificarMelhora){
                    int novoMakeSpan          = this.getMaquinaMaiorMakespan().getMakespan(); 
                    //verifica se ocorreu melhora na solucao
                   if(novoMakeSpan < makespanAtual){

                    if(debug){
                        System.out.println("realizou um movimento aleátorio da maquina " + this.getMaquinas().indexOf(maquinaAleatoria1)
                        + " para a maquina " + this.getMaquinas().indexOf(maquinaAleatoria2) + " tarefa: " + indexTarefaAleatoria); 
                    }

                       //zera o numero de movimentos sem melhora
                       numeroMovimentosSemMelhora = 0;                        
                   //nao ocorreu melhora, mantem a tarefa na mesma maquina, na mesma posição
                   }else{
                       if(debug){
                            System.out.println("tentou realizar um movimento aleátorio da maquina " + this.getMaquinas().indexOf(maquinaAleatoria1)
                            + " para a maquina " + this.getMaquinas().indexOf(maquinaAleatoria2) + " tarefa: " + indexTarefaAleatoria); 
                        }

                       maquinaAleatoria1.adicionaTarefaToIndex(indexTarefaAleatoria, maquinaAleatoria2.removePrimeiraTarefa()); 
                   } 
                }

            // aumenta o numero de iterações realizadas
            this.incrementaIteracao();
               
            //primeira melhora    
            }else{            
                //para se tentar mover uma terefa para as outras maquinas e não ocorrer melhora
                while (numeroMovimentosSemMelhora <= (maquinas.size() -1 )){                
                    maquinaMaiorMakespan = this.getMaquinaMaiorMakespan();
                    makespanAtual        = maquinaMaiorMakespan.getMakespan();            
                    proximaMaquina       = this.getProximaMaquina(maquinaMaiorMakespan, numeroMovimentosSemMelhora);
                    Tarefa tarefaAtual   = maquinaMaiorMakespan.removePrimeiraTarefa(); 

                    //ocorreu melhora, move a tarefa para a próxima maquina
                    if(proximaMaquina.getMakespan() + tarefaAtual.getTempo() < makespanAtual){
                        proximaMaquina.adicionaTarefa(tarefaAtual); 

                        //zera o numero de movimentos sem melhora
                        numeroMovimentosSemMelhora = 0;                        
                        
                        //iterompe o laço, ja achamos a primeira melhora nessa iteração
                        break;
                    //nao ocorreu melhora, mantem a tarefa na mesma maquina    
                    }else{
                        maquinaMaiorMakespan.adicionaTarefa(tarefaAtual);
                        //incrementa o numero de movimentos sem melhora.
                        numeroMovimentosSemMelhora++;
                    }
                    
                    // aumenta o numero de iterações realizadas
                    this.incrementaIteracao();
                }
            } 
                        
            numeroMovimentosSemMelhoraRandomizada++;
        }
    }
}
