#include "Lista.h"
#include <iostream>

using namespace std;


//criar uma lista
Lista::Lista(){
    this->numeroElementos = 0;
    for(int i = 0; i<100; i++){
        ocupados[i] = 0;
    }
}

//destruir uma lista
Lista::~Lista(){
    delete this->ocupados;
    delete this->elementos;
    this->numeroElementos = 0;
}

bool Lista::ehVazia(){
   return numeroDeElementos() == 0;
}

int Lista::numeroDeElementos(){
    return this->numeroElementos;
}


//verificar se um elemento existe na lista
bool Lista::existeElemento(char elemento){
    for(int i = 0; i<100; i++){
        if(ocupados[i] == 1){
            if(elementos[i] == elemento){
                return true;
            }
        }
    }
    return false;

}


// verificar se todos os elementos de uma lista existem em outra lista
bool Lista::existeElementos(Lista &outraLista){

    bool todos = true;
    for(int i=0;i<100;i++){
        if (this->ocupados[i]==1){
           if( !outraLista.existeElemento(this->elementos[i])){
                todos = false;
           }
        }
    }
    return todos;
}

// inserir um elemento na lista
void Lista::insere(char elemento){
    for(int i = 0; i<100; i++){
        if(ocupados[i] == 0){
            elementos[i] = elemento;
            ocupados[i] = 1;
            this->numeroElementos++;
            break;
        }
    }
}

//, inserir os elementos de uma lista em outra lista
void Lista::insereElementos(Lista &l){
    for(int i = 0; i<100; i++){
        if(ocupados[i] == 1){
            l.insere(this->elementos[i]);
        }
    }
}

// retirar um elementoda lista
void Lista::retira(int posicao){
    ocupados[posicao] = 0;
    numeroElementos--;
}

//mostrar uma lista
void Lista::mostra(){
    for(int i = 0; i<100; i++){
        if(ocupados[i]==1){
            cout<<i<<" - "<<elementos[i]<<endl;
        }
    }
}
Lista& Lista::operator*(){
    return *this;
}
