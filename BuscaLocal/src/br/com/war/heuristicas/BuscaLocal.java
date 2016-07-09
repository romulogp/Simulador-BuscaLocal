package br.com.war.heuristicas;

import br.com.war.modelo.Maquina;
import java.util.List;

public abstract class BuscaLocal {

    public void executar() {};
    
    public Maquina getMaiorMakespan(List<Maquina> maquinas) {
        if (maquinas.isEmpty()) {
            return null;
        }
        Maquina maiorMakeSpan = new Maquina();
        for (Maquina m : maquinas) {
            if (m.getMakespan() > maiorMakeSpan.getMakespan()) {
                maiorMakeSpan = m;
            }
        }
        return maiorMakeSpan;
    }
    
}
