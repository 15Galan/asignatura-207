/*
 * Principal.c
 *
 *  Created on: 14/6/2016
 *      Author: esc
 */

#include "ListaJugadores.h"
#include <stdio.h>

// Lee el fichero y lo introduce en la lista
void cargarFichero (char * nombreFich, TListaJugadores *lj){
    FILE *file;
    unsigned linea, i, leido = 0;

    if((file = fopen(nombreFich, "rb")) == NULL){

        perror("ERROR: Fichero inexistente.");
    }

    while(leido != 0){

    	for(i = 0; i < 2; i++){
    		fread(&linea, sizeof(unsigned int), 1, file);
    	}

    	insertar(&(*lj), linea);
        printf("%d", linea);

        leido = fread(&linea, sizeof(unsigned int), 1, file);
    }

    fclose(file);
}


int main(){

	TListaJugadores lj;
	crear(&lj);
    unsigned int num_goles;
	cargarFichero ("goles.bin",&lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	recorrer(lj);
	fflush(stdout);
	printf("Introduce un número de goles: \n");
	fflush(stdout);
	scanf("%d",&num_goles);


	eliminar(&lj,num_goles);
	printf("--------------------------------------\n");
	recorrer(lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	printf ("El jugador que más goles ha marcado es el que tiene ID: %d",maximo(lj));
	fflush(stdout);
	destruir (&lj);

	return 0;
}
