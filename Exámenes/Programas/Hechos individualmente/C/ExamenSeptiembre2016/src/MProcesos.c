#include "Mprocesos.h"
#include <stdio.h>

void Crear(LProc *lista){
	*lista = NULL;
}

void AnadirProceso(LProc lista, int idproc){
	LProc aux;
	aux = malloc(sizeof(struct TProceso));

	if(lista == NULL){		// Añadir primer proceso
		aux->idproceso = idproc;
		aux->sig = lista;

	}else{					// Añadir cualquier proceso
		aux->idproceso = idproc;
		aux->sig = lista->sig;

		lista->sig = aux;
	}
}

void EjecutarProcesos(LProc lista){
	LProc aux = lista->sig;

	do{
		printnf("[%i] ", aux->idproceso);
		aux = aux->sig;

	}while(aux != lista->sig);
}

void EliminarProceso(int id, LProc *lista){
	LProc ant, aux = *lista;

	if(id == (*lista)->idproceso){			// Eliminar primer proceso
		ant = *lista;

		while(ant->idproceso != (*lista)->idproceso){
			ant = ant->sig;
		}

		ant->sig = (*lista)->sig;
		free(*lista);
		*lista = ant;

	}else{								// Eliminar cualquier proceso

		while(aux->idproceso != id){
			ant = aux;
			aux = aux->sig;
		}

		ant->sig = aux->sig;
		free(aux);
	}
}
/*
void EscribirFichero (char * nomf, LProc *lista){
	FILE *file;
	unsigned linea, i, leido = 0;

	if((file = fopen(nomf, "wb")) == NULL){
		perror("ERROR: El fichero no existe.");
	}
}
*/
