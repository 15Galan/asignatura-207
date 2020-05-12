 /*
 * planificador.h
 *
 *  Created on: 15/04/2013
 *      Author: dgarrido
 */

#ifndef _PLANIFICADOR_H_
#define _PLANIFICAODR_H_

#define MAX_ID 80

typedef struct T_Nodo* T_Planificador;

struct T_Nodo {
			int pri;
			char id[MAX_ID+1];
			T_Planificador sig;
		};

/* Crea la estructura para gestionar el planificador */
void crear(T_Planificador *planif);

/* Inserta una nueva tarea en el planificador */
void insertar_tarea(T_Planificador *planif,int pri,char *id);

/* Elimina la tarea indicada del planificador */
/* ok==1 indica borrado realizado, 0 en otro caso */
void eliminar_tarea(T_Planificador *planif,char *id,unsigned *ok);

/* Planifica la primera tarea de la lista */
void planificar(T_Planificador *planif);

/* Destruye la estructura utilizada. */
void destruir(T_Planificador *manejador);


/* Muestra el estado actual del planificador */
void mostrar (T_Planificador planificador);


#endif /* _PLANIFICADOR_H_ */
