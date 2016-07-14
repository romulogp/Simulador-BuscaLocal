package br.com.war;

import br.com.war.heuristicas.BuscaLocal;
import java.io.FileWriter;

public class Log {
    private String fileName = "log.txt";
    
    public Log(BuscaLocal buscaLocal, String heuristica, int replicacao, long tempo, String parametro){      
        System.out.println("Numero de Máquinas: "+ buscaLocal.getNumeroMaquinas());
        System.out.println("Numero de Tarefas: "+ buscaLocal.getNumeroTarefas());
        System.out.println("Iterações: "+ buscaLocal.getIteracoes());
        System.out.println("Melhor Makespan: "+ buscaLocal.getMaquinaMaiorMakespan().getMakespan());
        System.out.println("Tempo: "+ tempo);
        
        try (FileWriter fw = new FileWriter(this.fileName, true)) {
            String log = heuristica+","+buscaLocal.getNumeroTarefas()+","+
                buscaLocal.getNumeroMaquinas()+","+replicacao+","+tempo+","+
                buscaLocal.getIteracoes()+","+buscaLocal.getMaquinaMaiorMakespan().getMakespan()+","+
                parametro;
            fw.append(System.lineSeparator() + log);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
