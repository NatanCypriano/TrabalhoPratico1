
package com.mycompany.atvtabhash;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;
/**
 *
 * @author Natan Cypriano e Matheus Costa
 */
public class HashTable {
    Elemento[] arrayElementos;
    int tamanho;
    int inseridos;
    int colisoes;
    
    public HashTable(int _tamanho){//Define o tamanho da tabela e inicializa cada registro com null
        
        tamanho = CalculoTamanhoPrimo(_tamanho);
        System.out.println("Tamanho real do arquivo: "+ tamanho);
        arrayElementos = new Elemento[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arrayElementos[i] = new Elemento();
        }
    }
    
    public boolean numeroPrimo(int num){//Verifica se o numero é primo ou não
        for (int i = 2; i * i < num; i++) {
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
    
    public int CalculoTamanhoPrimo(int num){
        if(numeroPrimo(num)){
            return num;//Se o valor for primo retorna true
        }else{
            for (int i = num; ; i++) {
                if(numeroPrimo(i)){//Se não busca pelo numero primo mais proximo maior do que a entrada
                    return i;
                }
            }
        }
    }
    
    
    
    public int GetHash(String chave){//Calcula e retorna um hash code de acordo com a chave
        double hash = 0;
        int n = chave.length();
        for (int i = 0; i < n-1; i++) {
            hash = hash + chave.charAt(i);//Soma o valor em ascii de cada caracter e armazena em hash
        }
        return (int)hash%tamanho;//Faz o calculo hash mode(tamanho) para garantir chave unica dentro do tamanho da tabela
    }
    
    public void Inserir(String chave, Conteudo conteudo){
        int hash = GetHash(chave);
        if(arrayElementos[hash].proxElemento !=null){//Verifica colisão
            colisoes++;
        }
        
        Elemento elemento = arrayElementos[hash];
        Elemento novoElemento = new Elemento(chave, conteudo);//Cria um novo elemento
        novoElemento.proxElemento = elemento.proxElemento;//Insere o em uma nova posição na linked list
        elemento.proxElemento = novoElemento;//Aponta o elemento anterior ao novo elemento
        inseridos++;
        
        
    }
    
    public Conteudo Get(String chave){
        final long startTime = System.currentTimeMillis();// tempo incial
        Conteudo conteudo = null;
        int hash = GetHash(chave);
        Elemento elemento = arrayElementos[hash];
        
        while(elemento !=null){//Procura por um elemento válido dentro da linked list
            if(elemento.getChave() == null ? chave == null : elemento.getChave().equals(chave)){//Verifica se o elemento tem a mesma chave
                conteudo = elemento.getConteudo();
                final long elapsedTimeMillis = System.currentTimeMillis() - startTime; //tempo total de execução do programa
                System.out.println("Tempo de busca: "  + elapsedTimeMillis);
                return conteudo;//Retorna o conteudo da chave do elemento de entrada
            }
            elemento = elemento.proxElemento;//Caso o elemento não tenha a mesma chave, procura pelo próximo elemento na lista
        }
        final long elapsedTimeMillis = System.currentTimeMillis() - startTime; //tempo total de execução do programa
        System.out.println("Tempo de busca: "  + elapsedTimeMillis);
        return null;
    }
    
    public boolean Deletar(String chave){
        boolean deletado = false;
        Conteudo conteudoADeletar = Get(chave);
        if(conteudoADeletar != null){//Deleta as informações do conteudo
            conteudoADeletar.cidade = null;
            conteudoADeletar.pais = null;
            conteudoADeletar.telefone = null;
            conteudoADeletar = null;
            int hash = GetHash(chave);
            Elemento elemento = arrayElementos[hash];
            while(elemento !=null){
                if(elemento.proxElemento == null){//Deleta as informações dentro da linked list caso não tenha ocorrido colisão no indice em questão
                    elemento.chave = null;
                    elemento.conteudo = null;
                    elemento.proxElemento = null;
                    elemento = null;
                    deletado = true;
                    break;
                }
                else if(elemento.proxElemento.getChave() == null ? chave == null : elemento.proxElemento.getChave().equals(chave)){
                    //Procura o elemento a ser deletado dentro da linked list
                    if(elemento.proxElemento.proxElemento != null){
                        elemento.proxElemento = elemento.proxElemento.proxElemento;
                        Elemento elementoDeletar = elemento.proxElemento;
                        elementoDeletar.chave = null;
                        elementoDeletar.conteudo = null;
                        elementoDeletar.proxElemento = null;
                        elementoDeletar = null;
                        deletado = true;
                        break;
                    }
                }
                elemento = elemento.proxElemento;
            }
        }
        if(deletado)
        {
            System.out.println(chave+" deletado com sucesso");
            return true;
        }
        else
        {
            System.out.println("Nada foi deletado ");
            return false;
        }
    }
    
    public void Atualizar(String chave, String telefone, String cidade, String pais){
        //Delete e insere o contato para simular atualizacao
        boolean retorno = Deletar(chave);
        if(retorno){
        Inserir(chave,new Conteudo(telefone, cidade, pais));
        }else{
           System.out.println("Problema na atualização "); 
        }
    }
    
    public void SalvarArquivo() {
	BufferedWriter buffwrite;
	int i=0;
	String name, nomearq;
	 String city;
	 String country;
	 String phone, registro;
	 Scanner leitor = new Scanner(System.in);
	 
	System.out.print("Nome do Arquivo de saida: "); 
	nomearq = leitor.next();
        Elemento elemento = new Elemento();
        elemento = arrayElementos[0];
       
       
        try {
                buffwrite = new BufferedWriter(new FileWriter(nomearq));
                
            while(i<tamanho)
            {
              elemento = arrayElementos[i].proxElemento;
              while(elemento != null)
              {
                name = elemento.getChave();
                phone = Get(elemento.getChave()).telefone;
                city = Get(elemento.getChave()).cidade;
                country = Get(elemento.getChave()).pais;  
                registro= name + "," +  phone + "," + city +","+ country + ",\n";
                System.out.print(registro);
                  try {
                          buffwrite.write(registro);
                  } catch (IOException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                  }
                  elemento = elemento.proxElemento;
                }
              i++;
              }
              
                buffwrite.close();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }

    }
}
