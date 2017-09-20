package m1.odolir.luiz.domino;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * Jogador. 
 * 
 * A classe jogador possui metodos referente ao prorio jogador
 * e sua mao, nela sao verificadas informacoes como se o jogador tem peca,
 * qual a maior peca da mao, a contagem de pontos da sua mao e se a mao esta vazia.
 * @author Odolir Junior <odolir@edu.univali.br> 
 * @author Luiz Eduardo Borgert Coelho <luiz.coelho@edu.univali.br>
 * @date   03/09/2017
 */
public class Jogador {

    /**
     * Lista de peças do jogador.
     * Definido como 14, pois acima disso é impossível ficar sem opções de jogo.
     * 
     */
    private  ArrayList<Peca> mao = new ArrayList<Peca>(14);

    /**
     * Define se jogador é humano(true) ou simulado (false).
     * 
     */
    private boolean humano;

    /**
     * 
     */
    private String nome;


    /**
     * Construtor de Jogador.
     * 
     * Este construtor, cria o jogador, e pede se ele eh humano, bem como o seu nome.
     * @param nome nome do jogador
     * @param humano define se jogador é humano ou simulado.
     */
    
   public Jogador(String nome,boolean humano){
        this.nome = nome;
        this.humano = humano;
    }
    /**
     * setNovaPeca. 
     * 
     * Este metodo seta uma nova peca no arrayList  mao do jogador,
     * adicionando pecas para ele.
     * @param p nova peça a ser adicionada.
     */   
    public void setNovaPeca(Peca p){
        mao.add(p);
        if(mao.size()>0){
            Collections.sort(mao, Peca.ordemPecaComparator);

        }
        
    }    
     /**
     * getPeca. 
     * 
     * Retorna uma  determinada peca do arrayList de domino, sem removê-la.
     * @param i indice da peça que se deseja buscar na mão.
     * @return peça da mão do jogador.
     */   
    public Peca getPeca(int i){
        return mao.get(i);
    }    
    /**
     * printMao. 
     * 
     * Este metodo mostra a mao do jogador.
     */
   
    public void printMao(){
        System.out.print("Mão atual      : ");
        for(Peca str: mao){
            System.out.print(str);
        }
        System.out.println("");
    }
    
   /**
     * isHumano. 
     * 
     * retorna se o jogador eh humano ou simulado pela aplicacao.
     * @return o valor da variável humano
     */   
    public boolean isHumano() {
        return humano;
    }
    /**
     * getNome.
     * 
     * este metodo, traz o valor contido no atributo nome.
     * @return o nome do jogador.
     */
    public String getNome() {
        return nome;
    }
 
    /**
     * setHumano. 
     * 
     * seta a variavel humano, se ele eh humano ou nao.
     * @param humano valor que define se é humano ou simulado. 
     */
    public void setHumano(boolean humano) {
        this.humano = humano;
    }
 
    /**
     * setNome. 
     * 
     * seta o nome do jogador.
     * @param nome novo nome do jogador.
     */   
    public void setNome(String nome) {
        this.nome = nome;
    }
     /**
     * printJogador. 
     * 
     * apresenta na tela o nome do jogador e chama printMao para mostrar
     * a mão do jogador.
     */   
    public void printJogador(){
        System.out.println("Jogador        :  "+getNome());
        printMao();
    }
    /**
     * temPeca. 
     * 
     * Este metodo, realiza um for no arrayList de mao,
     * testando se a peça encaixa na mesa de jogo.
     * Para isso, compara se a peça atual tem lado igual ao valor da ponta
     * esquerda da mesa ou igual ao valor da ponta direita da mesa.
     * @param esquerda ponta esquerda da mesa.
     * @param direita ponta direita da mesa.
     * @return se o jogador tem ou não peça para esta rodada.
     */    
    public boolean temPeca(int esquerda, int direita){
        
        for(Peca p: mao){
            if (p.temLadoIgual(esquerda) || p.temLadoIgual(direita)){
                return true;
            }
        }
        return false;
    }

    /**
     * escolhePeca. 
     * 
     * Jogador humano escolhe qual peça de sua mão deseja pousar na mesa.
     * Verifica se a pedra escolhida encaixa na mesa. Caso não, pede que
     * ele escolha outra peça, até escolher uma que encaixe.
     * Foi definido pelo programador que esse método só é chamado depois de 
     * testar que o jogador tem pelo menos uma peça válida.
     * Mostra na tela a peça escolhida.
     * @param esquerda valor da ponta esquerda da mesa.
     * @param direita valor da ponta direita da mesa.
     * @return a peça escolhida pelo jogador para pousar na mesa.
     */
    public Peca escolhePeca(int esquerda, int direita){
        printJogador();
        Peca p = null;
        int pos ;
        Scanner leitor = new Scanner(System.in);
        do{
            System.out.println("Qual peça deseja jogar? (Posicao do array)");
            
            pos = leitor.nextInt(); 
            if(mao.get(pos).temLadoIgual(esquerda) || mao.get(pos).temLadoIgual(direita)){
                p = mao.get(pos);
                p.encaixa(new Peca(esquerda, direita));
                mao.remove(pos);
            }else
                System.out.println("Esta peça não encaixa na mesa.");
        }while(p == null);
        System.out.println("Peça escolhida : " + p);
        return p;
    }
   /**
     * getMaiorPeca().
     * Retorna a maior peca da mao do jogador. Como a mão é definida pela ordem 
     * decrescente do dominó, a maior peça sempre está no índice 0.
     * @return peça da posição 0 de mão.
     */
    public Peca getMaiorPeca(){
        Peca p = mao.get(0);
        mao.remove(0);
        System.out.println("Peça escolhida : " + p);
        return p;        
    }
    
     /**
     * primeiraPecaValida. 
     * 
     * Percorre a mão do jogador em índice crescente para pegar
     * a primeira peça que encaixe na mesa. Mostra na tela a peça escolhida.
     * @param esquerda  valor da ponta esquerda da mesa.
     * @param direita  valor da ponta direita da mesa.
     * @return a primeira peça da mão que encaixe na mesa.
     */
    public Peca primeiraPecaValida(int esquerda, int direita) {              
        int pos = 0;
        Peca p = null;
        do{
            if(mao.get(pos).temLadoIgual(esquerda) || mao.get(pos).temLadoIgual(direita)){                
                p = mao.get(pos);
                p.encaixa(new Peca(esquerda, direita));
                mao.remove(pos);
            }else          
                pos++;
        }while(p == null);
        System.out.println("Peça escolhida : " + p);
        
        return p;
    }
     /**
     * contaPontos().
     * Conta os pontos da mao do jogador, somando os pontos de cada pedra na mão.
     * @return total de pontos.
     */
    public int contaPontos(){
        int pontos = 0;
        for(Peca p: mao){
            pontos += p.getPontos();
        }
        return pontos;
    }
    
    /**
     * maoVazia().
     * 
     * Verifica se a mao do jogador esta vazia.
     * @return o valor do teste isEmpty no arraylist mão.
     */   
    public boolean maoVazia() {
        return mao.isEmpty();
    }
    
    










 
}