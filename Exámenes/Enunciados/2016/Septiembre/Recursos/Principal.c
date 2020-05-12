/*
 * Principal.c
 *
 *  Created on: 18/06/2012
 *      Author: PSCC
 */

#include <stdio.h>
#include "MProcesos.h"


int main () {

	LProc plan;


	printf("Creamos una lista vacia\n");
	Crear(&plan);
	EjecutarProcesos(plan);

	printf("Anadir proceso  1 \n");
	AnadirProceso(plan,1);
	printf("Anadir proceso 8\n");
	AnadirProceso(plan,8);
	printf("Anadir proceso 3 \n");
	AnadirProceso(plan,3);
	printf("Anadir proceso  4 \n");
	AnadirProceso(plan,4);
	printf("Anadir proceso 6 \n");
	AnadirProceso(plan,6);

	EjecutarProcesos(plan);

	printf("Eliminamos proceso 1\n");
	EliminarProceso(1,&plan);
	EjecutarProcesos(plan);

	printf("Eliminamos proceso 6\n");
	EliminarProceso(6,&plan);
	EjecutarProcesos(plan);

	EscribirFichero("Salida.bin",&plan);
	EjecutarProcesos(plan);

	return 0;

}
