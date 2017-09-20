#include <iostream>
#include <ctime>
#include<string>
#include<stdlib.h>
#include "Lista.h"
#include "Nodo.h"
using namespace std;
void iniciaCartas(bool cartasEntregues[]);
void testeListaCircular();
void jogoBlackJack();
int calculaPontos(Lista<int>* mao, bool ehHumano);
int compraCarta(bool cartasEntregues[]);
void printCarta(int carta);
int main()
{
    time_t qTime;
    time(&qTime);
    srand(qTime);
    int op;
    int opmenu;
    do{
        cout <<"\n\n\nLista Circular - menu"<<endl;
        cout<<"1 - rodar teste para lista circular"<<endl;
        cout<<"2 - Jogar Blackjack" <<endl;
        cout<<"Outro numero - Sair"<<endl;
        cout<<"------------------------------------"<<endl;
        cout<<"escolha uma das opcoes acima:  ";
        cin>>opmenu;
        if(opmenu == 1){
            testeListaCircular();
        }else if(opmenu == 2){
            do{
                jogoBlackJack();
                cout << "Jogar novamente? 1 - Sim / !1 - Nao."<<endl;
                cin>>op;
            }while(op==1);
        }
    }while(opmenu==1 || opmenu==2);
    return 0;
}
void testeListaCircular(){


     cout << "\n\nLista dinamica circular generica. Modelo de teste com duas listas: \n" <<endl;
    cout << "\n1 - Lista int; \n 2 - Lista char" << endl;
    Lista<int>* listaInt = new Lista<int>();
    Lista<char>* listaChar  = new Lista<char>();
    cout << "\n\nFuncao ehVazia()" << endl;
    cout << "\n1 - Lista int: ";
    if (listaInt->ehVazia()){
        cout << "vazia";
    }
    cout << "\n2 - Lista char: ";
    if (listaChar->ehVazia()){
        cout << "vazia";
    }
    cout << "\nFuncao insereElemento(pos = 1)" << endl;
    for (int i =0; i<=5; i++){
        listaChar->insereElemento(65+i, 1);
    }
    cout << "\nFuncao insereElemento(pos = fim)" << endl;
       for (int i =0; i<=6; i++){
        listaInt->insereElemento(i,i+1);
    }
    cout << "\nFuncao insereElemento(76, pos = 3)" << endl;
        listaInt->insereElemento(76,3);
    cout << "\nFuncao insereElemento('Z', pos = 5)" << endl;
        listaChar->insereElemento('Z',5);
    cout << "\n\nFuncao numeroDeElementos()" << endl;
    cout << "\n1 - Lista int: ";
    cout << listaInt->numeroDeElementos() << endl;

    cout << "\n2 - Lista char: ";
    cout << listaChar->numeroDeElementos() << endl;
       cout << "\n\nFuncao mostra()" << endl;
    cout << "\n1 - Lista int: ";
    listaInt->mostra();
    cout << endl;
    cout << "\n2 - Lista char: ";
    listaChar->mostra();
    cout << endl;
    cout << "\n\nFuncao retiraElemento(A) - Lista char" << endl;
    listaChar->retiraElemento('A');
    cout << "\nFuncao retiraElementoPos(2) - Lista int" << endl;
    listaInt->retiraElementoPos(2);
       cout << "\n\nFuncao mostra()" << endl;
    cout << "\n1 - Lista int: ";
    listaInt->mostra();
    cout << endl;
    cout << "\n2 - Lista char: ";
    listaChar->mostra();
    cout << endl;

    cout << "\n1 - Lista int: ";
    cout << listaInt->numeroDeElementos() << endl;
    cout << "\n2 - Lista char: ";
    cout << listaChar->numeroDeElementos() << endl;
    cout << "\n\nFuncao existeElemento('E') - Lista char:" << endl;
    if(listaChar->existeElemento('E')){
        cout<< "existe !" << endl;
    }else
        cout << "Nao existe! " <<endl;
    cout << "\nFuncao existeElemento(77) - Lista int:" << endl;
    if(listaInt->existeElemento(77)){
        cout<< "existe !" << endl;
    }else
        cout << "Nao existe! " <<endl;
    cout << "\n\nFuncao mostra()" << endl;
    cout << "\n1 - Lista int: ";
    listaInt->mostra();
    cout << endl;
    cout << "\n2 - Lista char: ";
    listaChar->mostra();
    cout << endl;
}

void jogoBlackJack(){
    system("cls");
    bool cartasEntregues[52];
    iniciaCartas(cartasEntregues);
    Lista<int>* dealer = new Lista<int>();
    Lista<int>* jogador = new Lista<int>();
    bool comprou;
    int op;
    dealer->insereElemento(compraCarta(cartasEntregues),1);
    dealer->insereElemento(compraCarta(cartasEntregues),2);
    jogador->insereElemento(compraCarta(cartasEntregues),1);
    jogador->insereElemento(compraCarta(cartasEntregues),2);
    int pontosJ = calculaPontos(jogador,true);
    int pontosD = calculaPontos(dealer,false);
    do{
        comprou = false;
        cout << "*******************************************************" << endl;
        for(int i = 0; i<5;i++){
            cout << (char)259<<(char)260<<(char)261<<(char)262;
        }
        cout <<"   BLACKJACK   ";
        for(int i = 0; i<5;i++){
            cout << (char)259<<(char)260<<(char)261<<(char)262;
        }
        cout << "\n*******************************************************" << endl;
        cout << "Dealer:   ";
        printCarta(dealer->getElemento(1));
        for(int i = 2; i<=dealer->numeroDeElementos(); i++){
            cout << " XX ";
        }
        cout << "\n\n Voce:   ";
        for(int i = 1; i<=jogador->numeroDeElementos(); i++){
            printCarta(jogador->getElemento(i));
        }
        cout<< "\n Total de pontos:  " << pontosJ << endl;
        if(pontosD <17){
            dealer->insereElemento(compraCarta(cartasEntregues),dealer->numeroDeElementos()+1);
            comprou = true;
        }
        cout<< "(1) Compra mais ou (!1) mantem?"<<endl;
        cin>> op;
        if(op == 1){
            jogador->insereElemento(compraCarta(cartasEntregues),jogador->numeroDeElementos()+1);
            comprou = true;
        }

        pontosJ = calculaPontos(jogador,true);
        pontosD = calculaPontos(dealer,false);
        system("cls");
    }while(comprou && pontosJ<22);



    cout << "*******************************************************" << endl;
    for(int i = 0; i<5;i++){
        cout << (char)259<<(char)260<<(char)261<<(char)262;
    }
    cout <<"   BLACKJACK   ";
    for(int i = 0; i<5;i++){
        cout << (char)259<<(char)260<<(char)261<<(char)262;
    }
    cout << "\n*******************************************************" << endl;
    cout<<"\n\n\n FIM DE JOGO: \n\n"<<endl;

    cout << "Dealer:   ";
    for(int i = 1; i<=dealer->numeroDeElementos(); i++){
            printCarta(dealer->getElemento(i));
    }
    cout<< "\n Total de pontos:  " << pontosD << endl;
    cout << "\n\n Voce:   ";
    for(int i = 1; i<=jogador->numeroDeElementos(); i++){
            printCarta(jogador->getElemento(i));
    }
    cout<< "\n Total de pontos:  " << pontosJ << endl;

    if(pontosJ > 21 )
        cout<<"\n A casa venceu!" << endl;
    else if(pontosD > 21 )
        cout << "\n Voce venceu!!!" << endl;
    else if (pontosD >= pontosJ)
           cout<<"\n A casa venceu!" << endl;
    else
        cout << "\n Voce venceu!!!" << endl;

}
int compraCarta(bool cartasEntregues[]){
    int novaCarta = 1;
    bool cartaRepetida = true;
    do{
        novaCarta = (rand()%52);
        if(!cartasEntregues[novaCarta])
            cartaRepetida = false;

    }while(cartaRepetida);
    return novaCarta;
}

void printCarta(int carta){
    int valorC = (carta % 13);
	if (valorC == 0) {
		cout << 'A';
	} else if (valorC < 10) {
		cout << (valorC + 1);
	} else if (valorC == 10) {
		cout << 'J';
	} else if (valorC == 11) {
		cout << 'Q';
	} else {
		cout << 'K';
	}
	int naipe = (carta/13);
	if (naipe == 0) {
		cout <<(char) 259;
	} else if (naipe == 1) {
		cout << (char)260;
	} else if (naipe == 2) {
		cout << (char)261;
	} else {
		cout << (char)262;
	}
	cout<<"  ";
}

void iniciaCartas(bool cartasEntregues[]){
    for (int i = 0; i<52; i++){
        cartasEntregues[i] = false;
    }
}
int calculaPontos(Lista<int>* mao, bool ehHumano){
    int totalCartas = mao->numeroDeElementos();
    int carta;
    int ases = 0;
    int valorC;
    int pontos = 0;
    int op;
    for(int i = 1; i<=totalCartas; i++){
        carta = mao->getElemento(i);
        valorC = (carta%13);
        if(valorC == 0){
            ases++;
            pontos++;
        }else if(valorC <9){
            pontos = pontos + valorC+1;
        }else{
            pontos +=10;
        }
    }
    while (ases > 0) {
		if(ehHumano){
            cout<<"Comprou um As!"<<endl;
            cout<<"Voce tem  " << pontos <<"  pontos. "<<endl;
            cout <<"\nDigite (1) para manter 1 ponto ou (!1) para valer 11 pontos."<<endl;
            cin>>op;
            if(op != 1)
                pontos += 10;
		}else if (pontos + 10 <= 21){
                pontos += 10;
		}
		--ases;
	}
	return pontos;
}

