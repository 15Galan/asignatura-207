#include "Mprocesos.h"
#include <stdio.h>
#include <stdlib.h>

void Crear(LProc *lista) {

	*lista = NULL;
}

void AnadirProceso(LProc *lista, int idproc) {
	LProc nodo = malloc(sizeof(struct TProceso));

	nodo->idproc = idproc;

	if(*lista == NULL){				// Lista vacia.
		*lista = nodo;
		nodo->sig = *lista;

	}else{							// Lista con elementos.
		nodo->sig = (*lista)->sig;
		(*lista)->sig = nodo;

		*lista = (*lista)->sig;		// Actualizar la ultima posicion.
	}
}

void EjecutarProcesos(LProc lista) {
	if(lista == NULL){					// Lista vacia.
		printf("Lista vacia.\n");

	}else if(lista->sig == lista){		// Lista con un solo elemento.
		printf("[ %d ]", lista->idproc);

	}else{								// Lista con mas de un elemento.
		LProc aux = lista->sig;

		do{
			printf("[ %d ]-->", aux->idproc);
			aux = aux->sig;

		}while(aux != lista->sig);		// "do-while" para ejecutar el primer paso.
	}

	printf("\n");
}

void EliminarProceso(int id, LProc *lista) {
	if(*lista != NULL){					// Por si acaso, aunque "supongan".
		int eliminado = 0;
		LProc aux = (*lista)->sig;

		if((*lista)->idproc == id && (*lista)->sig == *lista){	// Lista con un solo nodo.
			free(*lista);

			*lista = NULL;

		}else if((*lista)->idproc == id){	// Borrar el ultimo de la lista.
			LProc ant = (*lista)->sig;

			while(ant->sig != *lista){
				ant = ant->sig;
			}

			ant->sig = aux;
			free(*lista);
			*lista = ant;

		}else{						// Borrar un nodo interior.
			LProc ant = *lista;

			do{
				if(aux->idproc == id){
					ant->sig = aux->sig;
					free(aux);
					aux = ant->sig;

					eliminado = 1;
				}

				aux = aux->sig;

			}while(aux != *lista && eliminado == 0);
		}
	}
}

void EscribirFichero (char *nomf, LProc *lista) {


}
