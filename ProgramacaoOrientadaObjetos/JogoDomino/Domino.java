package m1.odolir.luiz.domino;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Domino. 
 * 
 * Esta classe armazena todas as informações referente ao domino. 
 * O construtor dessa classe é quem gera as pecas do domino. 
 * Nela tambem é feito a mistura das pedras, para que se possa iniciar o jogo. 
 * O jogador tambem recebe a peca, atravez de um metodo dessa classe.
 * @author Odolir Junior <odolir@edu.univali.br> 
 * @author Luiz Eduardo Borgert Coelho <luiz.coelho@edu.univali.br>
 * @date   03/09/2017
 */
public class Domino {



    /**
     * Lista de peças que formam um dominó.
     * Tamanho definido como 28, tamanho padrão de um dominó.
     */
     private ArrayList<Peca> domino = new ArrayList<Peca>(28);



    /**
     * CONSTRUTOR DE DOMINO.
     * Este construtor realiza dois for,  para inserir no ArrayList de dominos, cada peca.
     * Chama o método mistura.
     */
      public Domino(){
        for(int i = 0; i<=6; i++){
            for(int j = i; j<=6; j++){
                domino.add(new Peca(i,j)) ;
            }
        }
        mistura();
    }      
   /**
     * MISTURA.
     * Utiliza o metodo nativo "shuffle" da classe Collection, para misturar as pecas do domino.
     * @return
     */    
    public void mistura(){
        Collections.shuffle(domino);
    }
     /**
     * getPecaDomino.
     * Este metodo,retorna uma determinada peca do domino
     * @param i 
     * @return uma peca de domino
     */           
    public Peca getPecaDomino (int i){
        return domino.get(i);
    }
    
    public void removePecaDomino(int i){
        domino.remove(i);
    }
    /**
     * getSize.
     * 
     * retorna o tamanho do arrayList de dominos.
     * @return quantas peças ainda existem em domino.
     */  
    public int getSize(){
        return domino.size();
    }
    /**
     * entregaPeca().     
     * 
     * remove a peça na posição 0 de dominó e retorna ao chamador.   * 
     * 
     * @return a peça que estava na posição 0 de dominó.
     */    
    public Peca entregaPeca(){
        Peca temp = domino.get(0);
        domino.remove(0);
        return temp;
    }

}