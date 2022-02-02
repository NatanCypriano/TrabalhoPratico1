
package com.mycompany.atvtabhash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
    * @author Natan Cypriano e Matheus Costa
 */
public class AgendaDeContatos {
    HashTable tabelaHash;
    int tamanhoTabela;
    public AgendaDeContatos(int tabSize){
        tamanhoTabela = tabSize;
        tabelaHash = new HashTable(tamanhoTabela);
    }
    
    public Conteudo localizarContato(String chave){
        System.out.println("*-------*");
       System.out.println("Localizando contato");
       System.out.println("*-------*");
       return tabelaHash.Get(chave);
       
    }
    
    public void inserirContato(String nome, String telefone, String cidade, String pais){
        tabelaHash.Inserir(nome, new Conteudo(telefone, cidade, pais));
       
    }
    
    public void  excluirContato(String chave){
        System.out.println("*-------*");
        tabelaHash.Deletar(chave);
        System.out.println("*-------*");
    }
    
    public void atualizarContato(String nome, String telefone, String cidade, String pais){
    System.out.println("*-------*");
     tabelaHash.Atualizar(nome, telefone, cidade, pais);
    System.out.println("*-------*");
        }
    
    public void salvarDados(){
    System.out.println("*-------*");
    System.out.println("Salvando dados");
    System.out.println("Tamanho da tabela "+ tabelaHash.tamanho);
    System.out.println("Inseridos "+ tabelaHash.inseridos);
    System.out.println("colisões "+ tabelaHash.colisoes);
    System.out.println("*-------*");
    tabelaHash.SalvarArquivo();
    
    }
    
    
    
}
