typedef struct Nodo *TLista;

struct Punto{
	float x;
	float y;
};

struct Nodo{
	struct Punto punto;
	TLista sig;
};
/*
 * Inicializa la lista de puntos creando una lista vacia
 */
void crearLista(TLista *lista);

/**
 * Inserta el punto de forma ordenada (por el valor de la abscisa x)
 * en la lista siempre que no este repetida la abscisa.
 * En ok, se devolvera un 1 si se ha podido insertar, y 0 en caso contrario.
 * Nota: utiliza una funcion auxiliar para saber
 * si ya hay un punto en la lista con la misma abscisa punto.x
 */
void insertarPunto(TLista *lista, struct Punto punto, int * ok);

/*
 * Elimina de la lista el punto con abscisa x de la lista.
 * En ok devolverá un 1 si se ha podido eliminar,
 * y un 0 si no hay ningún punto en la lista con abscisa x
 */
void eliminarPunto(TLista *lista,float x,int* ok);

 /**
 * Muestra en pantalla el listado de puntos
 */
void mostrarLista(TLista lista);

/**
 * Destruye la lista de puntos, liberando todos los nodos de la memoria.
 */
void destruir(TLista *lista);

/*
 * Lee el contenido del archivo binario de nombre nFichero,
 * que contiene una secuencia de puntos de una función polinómica,
 * y lo inserta en la lista.
 */
void leePuntos(TLista *lista,char * nFichero);
