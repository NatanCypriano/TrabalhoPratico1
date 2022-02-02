
package com.mycompany.atvtabhash;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
/**
 *
 * @author Natan Cypriano e Matheus Costa
 */
public class main {
    static Scanner scanner = new Scanner(System.in);
    static int entradaMenu;
    static boolean fimExecucao;
    public static void main(String[] args) {
         fimExecucao = false;
         Scanner entradaTamanho = new Scanner(System.in);
         System.out.println("Definir tamanho do tabela; ");
         int tamanhoInput = entradaTamanho.nextInt();
        AgendaDeContatos agenda = new AgendaDeContatos(tamanhoInput); 
        
        
        
        while(!fimExecucao)//Loop do menu principal
        {
            
            System.out.println("1. Carregar arquivo");
            System.out.println("2. Localizar Contato sando como chave de busca o Nome Completo. ");
            System.out.println("3. Inserir Contato Novo  ");
            System.out.println("4. Excluir Contato  ");
            System.out.println("5. Atualizar Contato (Atualizar dados de Telefone, Cidade e Pais) ");
            System.out.println("6. Salvar Dados ");
            System.out.println("7. Fim do Programa ");
            entradaMenu = scanner.nextInt();
            switch (entradaMenu)
            {
            case 1://Carregar arquivo
                
               carregarArquivo(agenda);
               
                break;
            case 2://Localizar contato
                Scanner entradaLocalizar = new Scanner(System.in);
                System.out.println("Digite um nome: ");
                String entrada = entradaLocalizar.nextLine();
                
                Conteudo retorno =  agenda.localizarContato(entrada);
                
                if(retorno == null){//Caso não localize o registro
                    System.out.println("Não há registros para "+entrada);
                }else{
                    System.out.println("Registros para "+entrada);
                    System.out.println("Telefone: "+retorno.telefone);
                    System.out.println("Cidade: "+retorno.cidade);
                    System.out.println("Pais: "+retorno.pais);
                }
                break;
            case 3://Inserção
                Scanner entradaInserir = new Scanner(System.in);
                System.out.println("Digite um nome: ");
                String nome = entradaInserir.nextLine();
                
                System.out.println("Digite o telefone: ");
                String tel = entradaInserir.nextLine();
                
                System.out.println("Digite a cidade: ");
                String cidade = entradaInserir.nextLine();
                
                System.out.println("Digite o pais: ");
                String pais =  entradaInserir.nextLine();
                
                agenda.inserirContato(nome, tel, cidade, pais);
                break;
            case 4://Exclusão
                 Scanner entradaDeletar = new Scanner(System.in);
                 System.out.println("Digite um nome para excluir: ");
                String nomeDeletar = entradaDeletar.nextLine();
                agenda.excluirContato(nomeDeletar);
                break;
            case 5://Atualização
                Scanner entradaAtualizar = new Scanner(System.in);
                System.out.println("Digite um nome: ");
                String nomeA = entradaAtualizar.nextLine();
                
                System.out.println("Digite o telefone: ");
                String telA = entradaAtualizar.nextLine();
                
                System.out.println("Digite a cidade: ");
                String cidadeA = entradaAtualizar.nextLine();
                
                System.out.println("Digite o pais: ");
                String paisA =  entradaAtualizar.nextLine();
               agenda.atualizarContato(nomeA, telA, cidadeA, paisA);
                break;
            case 6://Salvar dados e disponibilizar informações
              agenda.salvarDados();
             
                break;
            case 7:
                encerrarPrograma();
                break;
            }
        }

    }
    
   
       
    public static void carregarArquivo(AgendaDeContatos agenda){
        String[] info;
        try
        {
            FileInputStream stream = new FileInputStream("entradaHash.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            while(linha != null) {
                info = linha.split(",");
                agenda.inserirContato(info[0], info[1], info[2], info[3]);
                linha = br.readLine();
                
            }
        } catch(IOException ex){
            System.out.println("Erro no arquivo");
        }
        System.out.println("Arquivo carregado com sucesso");
    }
    
    

    public static void encerrarPrograma(){
        fimExecucao = true;
    }
}
