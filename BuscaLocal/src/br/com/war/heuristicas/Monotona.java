package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import br.com.war.modelo.Tarefa;
import java.util.ArrayList;

public class Monotona extends BuscaLocal {
    
    @Override
    public void executar() {
        ArrayList<Maquina> maquinas = super.getMaquinas();
        Maquina maquinaMaiorMakespan = maquinas.get(0);
        double makespanAtual = maquinas.get(0).getMakespan();
        boolean melhora;
        boolean recomecar = false;
        
        for (int i = 0, numMaquinas = maquinas.size(); i < numMaquinas; i++) {
            melhora = true;
            while(melhora){
                Tarefa atual = maquinas.get(i).removePrimeiraTarefa();
                melhora = false;
                for (int j = i + 1; j < numMaquinas; j++) {
                    if((maquinaMaiorMakespan.getMakespan() < makespanAtual) && (maquinas.get(j).getMakespan() + atual.getTempo() < makespanAtual)) {
                        maquinas.get(j).adicionaTarefa(atual);
                        maquinaMaiorMakespan = super.getMaquinaMaiorMakespan();
                        makespanAtual = maquinaMaiorMakespan.getMakespan();
                        melhora = true;
                        recomecar = true;
                        break;
                    }
                }
                if(!melhora){
                    maquinas.get(i).adicionaTarefa(atual);
                }
            }
            if(i == numMaquinas - 1 && recomecar){
                i = 0;
                recomecar = false;
            }
        }
    }
    
}