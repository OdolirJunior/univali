#include <iostream>
#include "Lista.h"
using namespace std;


int main(){
    int escolha = 0;
    char elemento;
    int posicao;
    int totalListas=0;
    int escolhaLista1, escolhaLista2;

    Lista* L[10];
    Lista* aux;
    do{
        cout<<"\n\n\n1 - Criar uma lista"<<endl;
        cout<<"2 - Destruir uma lista"<<endl;
        cout<<"3 - Verificar se um elemento existe na lista"<<endl;
        cout<<"4 - Verificar se todos os elementos de uma lista existem em outra lista"<<endl;
        cout<<"5 - Inserir um elemento na lista"<<endl;
        cout<<"6 - Inserir os elementos de uma lista em outra lista"<<endl;
        cout<<"7 - Retirar um elemento da lista"<<endl;
        cout<<"8 - Mostrar uma lista"<<endl;
        cout<<"9 - Sair"<<endl;
        cout<<"----------------------------------"<<endl;
        cout<<"Escolha uma das opcoes acima : ";
        cin>>escolha;
        switch(escolha){
            case 1:
                L[totalListas] = new Lista();
                totalListas++;
                cout<<"Lista criada na posicao "<<totalListas-1<<endl;
            break;
            case 2:
                if(totalListas>=2){
                    do{
                        cout<<"Lista para ser destruida: (posicao do array)";
                        cin>> escolhaLista1;

                    }while(escolhaLista1 >= totalListas );
                    delete L[escolhaLista1];
                    totalListas--;
                }else if(totalListas == 0){
                    cout<<"Nao ha listas criadas ainda." <<endl;
                    break;
                }else{
                    delete L[0];
                    totalListas--;
                }
                cout<<"Lista destruida!"<<endl;
                break;
            case 3:

                if(totalListas>=2){
                    do{
                        cout<<"Lista para pesquisar: (posicao do array)";
                        cin>> escolhaLista1;

                    }while(escolhaLista1 >= totalListas );
                }else if (totalListas == 0){
                    cout<<"Nao ha listas criadas ainda." <<endl;
                    break;
                }else{
                    escolhaLista1 = 0;
                }
                cout<<"Elemento que deseja procurar: ";
                cin>>elemento;
                if(L[escolhaLista1]->existeElemento(elemento)){
                        cout<<"Existe elemento"<<endl;
                }else{
                    cout<<"Nao existe elemento"<<endl;
                }
                break;
            case 4:
                    if(totalListas>=2){
                        do{
                            cout<<"Lista para  ser comparada : (posicao do array)";
                            cin>> escolhaLista1;
                            cout << "Lista para comparar: (posicao do array)";
                            cin>> escolhaLista2;
                        }while(escolhaLista1 >= totalListas || escolhaLista2>= totalListas);

                        if(L[escolhaLista1]->existeElementos(*L[escolhaLista2])){
                                cout << "Todos os elementos existem."<<endl;
                           }else{
                                cout<< "Alguns ou todos os elementos nao existem"<< endl;
                           }
                    }else{
                        cout<<"Sao necessarias duas listas pelo menos."<<endl;
                    }
                break;
            case 5:
                if(totalListas>=2){
                    do{
                        cout<<"Lista para inserir elemento: (posicao do array)";
                        cin>> escolhaLista1;

                    }while(escolhaLista1 >= totalListas );
                }else if (totalListas == 0){
                    cout<<"Nao ha listas criadas ainda." <<endl;
                    break;
                }else{
                    escolhaLista1 = 0;
                }

                cout<<"Elemento que deseja inserir: ";
                cin>>elemento;
                L[escolhaLista1]->insere(elemento);
                break;
            case 6:
                 if(totalListas>=2){
                        do{
                            cout<<"Lista para receber copia: (posicao do array)";
                            cin>> escolhaLista1;
                            cout << "Lista a ser copiada: (posicao do array)";
                            cin>> escolhaLista2;
                        }while(escolhaLista1 >= totalListas || escolhaLista2>= totalListas);
                        aux = L[0];
                        L[escolhaLista1]->insereElementos(*L[escolhaLista2]);
                    }else{
                        cout<<"Sao necessarias duas listas pelo menos."<<endl;
                    }
                break;
            case 7:
                if(totalListas>=2){
                    do{
                        cout<<"Lista para retirar elemento: (posicao do array)";
                        cin>> escolhaLista1;

                    }while(escolhaLista1 >= totalListas );
                }else if(totalListas == 0){
                    cout<<"Nao ha listas criadas ainda." <<endl;
                    break;
                }else{
                    escolhaLista1 = 0;
                }
                cout<<"Posicao que deseja retirar: ";
                cin>>posicao;
                L[escolhaLista1]->retira(posicao);
                break;
            case 8:
                if(totalListas>=2){
                    do{
                        cout<<"Lista para mostrar: (posicao do array)";
                        cin>> escolhaLista1;

                    }while(escolhaLista1 >= totalListas );
                }else if(totalListas == 0){
                    cout<<"Nao ha listas criadas ainda." <<endl;
                    break;
                }else{
                    escolhaLista1 = 0;
                }
                L[escolhaLista1]->mostra();
                break;
            case 9:
                break;
        }
    }while(escolha != 9);

    return 1;

}
