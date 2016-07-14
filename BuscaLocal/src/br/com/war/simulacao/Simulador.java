package br.com.war.simulacao;

import br.com.war.heuristicas.BuscaLocal;
import java.util.List;

/**
 * 
 * @author Rômulo Göelzer Portolann
 */
public class Simulador {

    private final List<Double> maquinaPipeline;
    private final List<Double> parametroPipeline;
    private final int vezesExecutarInstancia;
    private final StringBuilder logBuilder;
    
    public Simulador(List<Double> maquinaPipeline, List<Double> parametroPipeline, int vezesExecutarInstancia) {
        this.maquinaPipeline = maquinaPipeline;
        this.parametroPipeline = parametroPipeline;
        this.vezesExecutarInstancia = vezesExecutarInstancia;
        this.logBuilder = new StringBuilder();
    }
    
    public void simular(BuscaLocal heuristica) {
        
        for (int i = 0; i < vezesExecutarInstancia; i++) {
            for (Double r : parametroPipeline) {
                for (Double m : maquinaPipeline) {
                    heuristica.instanciarMaquinas(m.intValue(), r);
                    long inicio = System.currentTimeMillis();
                    heuristica.executar(false);
                    long tempo  = System.currentTimeMillis() - inicio;
                    logBuilder.append(GeradorLog.gerar(
                            heuristica,
                            Math.round(Math.pow(m, r)),
                            m.intValue(),
                            (i + 1),
                            tempo,
                            0,
                            heuristica.getMaquinaMaiorMakespan().getMakespan(),
                            heuristica.getParametro()));
                }
            }
        }
    }
    
    public String getLog() {
        return logBuilder.toString();
    }
    
}
