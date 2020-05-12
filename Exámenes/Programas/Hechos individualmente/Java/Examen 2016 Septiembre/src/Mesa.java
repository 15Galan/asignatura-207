import java.util.concurrent.*;

public class Mesa {
	private int ganadorID;			// Jugador ganador (id)
	private int jugadores;			// Número de jugadores totales (variable)
	private static int JUGADORES;	// Número de jugadores totales (constante = N)

	private int caras  = 0;		// Cantidad de jugadores con cara
	private int cruces = 0;		// Cantidad de jugadores con cruz
	private int caraID;			// ID del jugador ganador sacando cara
	private int cruzID;			// ID del jugador ganador sacando cruz

	private Semaphore jugando	= new Semaphore(1, true);	// Una jugando tiene N turnos
	private Semaphore esperando = new Semaphore(0, true);	// Un jugador esperando al final de la jugando
	private Semaphore mutex		= new Semaphore(1, true);

	public Mesa(int N){
		JUGADORES = N;
		jugadores = N;
		ganadorID = N;
	}

	/**
	 *
	 * @param id del jugador que llama al m�todo
	 * @param cara : true si la moneda es cara, false en otro caso
	 * @return un valor entre 0 y N. Si devuelve N es que ning�n jugador 
	 * ha ganado y hay que repetir la partida. Si  devuelve un n�mero menor que N, es el id del
	 * jugador ganadorID.
	 *
	 * Un jugador llama al m�todo nuevaJugada cuando quiere poner
	 * el resultado de su tirada (cara o cruz) sobre la mesa.
	 * El jugador deja su resultado y, si no es el �ltimo, esperando a que el resto de
	 * los jugadores pongan sus jugadas sobre la mesa.
	 * El �ltimo jugador comprueba si hay o no un ganadorID, y despierta
	 * al resto de jugadores
	 *
	 */
	public int nuevaJugada(int id, boolean cara) throws InterruptedException{
		jugando.acquire();		// Empezar una nueva jugando de jugadas
		mutex.acquire();
			jugadores--;		// Reducir el número de jugadores que deben hacer su jugada

			if(jugadores > 0){	// Si quedan jugadores sin hacer su jugada, pasa el jugando
				jugando.release();
			}

			if(cara){				// Sale cara
				System.out.println("[Jugador]["+id+"]	CARA.");
				caras++;

				caraID = id;
			}else{					// Sale cruz
				System.out.println("[Jugador]["+id+"]	CRUZ.");
				cruces++;

				cruzID = id;
			}

			if(jugadores > 0){		// Si la jugando aun no ha acabado
				mutex.release();
				esperando.acquire();
				esperando.release();

			}else{

				if(caras == 1){	// Si la jugando ha acabado, comprobamos si gana un jugador con cara
					System.out.println("El ganador es el Jugador "+caraID+".");
					ganadorID = caraID;

				}else if(cruces == 1){	// Si la jugando ha acabado, comprobamos si gana un jugador con cara
					System.out.println("El ganador es el Jugador "+cruzID+".");
					ganadorID = cruzID;

				}else{					// No ha habido ganador, así que se preparan las variables para una nueva jugando
					System.out.println("Ronda sin ganador.");

					jugadores = JUGADORES;
					ganadorID = JUGADORES;

					caras  = 0;
					cruces = 0;
				}

				mutex.release();
				jugando.release();
				esperando.release();
				esperando.acquire();
			}

		return ganadorID;
	}
}
