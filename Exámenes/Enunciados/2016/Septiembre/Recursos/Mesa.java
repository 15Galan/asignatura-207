package semaforos;

import java.util.concurrent.Semaphore;
public class Mesa {


	private Semaphore mutex = new Semaphore(1);
	private Semaphore nueva = new Semaphore(1); // separa cada jugada de los N procesos
											    // con la siguiente, si no ha habido ganador
	private Semaphore espera = new Semaphore(0); // bloquea a los procesos
												// hasta que todos han puesto sus monedas

	private int N; // numero de jugadores
	private boolean[] moneda; //arrays de booleanos con los resultados
							// de cada tirada por jugador: true = cara, false = cruz
	private int num = 0; //numero de jugadores que han dejado su moneda en
						 //cada tirada
	private int ganador = N; // si no hay ganador vale N
	private int numCaras=0, numCruces=0; //numero de caras y cruces en cada
										 //tirada

	public Mesa(int N){
		this.N = N;
		this.moneda = new boolean[N];
	}

	/**
	 * metodo privado que asigna a la variable ganador el
	 * indice del ganador, si existe, o -1, si no ha habido ganador
	 */
	private void ganador(){
		int gana = -1;
		if (numCaras==1) gana = 1;
		if (numCruces==1) gana = 0;
		boolean hayGanador = gana != -1; // hay un ganador si alguien tiene una
		// moneda distinta a todos los demas
		if (hayGanador){ // si hay ganador lo busco
			boolean enc = false;
			int i = 0;
			while (!enc){
				if ( moneda[i] == (gana == 1)) {
					ganador = i;
					enc = true;
				} else {
					i++;
				}
			}
		} else ganador = N;


	}
	/**
	 *
	 * @param id del jugador que llama al metodo
	 * @param cara : true si la moneda es cara, false en otro caso
	 * @return un valor entre 0 y N. Si devuelve N es que ningun jugador
	 * ha ganado y hay que repetir la partida. Si  devuelve un numero menor que N, es el id del
	 * jugador ganador.
	 *
	 * Un jugador llama al metodo nuevaJugada cuando quiere poner
	 * el resultado de su tirada (cara o cruz) sobre la mesa.
	 * El jugador deja su resultado y, si no es el ultimo, espera a que el resto de
	 * los jugadores pongan sus jugadas sobre la mesa.
	 * El ultimo jugador comprueba si hay o no un ganador, y despierta
	 * al resto de jugadores
	 *
	 */
	public int nuevaJugada(int id,boolean cara) throws InterruptedException{
		int miGanador;
		nueva.acquire();
		mutex.acquire();
		moneda[id] = cara;
		num++;
		if (cara) numCaras++;
		else numCruces++;
		System.out.println("Al jugador "+id+" le ha salido "+cara);
		if (num<N){
			mutex.release();
			nueva.release();
			espera.acquire(); // espera a que estan todos
			mutex.acquire();
		} else {
			ganador(); // es el ultimo, mira a ver si hay ganador
		}
		miGanador = ganador;
		num--;
		if (cara) numCaras--;
		else numCruces--;
		if (num > 0)
			espera.release(); // si quedan mas esperando los despierto
		else {
			nueva.release(); // si soy el ultimo, abro el semaforo para
							// la siguiente jugada
			System.out.println("El ganador ha sido "+ganador);
		}
		mutex.release();
		return miGanador;
	}
}
