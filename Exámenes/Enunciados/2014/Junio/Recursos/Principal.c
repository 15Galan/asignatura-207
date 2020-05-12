#include <stdio.h>
#include <stdlib.h>
#include "colaprio.h"

#define FICHERO "cola.bin"

int main() {
  TColaPrio cola;
  
  Crear_Cola(FICHERO, &cola);
  Mostrar(cola);
  printf("\n");
  Ejecutar(&cola, 2);
  Mostrar(cola);
  printf("\n");
  Ejecutar_Max_Prio(&cola);
  Mostrar(cola);
  printf("\n");
  Destruir(&cola);
  Mostrar(cola);
  printf("\n");
  return 0;
}