
package com.mycompany.atvtabhash;

/**
 *
 * @author Natan Cypriano e Matheus Costa
 */
public class Elemento {//o Elemento é um registro (chave+informação) da hash table
    String chave = null;
    Conteudo conteudo = null;
    Elemento proxElemento;//Cada elemento irá apontar para o elemento a sua frente em casa de colisão, formando uma linked list
    
    public Elemento(String _chave,Conteudo _conteudo){
        chave = _chave;
        conteudo = _conteudo;
        proxElemento = null;
    }
    
    public Elemento(){
        proxElemento = null;
    }
    
    public String getChave(){
        return chave;
    }
    
    public Conteudo getConteudo(){
        return conteudo;
    }
}
