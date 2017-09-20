#include "Nodo.h"
#include <iostream>

template <typename T>
Nodo<T>::Nodo()
{
    this->setaProximo(NULL);
}
template <typename T>
Nodo<T>::Nodo(T elemento)
{
    this->setaElemento(elemento);
    this->setaProximo(NULL);
}
template <typename T>
Nodo<T>::~Nodo(){
}
template <typename T>
void Nodo<T>::setaElemento(T elemento)
{
    this->elemento = elemento;
}
template <typename T>
void Nodo<T>::setaProximo(Nodo* nodo)
{
    this->proximo = nodo;
}
template <typename T>
T Nodo<T>::pegaElemento()
{
    return this->elemento;
}
template <typename T>
Nodo<T>* Nodo<T>::pegaProximo()
{
    return this->proximo;
}


