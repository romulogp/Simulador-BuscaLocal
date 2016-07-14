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
        
        String cabecalho = "heuristica,n,m,replicacao,tempo,iteracoes,valor,parametro";
        logBuilder.append(cabecalho).append("\n");
        
        for (int i = 0; i < vezesExecutarInstancia; i++) {
            for (Double r : parametroPipeline) {
                for (Double m : maquinaPipeline) {
                    heuristica.instanciarMaquinas(m.intValue(), r);
                    heuristica.executar(false);
                    logBuilder.append(GeradorLog.gerar(
                            heuristica,
                            heuristica.getNumeroTarefas(),
                            heuristica.getNumeroMaquinas(),
                            (i + 1),
                            heuristica.getTempoExecucao(),
                            heuristica.getIteracoes(),
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
