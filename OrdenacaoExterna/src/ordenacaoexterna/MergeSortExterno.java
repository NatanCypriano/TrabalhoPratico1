/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenacaoexterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mathe
 */
public class MergeSortExterno {
    public static String[] nomeArq = new String[4];
    public static int ENTRADA1 = 0;
    public static int ENTRADA2 = 1;
    public static int SAIDA1 = 2;
    public static int SAIDA2 = 3;
    
    static int divisor(String arq) throws IOException {
        BufferedReader entrada1 = new BufferedReader(new FileReader(arq));

        BufferedWriter entrada2 = new BufferedWriter(new FileWriter(nomeArq[ENTRADA1]));
        BufferedWriter entrada3 = new BufferedWriter(new FileWriter(nomeArq[ENTRADA2]));


        int total = 0, count = 1;

        while (count != -1){

            String line = entrada1.readLine();
            if(line != null){
                if (count % 2 == 0){
                    entrada2.append(line + "\n");
                }else{
                    entrada3.append(line + "\n");
                }
                count++;
            }else{
                total = count;
                count = -1;
            }

        }


        entrada1.close();
        entrada2.close();
        entrada3.close();
        return total;
    }

    public static void passada(String entrada_1, String entrada_2, String saida_1, String saida_2, int numRodada) throws IOException {
        
        BufferedReader entrada1 = new BufferedReader(new FileReader(entrada_1));
        BufferedReader entrada2 = new BufferedReader(new FileReader(entrada_2));
        BufferedWriter saida1 = new BufferedWriter(new FileWriter(saida_1));
        BufferedWriter saida2 = new BufferedWriter(new FileWriter(saida_2));
        BufferedWriter temp = saida1;

        int cont = 0, cont1 = 0;
        String  linha = entrada1.readLine(), linha1 = entrada2.readLine();

        while (linha != null || linha1 != null){

            for(int count = 0;count < numRodada*2; count++){
                if(linha != null && linha1 != null && linha.compareTo(linha1) <= 0 && cont < numRodada)
                {
                    temp.append(linha + "\n");
                    cont++;
                    linha = entrada1.readLine();
                }
                else if(linha1 != null && cont1 < numRodada)
                {
                    temp.append(linha1 + "\n");
                    cont1++;
                    linha1 = entrada2.readLine();
                }
                else if(linha != null && cont < numRodada)
                {
                    temp.append(linha + "\n");
                    cont++;
                    linha = entrada1.readLine();
                }
            }
            cont = 0; cont1 = 0;
            if(temp == saida1) temp = saida2;
            else temp = saida1;
        }
        entrada1.close();
        entrada2.close();
        saida1.close();
        saida2.close();
    }

    static void mergeSortExterno(String arq) throws IOException {
        int totalRuns = divisor(arq);
        String entrada1 = nomeArq[ENTRADA1], entrada2 = nomeArq[ENTRADA2];
        String saida1 = nomeArq[SAIDA1], saida2 = nomeArq[SAIDA2];
        String aux1, aux2;
        int rodada = 1;
        while (rodada <= totalRuns*2){
            passada(entrada1, entrada2, saida1, saida2, rodada);
            rodada = rodada * 2;
            aux1 = entrada1;
            aux2 = entrada2;
            entrada1 = saida1;
            entrada2 = saida2;
            saida1 = aux1;
            saida2 = aux2;
        }
    }
    
    public void setFiles(String arq) {
        String output = "convertido.txt";

        nomeArq[ENTRADA1] = arq + ".1";
        nomeArq[ENTRADA2] = arq + ".2";
        
        nomeArq[SAIDA1] = output + ".1";
        nomeArq[SAIDA2] = output + ".2";
    }
}
