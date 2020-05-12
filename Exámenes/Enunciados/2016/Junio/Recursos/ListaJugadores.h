
// Definir aquí
// TListaJugadores;


//crea una lista vacía (sin ningún nodo)
void crear(TListaJugadores *lc);

//inserta un nuevo jugador en la lista de jugadores, poniendo 1 en el numero de goles marcados.
//Si ya existe añade 1 al numero de goles marcados.
void insertar(TListaJugadores *lj,unsigned int id);

//recorre la lista circular escribiendo los identificadores y los goles marcados
void recorrer(TListaJugadores lj);

//devuelve el numero de nodos de la lista
int longitud(TListaJugadores lj);

//Eliminar. Toma un numero de goles como parametro y
//elimina todos los jugadores que hayan marcado menos que ese numero de goles
void eliminar(TListaJugadores *lj,unsigned int n);


// Devuelve el ID del maximo jugador. Si la lista esta vacia devuelve 0. Si hay mas de un jugador con el mismo numero de goles que el maximo devuelve el de mayor ID
// Hay que devolver el identificador, no el numero de goles que ha marcado
unsigned int maximo(TListaJugadores lj);

//Destruye la lista y libera la memoria)
void destruir(TListaJugadores *lj);
