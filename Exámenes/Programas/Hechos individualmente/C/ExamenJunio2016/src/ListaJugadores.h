#ifndef LISTAJUGADORES_H_
#define LISTAJUGADORES_H_

typedef struct TNodo *TListaJugadores;

struct TNodo{
    unsigned int jugador;
    unsigned int goles;
    TListaJugadores sig;
};

//crea una lista vacía (sin ningún nodo)
void crear(TListaJugadores *lc);

//inserta un nuevo jugador en la lista de jugadores, poniendo 1 en el número de goles marcados.
//Si ya existe añade 1 al número de goles marcados.
void insertar(TListaJugadores *lj,unsigned int id);

//recorre la lista circular escribiendo los identificadores y los goles marcados
void recorrer(TListaJugadores lj);

//devuelve el número de nodos de la lista
int longitud(TListaJugadores lj);

//Eliminar. Toma un número de goles como parámetro y
//elimina todos los jugadores que hayan marcado menos que ese número de goles
void eliminar(TListaJugadores *lj,unsigned int n);

// Devuelve el ID del máximo jugador. Si la lista está vacía devuelve 0. Si hay más de un jugador con el mismo número de goles que el máximo devuelve el de mayor ID
// Hay que devolver el identificador, no el número de goles que ha marcado
unsigned int maximo(TListaJugadores lj);

//Destruye la lista y libera la memoria)
void destruir(TListaJugadores *lj);

#endif /* LISTAJUGADORES_H_ */
