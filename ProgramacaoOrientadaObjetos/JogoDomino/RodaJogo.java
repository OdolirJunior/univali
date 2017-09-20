package m1.odolir.luiz.domino;

import java.util.Scanner;


/**
 * Criação e chamadas a execução da classe Jogo.
 * @author Odolir Junior <odolir@edu.univali.br> 
 * @author Luiz Eduardo Borgert Coelho <luiz.coelho@edu.univali.br>
 * @date   03/09/2017
 */
public class RodaJogo {

    /**
     * Default constructor
     */
    public RodaJogo() {
    }


    /**
     * criaJogo(). 
     * 
     * este metodo realiza as seguintes funcoes: 
     * - solicita a entrada de dados do jogador referente a quantidade de jogadores, simulado? e nome de jogador se necessario. 
     * 
     * - instancia um objeto do tipo Jogo 
     * - distribui as pecas do jogo. 
     * - seta o primeiro jogador.
     * @return um novo jogo.
     */
    public Jogo criaJogo() {
        int jogadores;
        Scanner leitor = new Scanner(System.in);
        boolean demonstracao;
        System.out.println("**** DOMINO ****");
        do{
            System.out.println("Numero de jogadores:");
            jogadores= leitor.nextInt();
            if(jogadores<2 || jogadores >4){
                System.out.println("Quantidade deve estar entre [2..4]");
            }
        }while(jogadores<2 || jogadores >4);
        System.out.println("Jogo de demonstração(true / false)? ");
        demonstracao = leitor.nextBoolean(); 
        Jogo   j1  = new Jogo(jogadores,demonstracao); 
        j1.distribui();
        System.out.println("**** Pecas entregas para os jogadores ****");        
        j1.setPrimeiroJogador();     
        return j1;
    }

    /**
     * iniciaJogo(). 
     * 
     * executa um loop verificando se o jogo acabou, caso seja falso, é dado continuidade na jogada.
     * Scanner leitor: espera o usuario pressionar qualquer tecla para seguir ao proximo passo do loop.
     * @param jogo 
     */
    public void iniciaJogo(Jogo jogo) {
        Scanner leitor = new Scanner(System.in);      
        while(!jogo.isFim()){
            jogo.jogada();
            System.out.println("\nPressione enter para seguir.");
            leitor.nextLine();          
        }
    }

    /**
     * run().
     * 
     * Executa os métodos de RodaJogo
     */
    public void run() {
        Jogo jogo = criaJogo();
        iniciaJogo(jogo);
    }

}