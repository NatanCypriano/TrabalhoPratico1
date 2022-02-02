/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenacaoexterna;

import java.io.IOException;

/**
 *
 * @author mathe
 */
public class OrdenacaoExterna {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        
        
        Kwaymerge kway = new Kwaymerge();
        MergeSortExterno merge = new MergeSortExterno();
        
        String arq = "arqEntrada.csv";
        
        System.out.println("===========================================Bloco KwayMerge======================================================");
        final long startTime = System.currentTimeMillis();// tempo incial
        kway.divideArquivo(arq);
        
        for(int i = 0; i < kway.getContador(); i++){
            kway.ordenaArquivo( "divididos/arq" + i + ".csv");
        }
        kway.mergingArquivo();
        final long elapsedTimeMillis = System.currentTimeMillis() - startTime; //tempo total de execução do programa
        System.out.println("Tempo total: " + elapsedTimeMillis);
        System.out.println("===========================================Fim Bloco KwayMerge==================================================");
        
        System.out.println("\n\n\n");
        
        System.out.println("==========================================Bloco MergeSortExterno================================================");
        final long startTime3 = System.currentTimeMillis();// tempo incial
        merge.setFiles(arq);
        
        merge.mergeSortExterno(arq);
        final long elapsedTimeMillis3 = System.currentTimeMillis() - startTime3; //tempo total de execução do programa
        System.out.println("Tempo total: " + elapsedTimeMillis3);
        System.out.println("========================================Fim Bloco MergeSortExterno==============================================");
    }

    
    
}
