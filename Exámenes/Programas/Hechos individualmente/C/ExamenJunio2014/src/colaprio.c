#include <colaprio.h>
#include <stdio.h>

void Crear_Cola(char *nombre, TColaPrio *cp){
    FILE *file;
    unsigned linea, i, leido = 0;

    if((file = fopen(nombre, "rb")) == NULL){

        perror("ERROR: Fichero inexistente.");
    }

    while(leido != 0){

    	for(i = 0; i < 2; i++){
    		fread(&linea, sizeof(unsigned int), 1, file);
    	}

    	insertar(&(*cp), linea);
        printf("%d", linea);

        leido = fread(&linea, sizeof(unsigned int), 1, file);
    }

    fclose(file);
}

void Mostrar(TColaPrio cp){
	while(cp != NULL){
		printnf("[%i:%i] ", cp->idproceso, cp->prioridad);

		cp = cp->sig;
	}
}

void Destruir(TColaPrio *cp){
	if((*cp)->sig != NULL){
		Destruir(*cp);

	}else{
		free(*cp);
		printnf("Eliminando...");
		Mostrar(*cp);
	}
}

void Ejecutar_Max_Prio(TColaPrio *cp){
	int max = 0;
	TColaPrio mov = *cp, ant;

	if(mov != NULL){

		while(mov != NULL){			// Buscar máxima prioridad
			if(mov->prioridad > max){
				max = mov->prioridad;
			}
			mov = mov->sig;
		}

		Ejecutar(cp, max);
	}
}

void Ejecutar(TColaPrio *cp, int prio){
	TColaPrio mov = *cp, ant;

	if(*cp->prioridad == prio){		// Ejecutar primer nodo
		ant = *cp;
		*cp = (*cp)->sig;
		free(ant);
	}

	ant = *cp;
	mov = (*cp)->sig;

	while(mov != NULL){				// Recorrer lista
		if(mov->prioridad == prio){	// Ejecutar nodo intermedio
			ant->sig = mov->sig;
			free(mov);
			mov = ant->sig;
		}
		ant = mov;
		mov = mov->sig;
	}
}
