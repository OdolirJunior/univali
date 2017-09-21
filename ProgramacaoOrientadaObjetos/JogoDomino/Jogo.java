package m1.odolir.domino;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Classe que controla a estrutura e andamento do jogo de dominó.
 * <p>
 * Armazena os jogadores, o monte, a mesa e variáveis de controle.
 * <p>
 * Controla o início e o fim da partida, o andamentos das rodadas, o início e o 
 * fim da partida.
 * <p>
 * Pode ser criada para uma partida de 2,3 ou 4 jogadores, com um jogador humano
 * ou como uma partida de demonstração (somente com jogadores simulados).
 * <p>
 * Termina quando um jogador ficar sem pedras, ou o jogo ficar trancado.
 * Esta classe calcula o fim de uma partida por trancamento.
 * <p>
 * Vence o jogo aquele jogador que terminar de pousar todas as peças de sua mão.
 * Caso o jogo termine trancado, o vencedor é aquele que tem o menr número de pontos 
 * somando todas as pedras de sua mão.
 * @author Odolir Junior <odolir@edu.univali.br> 
 * @author Luiz Eduardo Borgert Coelho <luiz.coelho@edu.univali.br>
 * @date   03/09/2017
 */
public class Jogo {


    /**
     * Número da rodada atual.
     */
    private int rodada;

    /**
     * Contém as 28 pedras do jogo de dominó.
     */
    private Domino monte;

    /**
     * Lista que recebe as peças dos jogadores.
     * Declarado com 28: total de pedras de um dominó.
     */
    private ArrayList<Peca> mesa = new ArrayList<Peca>(28);

    /**
     * Lista que acumula os jogadores ativos na partida.
     * definido como 4: valor máximo de jogadores.
     */
    private ArrayList<Jogador> jogadores = new ArrayList<Jogador>(4);

    /**
     * Número de jogadores ativos.
     */
    private int numJogadores;

    /**
     * Define se o jogo é uma simulação(true) ou tem um jogador real(false).
     */
    private boolean demonstracao;

    /**
     * Define se o jogo chegou ao fim(true) ou está em andamento(false).
     */
    private boolean fim;

    /**
     * Define o índice do arraylist jogadores que representa o jogador
     * da vez na rodada atual.
     */
    private int jogadorAtual;

    /**
     * Acumula os nomes a serem dados aos jogadores simulados.
     */
    private String jogadoresSimulacao[] = {"Roy Batty", "Leon Kowalski", "Pris Stratton", "Zhora Salome"};



    /**
     * Construtor de jogo. 
     * 
     * É chamado na classe rodaJogo, sendo passado para ele o numero de jogadores e se é uma demonstracao do jogo. 
     * 
     * -seta a rodada como 1
     * - seta o fim como falso
     * @param numJogadores 
     * @param demonstracao
     */
     public Jogo(int numJogadores,boolean demonstracao ){
        this.numJogadores = numJogadores;
        this.demonstracao = demonstracao;
        monte = new Domino();
        setRodada(1);
        setJogadores();
        setFim(false);
    }
     /**
     * setFim. 
     * 
     * seta o fim do jogo.
     * @param fim define o fim do jogo.
     */
    public void setFim(boolean fim){
        this.fim = fim;
    }
    /**
     * isFim. 
     * 
     * verifica se o jogo ja acabou.
     * @return
     */
    public boolean isFim(){
        return fim;
    }
  
    /**
     * setRodada. 
     * 
     * seta a rodada do jogo com um valor passado por parametro.
     * @param rodada novo valor para a rodada do objeto.
     */  
    public void setRodada(int rodada){
        this.rodada = rodada;
    }
     /**
     * getRodada(). 
     * 
     * pega a rodada do jogo.
     * @return o número da rodada em que a partida está.
     */   
    public int getRodada(){
        return rodada; 
    }
     /**
     * setJogadores(). 
     * 
     * - verifica se o jogo é uma demonstracao, caso seja seta os nomes predefinidos,  se nao, solicita o nome do usuario. 
     * - cria o jogador e salva no ArrayList de jogadores.
     */   
    public void setJogadores() {
        if(demonstracao == true){
           for(int i=0 ;i<numJogadores;i++){
               jogadores.add(new Jogador(jogadoresSimulacao[i],false));
           }
        }else{
            Scanner leitor = new Scanner(System.in);
            for(int i=0 ;i<numJogadores;i++){
                if(i==0){
                    System.out.println("Nome do jogador humano: ");
                    String nomeJogador = leitor.next();
                    jogadores.add(new Jogador(nomeJogador,true));
                }else{
                    jogadores.add(new Jogador(jogadoresSimulacao[i],false));
                }
            }
        }
    }
    
    /**
     * setJogadorAtual().
     * 
     * seta o jogador atual com um valor passado por parametro .
     * @param i novo valor para a variável jogadorAtual.
     */    
    public void setJogadorAtual(int i){
        jogadorAtual = i;
    }

    /**
     * distribui().
     * 
     * distribui as pecas do domino entre os jogadores.
     */
    public void distribui(){
        for(Jogador j : jogadores){
            for(int i=0; i<7; i++){
                
                j.setNovaPeca(monte.entregaPeca());
            }
        }
        
    }

    /**
     * compraUmaPeca().
     *  
     * passa  uma peça do monte para a mão do jogador atual.
     */    
    public void compraUmaPeca(){
        Peca p = monte.entregaPeca();
        System.out.print(p);
        jogadores.get(jogadorAtual).setNovaPeca(p);
        
    }    
     /**
     * printRodada().
     * 
     * exibe na tela a rodada atual e chama printJogador para exibir os 
     * dados do jogador atual.
     */   
    public void printRodada(){
        System.out.println("Rodada: "+getRodada());
        jogadores.get(jogadorAtual).printJogador();
    }
     
    /**
     * podeJogar().
     * 
     * verifica se o jogador atual tem uma peça que encaixe na mesa.
     * Caso tenha, será permitido jogar.
     * Caso não o tenha e o monte não esteja vazio, compra uma peça.
     * Isso se repete até que o jogador compre uma peça que se encaixe
     * ou o monte acabe. Ao final disso, caso não tenha encontrado peça adequada, 
     * o jogador passa a vez.
     * @return true se o jogador tem uma peça que encaixe, false caso não tenha
     */   
    public boolean podeJogar(){
        System.out.print("Compradas      : ");
        while(jogadores.get(jogadorAtual).temPeca(valorEsquerda(),valorDireita()) == false && monte.getSize() >0){
            compraUmaPeca();              
        }
        System.out.println("");
        if(jogadores.get(jogadorAtual).temPeca(valorEsquerda(),valorDireita())){
            return true;
        }
        return false;           
    }
   /**
     * pousaPeca. 
     *  
     * adiciona a peca escolhida na mesa, no lado adequado.
     * Por padrão, sempre que a peça encaixar no lado esquerdo,
     * a peça será encaixada nesse lado.
     * <p>
     * Lado esquerdo = mesa(index 0). Lado direito = mesa(lastIndex).
     * @param p peça a ser encaixada na mesa,
     */
    public void pousaPeca(Peca p){
        if(p.getLado2() == valorEsquerda()){
            mesa.add(0, p);
        }else
            mesa.add(p);
    }
    /**
     * trancou().
     * 
     * verifica se o jogo trancou, ou seja, nao é possivel jogar mais pecas.
     * Só é chamado quando há o mesmo valor nas duas pontas.Nesse contexto,
     * avalia se o total de pedras que contém o valor das pontas é igual a 7. 
     * @param ponta valor das duas pontas da mesa.
     * @return true se existem 7 pedras na mesa com o mesmo valor das pontas.
     *  false se o contrário.
     */ 
    public boolean trancou(int ponta){
        int conta = 0;
        for(Peca p: mesa){
            if(p.temLadoIgual(ponta)){
                conta++;
            }
        }
        if(conta == 7){
            return true;
        }else
            return false;
    }
     /**
     * contaPontosJogadores.
     * 
     * caso o jogo tenha sido trancado, as pecas que sobraram na mao dos jogadores
     * sao contadas e comparadas. O vencedor é o que somar menor numero de pontos.
     * Após definir o vencedor, seta seu índice como o jogador atual, para
     * que seja apresentado como vencedor
     */
    public void contaPontosJogadores(){
        int vencedor = 0;
        for(int j = 1; j < jogadores.size(); j++){
            if(jogadores.get(vencedor).contaPontos() < jogadores.get(j).contaPontos() )
                vencedor = j;
        }
        setJogadorAtual(vencedor);
    }
    /**
     * getJogadorAtual().
     * 
     * pega o jogador atual da rodada.
     * @return valor de jogadorAtual
     */
    public int getJogadorAtual(){
        return jogadorAtual;
    }
    /**
     * acabou().
     * 
     * verifica se o jogo terminou (se trancou ou se alguem bateu).
     * @return true se acabou, false caso contrário.
     */    
    public boolean acabou(){
        if(jogadores.get(jogadorAtual).maoVazia()){            
            return true;
        }else if(valorEsquerda() == valorDireita()){
                if(trancou(valorEsquerda())){
                    contaPontosJogadores();
                    return true;
                }
        }
        return false;
    }
    /**
     * fimDeJogo().
     * 
     * apresenta o jogador vencedor e altera fim para true.
     */
    public void fimDeJogo(){
        System.out.println("\n\n\n\n***** FIM DE JOGO *****");
        System.out.println("\n\n\n O jogador vencedor é: "+jogadores.get(getJogadorAtual()).getNome()+"\n\n\n" );
        setFim(true);
    }
     /**
     * Executa a jogada do jogador atual.
     * 
     * Pega a peca de do arrayList do jogador atual e  adiciona na mesa,
     * verificando se a peca encaixa e se o jogador pode jogar.
     * Caso seja a primeira rodada, o jogador entrega a maior peça;
     * Testa se é possível jogar. Caso não seja passa a vez ao proxi,mo.
     * caso o jogador seja humano, será perguntado qual peça deseja jogar;
     * caso seja simulado, o jogador entrega primeira peça valida.
     * Testa se o jogo chegou ao fim. Caso não, passa ao proximo jogador.
     */
    public void jogada(){
        printRodada();
        if(getRodada()==1){
            mesa.add(jogadores.get(jogadorAtual).getMaiorPeca());
        }else if(podeJogar()){
            if (jogadores.get(jogadorAtual).isHumano())
                pousaPeca(jogadores.get(jogadorAtual).escolhePeca(valorEsquerda(),valorDireita()));             
            else
                pousaPeca(jogadores.get(jogadorAtual).primeiraPecaValida(valorEsquerda(),valorDireita()));
        }  
        if(acabou()){
            fimDeJogo();
        }else{
            proximoJogador();
            setRodada(rodada + 1);
        }
        printMesa();
    }
     /**
     * valorEsquerda().
     * 
     * retorna o valor esquerdo da peca mais a esquerda da mesa.
     * @return o valor da ponta esquerda da mesa.
     */   
    public int valorEsquerda(){
       return mesa.get(0).getLado1();        
    }
     /**
     * valorDireita().
     * 
     * retorna o valor direito da peca mais a direito da mesa.
     * @return o valor da ponta direita da mesa.
     */
    public int valorDireita(){
        return mesa.get(mesa.size()-1).getLado2();
    }
    
            
    /**
     * setPrimeiroJogador().
     * 
     * encontra o jogador que tem a maior peca no arrayList mao e seta como
     * primeiro a jogar.
     */
    public void setPrimeiroJogador(){
        int primeiro = 0;
        int compara;
        Comparator<Peca> comp = Peca.ordemPecaComparator;
        for(int i = 1; i < jogadores.size(); i++){
            
            
            compara = comp.compare(jogadores.get(primeiro).getPeca(0),
                    jogadores.get(i).getPeca(0));
            if(compara > 0)
                primeiro = i;
         } 
            
        setJogadorAtual(primeiro);
    }
    /**
     * proximoJogador().
     * 
     * verifica qual é o proximo jogador na rodada e passa o seu indice para atualizar
     * o jogador atual.
     */    
    public void proximoJogador(){
        if (jogadorAtual == jogadores.size()-1)
            setJogadorAtual(0);
        else
            setJogadorAtual(jogadorAtual+1);
    }
     /**
     *
     * 
     * printa a mesa com todas as pecas jogadas e encaixadas de acordo com seus lados.
     */  
    public void printMesa(){
        System.out.print("Mesa           : ");
        if(!mesa.isEmpty()){
            for(Peca p: mesa){
                System.out.print(p);
            }
        }
        System.out.println("");
    }
              
}
