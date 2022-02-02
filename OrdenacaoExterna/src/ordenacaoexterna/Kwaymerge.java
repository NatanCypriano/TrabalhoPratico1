/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenacaoexterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author mathe
 */
public class Kwaymerge {
    private int contador = 1;
    private int buffer = 25;
    
    
    public void divideArquivo(String arq) throws IOException{
        
        ArrayList contatos = new ArrayList<String>();
        
        BufferedReader buffRead = new BufferedReader(new FileReader(arq));
        String linha = buffRead.readLine();
        for(int i = 0; i < this.contador; i++){
            contatos = new ArrayList<String>();
            int j = 0;
            while (true) {
                if (linha != null && j < this.buffer) {
                    contatos.add(linha);
                    j++;
                } else
                    break;
                linha = buffRead.readLine();
            }
            if(j==this.buffer){
                this.contador = this.contador + 1;
                escreverArquivo(contatos, "divididos/arq" + i + ".csv");
            }else if(j>0){
                escreverArquivo(contatos, "divididos/arq" + i + ".csv");
            }else{
                this.contador = this.contador - 1;
            }
            
            
        }
        buffRead.close();
    }
     
    public ArrayList lerArquivo(String arq) throws IOException{
        ArrayList leitor = new ArrayList<String>();
        BufferedReader buffRead = new BufferedReader(new FileReader(arq));
        String linha = buffRead.readLine();
        int i = 0;
	while (true) {
            if (linha != null && i < this.buffer) {
		leitor.add(linha);
                i++;
            } else
		break;
            linha = buffRead.readLine();
        }
	buffRead.close();
        return leitor;
    }
    public void escreverArquivo(ArrayList escritor, String arq) throws IOException{
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(arq));
        for(int i = 0; i<escritor.size(); i++){
            buffWrite.append(escritor.get(i) + "\n");
        }
	buffWrite.close();

    }
    
    public void ordenaArquivo(String arq) throws IOException{
        ArrayList contatos = lerArquivo(arq);
        
        final long startTime = System.currentTimeMillis();// tempo incial
        
        Collections.sort(contatos);
        
        final long elapsedTimeMillis = System.currentTimeMillis() - startTime; //tempo total de execução do programa
        // System.out.println("Tempo de Ordenação do arquivo: "+ arq + " " + elapsedTimeMillis);
        
        escreverArquivo(contatos, arq);
         
    }
    
    public int getContador(){
        return contador;
    }
    
    public void mergingArquivo() throws IOException{
        ArrayList<String> contatos1 = lerArquivo("divididos/arq0.csv");
        ArrayList<String> contatos2 = new ArrayList();
        ArrayList<String> contatosFinal = new ArrayList();
        for(int i=1; i<this.contador; i++){
            contatos2 = lerArquivo("divididos/arq" + i + ".csv");
            contatos1 = merging(contatos1, contatos2, "divididos/arq" + i + ".csv");
        }
        escreverArquivo(contatos1, "arquivoFinal.csv");
    }
    
    public ArrayList merging(ArrayList<String> v1, ArrayList<String> v2, String arq){
        String merging = "";
        ArrayList mergingVet = new ArrayList();
        int i = 0;
        int j = 0;
        int comp;
        while (i < v1.size() || j < v2.size()) {
            while (j < v2.size()) {
                if (i < v1.size()) {
                    comp = v1.get(i).compareTo(v2.get(j));
                    if (merging !=  v1.get(i)) {
                        if (merging !=  v2.get(j)) {
                            if (comp <= 0) {
                                mergingVet.add(v1.get(i));
                                merging =  (String) v1.get(i);
                                break;
                            } else if (comp > 0) {

                                mergingVet.add(v2.get(j));
                            }
                        }

                    } else {
                        break;
                    }
                } else if (merging != v2.get(j)) {
                    mergingVet.add(v2.get(j));

                }
                merging = v2.get(j);
                j++;
            }
            if (j >= v2.size() && i < v1.size() && merging != v1.get(i)) {
                merging = v1.get(i);
                mergingVet.add(v1.get(i));
            }
            i++;
        }
        return mergingVet;
    }
    

    
}
