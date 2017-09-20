#ifndef LISTA_H_INCLUDED
#define LISTA_H_INCLUDED

#include "Nodo.h"
#include <iostream>

using namespace std;

template <typename T>
class Lista
{
private:
    int numeroElementos;
    Nodo<T> *inicio;
public:
    Lista();
    ~Lista();
    bool ehVazia();
    int numeroDeElementos();
    bool existeElemento(T elemento);
    T getElemento(int posicao);
   // void insereNoInicio(T elemento);
   // void insereNoFinal(T elemento);
   // void retiraDoInicio();
   // void retiraDoFinal();
    void retiraElemento(T elemento);
    void mostra();
    //retirar os que retiram ou inserem somente no inicio ou no final
    void insereElemento(T elemento, int posicao);
    void retiraElementoPos(int posicao);
};
template <typename T>
Lista<T>::Lista()
{
    this->numeroElementos = 0;
    this->inicio = NULL;
}

template <typename T>
Lista<T>::~Lista()
{
    Nodo<T>* nodo;
    int i = numeroDeElementos();
    while(i!=0){
        nodo = this->inicio;
        this->inicio = this->inicio->pegaProximo();
        delete nodo;
        i--;
    }
}
template <typename T>
bool Lista<T>::ehVazia()
{
    return numeroDeElementos() == 0;
}
template <typename T>
int Lista<T>::numeroDeElementos()
{
    return this->numeroElementos;
}

template <typename T>
bool Lista<T>::existeElemento(T elemento)
{
    Nodo<T>* nodo = this->inicio;
    do
    {
        if (nodo->pegaElemento() == elemento)
            return true;
        nodo = nodo->pegaProximo();
    }while (nodo != this->inicio);
    return false;
}

template <typename T>
T Lista<T>::getElemento(int posicao)
{
    Nodo<T>* nodo = this->inicio;
    int i = 1;

    while (i != posicao)
    {
        nodo = nodo->pegaProximo();
        i++;
    }
    return nodo->pegaElemento();
}
template <typename T>
void Lista<T>::insereElemento(T elemento, int posicao){
     Nodo<T>* nodo = new Nodo<T>(elemento);
    if(ehVazia()){
        nodo->setaProximo(nodo);
        this->inicio = nodo;
    }
    else{
        Nodo<T>* aux = this->inicio;
        if(posicao == 1){
            while(aux->pegaProximo()!=this->inicio){
                aux = aux->pegaProximo();
            }
            aux->setaProximo(nodo);
            nodo->setaProximo(this->inicio);
            this->inicio = nodo;
        }else{
            int i = 2;
            while (i != posicao){
                aux = aux->pegaProximo();
                i++;
            }
            nodo->setaProximo(aux->pegaProximo());
            aux->setaProximo(nodo);

        }
    }
    numeroElementos++;
}

template <typename T>
void Lista<T>::retiraElementoPos(int posicao){
    if(ehVazia() || posicao>numeroDeElementos())
        return;
    else{
        Nodo<T>* aux = this->inicio;
        if(posicao == 1){
            while(aux->pegaProximo()!=this->inicio){
                aux = aux->pegaProximo();
            }
            aux->setaProximo(this->inicio->pegaProximo());
            aux = this->inicio;
            this->inicio = this->inicio->pegaProximo();
            delete aux;
        }else{
            int i = 2;
            while (i != posicao){
                aux = aux->pegaProximo();
                i++;
            }
            Nodo<T>* aux2 = aux->pegaProximo();
            aux->setaProximo(aux2->pegaProximo());
            delete aux2;

        }
        numeroElementos--;
    }
}
template <typename T>
void Lista<T>::retiraElemento(T elemento){
    if(ehVazia() || !existeElemento(elemento))
        return;
    else{
        Nodo<T>* aux = this->inicio;
        int i = 1;
        while( aux->pegaElemento() != elemento ){
            aux = aux->pegaProximo();
            i++;
        }
        retiraElementoPos(i);
    }
}

template <typename T>
void Lista<T>::mostra()
{
    Nodo<T>* nodo = this->inicio;

    do
    {
        cout << nodo->pegaElemento() << " , ";
        nodo = nodo->pegaProximo();

    }while (nodo != this->inicio);

}


#endif // LISTA_H_INCLUDED
