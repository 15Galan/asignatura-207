#include "ListaJugadores.h"
#include <stdio.h>

// Crea una lista vacía (sin ningún nodo)
void crear(TListaJugadores *lj){
    *lj = NULL;
}

// Devuelve el número de nodos de la lista
int longitud(TListaJugadores lj){
    int cont = 0;

    while(lj != NULL){

        cont++;
    }

    return cont;
}

// Inserta un nuevo jugador en la lista de jugadores, poniendo 1 en el número
// de goles marcados. Si ya existe añade 1 al número de goles marcados.
void insertar(TListaJugadores *lj, unsigned int id){
    TListaJugadores aux, nodo = malloc(sizeof(struct TNodo));;
    int i;

    if(*lj == NULL){                                        // Lista vacía: añadir primer elemento


        nodo->jugador = id;
        nodo->goles += 1;

        *lj = nodo;

    }else{                                                  // Lista no vacía: insertar ordenado
        aux = (*lj)->sig;

        for(i = 0; i < longitud(*lj); i++){
            if((*lj)->jugador == id){                               // Insertar existente
                (*lj)->goles++;

            }else if(((*lj)->jugador > id) && (id > aux->jugador)){                         // Insertar nuevo
                aux = *lj;

                nodo->jugador = id;
                nodo->goles++;
                nodo->sig = aux;

                *lj = nodo;

            }else{

            *lj = (*lj)->sig;
        }
    }
}

// Recorre la lista de jugadores escribiendo los identificadores y los goles
// marcados
void recorrer(TListaJugadores lj){
    while(lj != NULL){
        printf("Jugador: %d\n", lj->jugador);
        printf("Goles  : %d\n", lj->goles);
        lj = lj->sig;
    }
}


// Eliminar. Toma un número de goles como parámetro y elimina todos los
// jugadores que hayan marcado menos que ese número de goles
void eliminar(TListaJugadores *lj,unsigned int n){
    TListaJugadores ljsig = (*lj)->sig;
    int i;

    for(i = 0; i < longitud(*lj); i++){

        if((*lj)->goles < n && ljsig != NULL){
            *lj = NULL;
            *lj = ljsig;
            ljsig = (*lj)->sig;

        }else if (ljsig->goles < n){
            ljsig = NULL;
        }
    }
}

// Devuelve el ID del máximo goleador. Si la lista está vacía, devuelve 0. Si
// hay más de un jugador con el mismo número de goles que el máximo devuelve
// el de mayor ID. Hay que devolver el identificador, no el número de goles
// que ha marcado
unsigned int maximo(TListaJugadores *lj){
    int max = 0;

    if(*lj != NULL){    // if(longitud(*lj) != 0){
        if((*lj)->goles >= max){
            max = (*lj)->jugador;
        }
    }

    return max;
}

//Destruye la lista y libera la memoria)
void destruir(TListaJugadores *lj){

    if(*lj != NULL){

        destruir(&(*lj)->sig);
        free(*lj);
    }
}}

