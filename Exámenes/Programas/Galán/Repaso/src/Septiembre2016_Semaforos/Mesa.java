package Septiembre2016_Semaforos;

import java.util.concurrent.Semaphore;

public class Mesa {

	private int ganador, pendientes, N;
	private int ganCara, ganCruz;
	private int nCara = 0, nCruz = 0;

	private int nRonda = 1;

	private Semaphore MUTEX 		 = new Semaphore(1, true);
	private Semaphore jugadorEnEpera = new Semaphore(1, true);
	
	public Mesa(int N){

		ganador 	= N;
		pendientes 	= N;
		this.N	 	= N;
	}

	/**
	 * @param id del jugador que llama al metodo.
	 * @param cara: true si la moneda es cara, false en otro caso.
	 * @return un valor entre 0 y N. Si devuelve N es que ningun jugador ha ganado
	 * y hay que repetir la partida. Si  devuelve un numero menor que N, es el id del
	 * jugador ganador.
	 * 
	 * Un jugador llama al metodo nuevaJugada cuando quiere poner
	 * el resultado de su tirada (cara o cruz) sobre la mesa.
	 * 		El jugador deja su resultado y, si no es el ultimo, espera a que
	 * 		el resto de los jugadorEnEpera pongan sus jugadas sobre la mesa.
	 *
	 *		El ultimo jugador comprueba si hay o no un ganador, y despierta
	 * 		al resto de jugadorEnEpera.
	 */
	public int nuevaJugada(int id, boolean cara) throws InterruptedException{
		MUTEX.acquire();

		if(nRonda == 1 && pendientes == N){
			System.out.println("Empezamos.		[Ronda "+ nRonda +"]\n");
		}

		if(cara){
			System.out.println("Jugador	#" + id + "		( O ).");
			ganCara = id;
			nCara++;

		}else {
			System.out.println("Jugador	#" + id + "		( + ).");
			ganCruz = id;
			nCruz++;
		}

		pendientes--;

		if(pendientes > 0){
			MUTEX.release();
			jugadorEnEpera.acquire();
			MUTEX.acquire();


		}else if(pendientes == 0){
			nRonda++;

			if(nCara == 1){
				System.out.println("\nJugador	#"+ganCara+"		[Victoria]\n");
				ganador = ganCara;

			}else if(nCruz == 1){
				System.out.println("\nJugador	#"+ganCruz+"		[Victoria]\n");
				ganador = ganCruz;

			}else{
				System.out.println("\nSin ganador.	[Ronda "+ nRonda +"]\n");
				ganador = N;
			}

			nCara = 0; nCruz = 0;
			pendientes = N;
		}

		MUTEX.release();
		jugadorEnEpera.release();

		return ganador;
	}
}