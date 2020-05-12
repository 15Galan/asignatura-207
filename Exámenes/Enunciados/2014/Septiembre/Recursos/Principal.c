#include <stdio.h>
#include <stdlib.h>
#include "Sistema.h"
#include <string.h>


int main() {
  LSistema l;
  char idh[3];
  
  
  Crear(&l);
  Mostrar(l);
  printf("\n");
  InsertarProceso ( &l, 4);
  Mostrar(l);
  printf("\n");
 InsertarProceso ( &l, 6);
  Mostrar(l);
  printf("\n");

  strcpy(idh,"h1");
  InsertarHebra (&l, 6, idh, 7);
  Mostrar(l);
  printf("\n");


   strcpy(idh,"h3");
  InsertarHebra (&l, 6, idh, 1);
  Mostrar(l);
  printf("\n");

  InsertarProceso ( &l, 1);
  Mostrar(l);
  printf("\n");

  InsertarProceso ( &l, 2);
  Mostrar(l);
  printf("\n");

   strcpy(idh,"h2");
  InsertarHebra (&l, 6, idh, 4);
  Mostrar(l);
  printf("\n");

      strcpy(idh,"h8");
  InsertarHebra (&l, 2, idh, 3);
  Mostrar(l);
  printf("\n");
    strcpy(idh,"h5");
  InsertarHebra (&l, 2, idh, 2);
  Mostrar(l);
  printf("\n");


      strcpy(idh,"h7");
  InsertarHebra (&l, 2, idh, 10);
  Mostrar(l);
  printf("\n");

 InsertarProceso ( &l, 5);
  Mostrar(l);
  printf("\n");



 
  Destruir(&l);
  Mostrar(l);
  printf("\n");
  return 0;
}