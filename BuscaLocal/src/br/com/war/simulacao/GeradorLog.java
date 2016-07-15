package br.com.war.simulacao;

import br.com.war.heuristicas.BuscaLocal;

/**
 * 
 * @author Rômulo Göelzer Portolann
 */
public class GeradorLog {

    public static String gerar(BuscaLocal heuristica, long n, int m, int replicacao, double tempo, int iteracoes, int valor, String parametro) {
        StringBuilder logFeed = new StringBuilder();
        logFeed.append(heuristica.getClass().getSimpleName()).append(",")
                .append(n).append(",")
                .append(m).append(",")
                .append(replicacao).append(",")
                .append(tempo).append(",")
                .append(iteracoes).append(",")
                .append(valor).append(",")
                .append(parametro).append("\n\r");
        return logFeed.toString();
    }
    
}
