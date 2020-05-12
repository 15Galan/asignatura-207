/*
 * Principal.c
 *
 *  Created on: 16 jun. 2017
 *      Author: mmar
 */

#include "lista.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


float f(float x){
	return x*x -625;
}


int main(){

	srand(time(NULL));

	int ok;
	int i = 0;
	TLista l;
	crearLista(&l);

    int v = 10;

    printf("Numero de puntos: %d\n",v);

    struct Punto punto;
    for (i=0;i<v;i++){
    		punto.x = 100 * ((float)rand() / (float)RAND_MAX);
    		punto.y = f(punto.x);
    		printf("Introduce el punto (%.2f, %.2f)\n",punto.x,punto.y);
    		insertarPunto(&l,punto,&ok);
    		printf("%d\n", i);
    }
    /*
    punto.x = 25;
    punto.y = f(punto.x);
    printf("Introduce el punto (%.2f, %.2f)\n",punto.x,punto.y);
    insertarPunto(&l,punto,&ok);
    mostrarLista(l);

    eliminarPunto(&l,25,&ok);
    mostrarLista(l);
    destruir(&l);
    mostrarLista(l);*/
	return 0;
}
/*
int main2(){
	TLista l;
	int ok;
	leePuntos(&l,"Puntos.bin");
	mostrarLista(l);
	eliminarPunto(&l,25,&ok);
	mostrarLista(l);
	destruir(&l);
	mostrarLista(l);
	return 0;
}*/
