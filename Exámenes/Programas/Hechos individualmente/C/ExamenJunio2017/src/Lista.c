/*
 * Alumno:		Antonio J. Galán Herrera
 * Titulación:	Ingeniería Informática
 * Ordenador:	PC1105
 * DNI:			26808894w
 */

#include "Lista.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void crearLista(TLista *lista){
	*lista = NULL;
}

int recorrer(TLista lista, float n, int *repetido){
	*repetido = 0;										// Se empieza suponiendo que no está repetido.

	while(lista != NULL && *repetido == 0){
		if(lista->punto.x == n){						// Se encuentra el valor repetido y se indica.
			*repetido = 1;

			printf("Hola.");
		}

		lista = lista->sig;
	}

	return *repetido;
}

void insertarPunto(TLista *lista, struct Punto punto, int *ok){
	if(recorrer(*lista, punto.x, ok) == 0){								// "recorrer()" devuelve 0, lo que quiere decir que el punto no esta repetido.
		TLista ant, aux = *lista, nodo = malloc(sizeof(struct Nodo));

		nodo->punto = punto;											// Se crea el nodo a insertar, una vez se sabe que si se insertara un nodo.

		if(*lista == NULL){						// Primer nodo en caso de que la lista sea nula.
			*lista = nodo;

			*ok = 1;

		}else if(punto.x < (*lista)->punto.x){	// Primer caso: Mas pequeño que el primer nodo.
			(*lista)->sig = nodo;
			nodo->sig = aux->sig;

			*ok = 1;							// Se notifica que el nodo ha sido insertado.

		}else{									// Resto de casos: Mas pequeño que cualquiera, contando el ultimo nodo.
			ant = *lista, aux = aux->sig;

			while(aux != NULL && *ok == 0){		// El "while()" se ejecutara mientras que haya nodos o no se haya insertado el nuevo nodo.
				if(punto.x < aux->punto.x){				// Comprobacion para ver si el punto es menor que algun nodo (incluyendo el ultimo).
					nodo->sig = aux;
					ant->sig = nodo;
					ant = nodo;

					*ok = 1;					// Se notifica que el nodo ha sido insertado.

				}else if(aux->sig == NULL){				// Comprobacion para ver si el punto no es menor que algun nodo (es mayor que el ultimo).
					aux->sig = nodo;
					nodo->sig = NULL;

					*ok = 1;					// Se notifica que el nodo ha sido insertado.
				}

				aux = aux->sig;					// Actualizar los punteros al final del "while()" para seguir buscando.
				ant = ant->sig;
			}
		}

	}else{										// "recorrer()" devuelve 1, lo que quiere decir que esta repetido.

		*ok = 0;								// "*ok" se cambia a 0 ya que el punto no ha podido insertarse.
	}
}

void eliminarPunto(TLista *lista, float x,int *ok){
	if(recorrer(*lista, x, ok) == 1){			// "recorrer()" devuelve 1, lo que quiere decir que el punto esta en la lista.
		TLista ant, aux = *lista;

		if((*lista)->punto.x == x){				// Primer caso: Debe borrarse el primer nodo.
			*lista = (*lista)->sig;

			free(aux);							// Liberar de memoria.

		}else{									// Resto de casos: Debe borrarse cualquiera.
			ant = *lista, aux = aux->sig;

			while(aux != NULL && *ok == 0){
				if(aux->punto.x == x && aux->sig == NULL){	// Ultimo caso: Debe borrarse el ultimo nodo.
					ant->sig = NULL;

					free(aux);					// Liberar de memoria.

					*ok = 1;					// Se notifica que el nodo ha sido borrado.

				}else if(aux->punto.x == x){
					ant->sig = aux->sig;

					free(aux);					// Liberar de memoria.

					*ok = 1;					// Se notifica que el nodo ha sido borrado.
				}

				aux = aux->sig;					// Actualizar los punteros al final del "while()" para seguir buscando.
				ant = ant->sig;
			}
		}

	}else{

		*ok = 0;
	}
}

void mostrarLista(TLista lista){

	while(lista != NULL){

		printf("(%f,%f) ", lista->punto.x, lista->punto.y);
		fflush(stdout);

		lista = lista->sig;
	}
}

void destruir(TLista *lista){
	TLista aux;

	while(*lista != NULL){
		aux = (*lista)->sig;	// El auxiliar apunta al siguiente nodo.

		free(*lista);			// Se libera memoria, se elimina ese nodo.

		*lista = aux;			// El puntero "lista" apunta a donde apunta "aux".
	}
}

void leePuntos(TLista *lista, char * nFichero){
	FILE *file;
	struct Punto *punto = NULL;
	int ok;

	if((file = fopen(nFichero, "rt")) == NULL){
		perror("Error en el fichero.");

	}else{
		while(fread(punto, sizeof(struct Nodo), 1, file) > 0){

			insertarPunto(lista, *punto, &ok);
		}
	}
}
