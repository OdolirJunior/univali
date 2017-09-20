package m1.odolir.luiz.domino;


import java.util.Comparator;

/**
 * Peca. 
 * 
 * A classe peça,  armazena os lados de uma peça. 
 * Tambem existem metodos de contagem de pontos da peça, e comparações de ordem 
 * das peças de acordo com as regras de negócio do jogo de dominó.
 * @author Odolir Junior <odolir@edu.univali.br> 
 * @author Luiz Eduardo Borgert Coelho <luiz.coelho@edu.univali.br>
 * @date   03/09/2017
 */
public class Peca {
    /**
     * Pontos de lado 1 de peça
     */
    private int lado1;
     /**
     * Pontos de lado 2 de peça
     */
    private int lado2;

    /**
     * Construtor Peca. 
     * 
     * O construtor dessa classe eh chamado na classe domino, para que seja possivel criar todas as pecas do domino.
     * @param i 
     * @param j
     */
    public Peca(int i, int j){
        this.lado1 = i;
        this.lado2 = j;
    }
     
    /**
     * getLado1().
     * 
     * Retorna o lado esquerdo da peca
     * @return pontos no lado1 da peça.
     */
    public int getLado1() {
        return lado1;
    }
    /**
     * setLado1().
     * 
     * seta o lado esquerdo da peca, passando o valor a ser setado.
     * @param lado1 valor a ser aplicado no lado 1 da peça.
     */
    private void setLado1(int lado1) {
        this.lado1 = lado1;
    }
    /**
     * getLado2().
     * 
     * Retorna o lado direito da peca
     * @return pontos no lado2 da peça.
     */
    public int getLado2() {
        return lado2;
    }
   /**
     * setLado2().
     * 
     * seta o lado direito da peca, passando o valor a ser setado.
     * @param lado2 valor a ser aplicado no lado 2 da peça
     */
    private void setLado2(int lado2) {
        this.lado2 = lado2;
    }

    /**
     * getPontos().
     * 
     * Retorna o total de pontos da pedra.
     * @return o valor dos pontos somados.
     */    
    public int getPontos(){
        return lado1+lado2;
    }
    /**
     * toString(). 
     * 
     * Metodo redefinido de toString(), para apresentar a peca no formato
     * [ lado1 - lado2 ]
     * @return uma String que representa o objeto.
     */    
    @Override
    public String toString() {
        return "[" + getLado1() + "-" + getLado2() + "]";
    }
        
    



     /**
     * trocaLados().
     * 
     * altera os lados da peca, utilizado para encaixar a peca na mesa.
     */
    public void trocaLados(){
        int temp = lado1;
        setLado1(lado2);
        setLado2(temp);
        
    }
     /**
     * temLadoIgual().
     * 
     * verifica se um dos lados desta peça contém o valor passado por parametro.
     * Utilizado para definir se esta peça se encaixa na mesa.
     * @param valor é utilizado para comparar com os dois lados da peça.
     * @return  true se um dos lados é igual valor, false se o contrário.
     */   
   public boolean temLadoIgual(int valor){
        return getLado1() == valor || getLado2() == valor;
    }
    /**
     * encaixa().
     * 
     * Verifica a necessidade de girar a peça para encaixar na mesa.
     * Só é utlizado após definir se esta peça é válida para  mesa atual.
     * @param compara Pedra criada artificialmente pelo chamador, 
     * com os valores das pontas da mesa.
     */
    public void encaixa(Peca compara) {
        if(getLado1() == compara.getLado1() || getLado2() == compara.getLado2()){
            trocaLados();
        }
    }
    /**
     * ehCareta().
     * 
     * verifica se uma determinada pedra eh uma carreta através do teste de igualdade 
     * dos lados.
     * @return true se a peça tem dois lados iguais, false se o contrário.
     */
    public boolean ehCarreta(){
        return lado1 == lado2;
    }
 
    /**
     * ordemPecaComparator. 
     * 
     * esta interface de Comparator, possui uma redifinicao do metodo compare.
     */   
    public static Comparator<Peca> ordemPecaComparator = new Comparator<Peca>(){
      /**
     * Redefinição do método compara, da interface Comparator. 
     * Cria um novo método de comparação, adequado à comparação de peças de 
     * dominó.
     */ 
        @Override
        public int compare(Peca p1, Peca p2){
        int maior = 0;
        if(p1.ehCarreta() && !p2.ehCarreta())
            maior = -1;
        else if(!p1.ehCarreta() && p2.ehCarreta()){
            maior = 1;
        }else if(p1.ehCarreta() && p2.ehCarreta()){
            maior = p2.getLado1() - p1.getLado1();
        }else if(p1.getPontos() != p2.getPontos()){
            maior = p2.getPontos() - p1.getPontos();
        }else if(p1.getPontos() == p2.getPontos()){
            maior = p2.getLado1() - p1.getLado1();
        }
        return maior;
    }}; 

}