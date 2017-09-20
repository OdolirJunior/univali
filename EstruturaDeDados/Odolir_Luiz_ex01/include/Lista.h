#ifndef LISTA_H
#define LISTA_H

class Lista {
    private:
        int numeroElementos;
        char elementos [100];
        int ocupados [100];
    public:
        Lista();
        ~Lista();
        bool ehVazia();
        int numeroDeElementos();
        bool existeElemento(char elemento);
        bool existeElementos(Lista &outraLista);
        void insere(char elemento);
        void insereElementos(Lista &l);
        void retira(int posicao);
        void mostra();
        Lista& operator *();

};
#endif // LISTA_H
