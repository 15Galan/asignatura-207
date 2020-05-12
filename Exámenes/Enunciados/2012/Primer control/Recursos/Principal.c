============================================================================
 Name        : control1_1213.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description :
 ============================================================================
 */


#include <stdio.h> 
#include <stdlib.h> 
#include "planificador.h"



int main () {

	T_Planificador planif;
	unsigned ok;

	crear(&planif);
	mostrar(planif);

	insertar_tarea(&planif,4,"t8");
	mostrar(planif);

	insertar_tarea(&planif,8,"t1");
	mostrar(planif);

	insertar_tarea(&planif,1,"t3");
	mostrar(planif);

	insertar_tarea(&planif,8,"t2");
	mostrar(planif);

	insertar_tarea(&planif,3,"t7");
	mostrar(planif);

	eliminar_tarea(&planif,"t7",&ok);
	if (ok) {
		printf("Borrado realizado correctamente\n");
	}
	else {
		printf("Borrado no se ha podido realizar\n");

	}
	mostrar(planif);

	eliminar_tarea(&planif,"t9",&ok);
	if (ok) {
		printf("Borrado realizado correctamente\n");
	}
	else {
		printf("Borrado no se ha podido realizar\n");

	}
	mostrar(planif);

	eliminar_tarea(&planif,"t3",&ok);
	if (ok) {
		printf("Borrado realizado correctamente\n");
	}
	else {
		printf("Borrado no se ha podido realizar\n");

	}
	mostrar(planif);

	insertar_tarea(&planif,9,"t10");
	mostrar(planif);

	planificar(&planif);
	mostrar(planif);

	planificar(&planif);
	planificar(&planif);
	planificar(&planif);
	mostrar(planif);

	destruir(&planif);

	printf("Fin Programa\n");

	system("PAUSE");

	return 0;
}
