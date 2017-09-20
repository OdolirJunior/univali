#ifndef NODO_H_INCLUDED
#define NODO_H_INCLUDED
#include <iostream>

using namespace std;

template <typename T>
class Nodo{
    private:
        T elemento;
        Nodo<T> *proximo;
    public:
        Nodo();
        Nodo(T elemento);
        ~Nodo();
        void setaElemento(T elemento);
        void setaProximo(Nodo<T>* nodo);
        T pegaElemento();
        Nodo* pegaProximo();
};

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
#endif // NODO_H_INCLUDED
