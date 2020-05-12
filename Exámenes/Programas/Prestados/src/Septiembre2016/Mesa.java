package Septiembre2016;

import java.util.concurrent.*;

public class Mesa {
	/**
	 * N es el número de jugadores que intervienen
	 */
	private int numJugadores;
	private int ganador;
	private int jugadores;

	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore esperaTerminar=new Semaphore (0,true);
	private Semaphore turno=new Semaphore(1,true);

	private int car=0;	//numero de jugadores que le han salido cara
	private int cruz=0;	//numero de jugadores que le han salido cruz
	private int posGanadorCara;
	private int posGanadorCruz;

	private boolean asignado=false;//para que solo el ultimo jugador vea si hay o no ganador

	public Mesa(int N) {
		numJugadores=N;
		ganador=N;
		jugadores=N;
	}

	/**
	 *
	 * @param id
	 *            del jugador que llama al método
	 * @param cara
	 *            : true si la moneda es cara, false en otro caso
	 * @return un valor entre 0 y N. Si devuelve N es que ningún jugador ha
	 *         ganado y hay que repetir la partida. Si devuelve un número menor
	 *         que N, es el id del jugador ganador.
	 *
	 *         Un jugador llama al método nuevaJugada cuando quiere poner el
	 *         resultado de su tirada (cara o cruz) sobre la mesa. El jugador
	 *         deja su resultado y, si no es el último, espera a que el resto de
	 *         los jugadores pongan sus jugadas sobre la mesa. El último jugador
	 *         comprueba si hay o no un ganador, y despierta al resto de
	 *         jugadores
	 * @throws InterruptedException
	 *
	 */
	public int nuevaJugada(int id, boolean cara) throws InterruptedException {
		turno.acquire();
		mutex.acquire();
		numJugadores--;
		if(numJugadores>0){
			turno.release();
		}
		if(cara){
			System.out.println("[Jugador "+id+"] Ha sacado CARA.");
			car++;
			posGanadorCara=id;
		}else{
			System.out.println("[Jugador "+id+"] Ha sacado CRUZ.");
			cruz++;
			posGanadorCruz=id;
		}

		if(numJugadores>0){
			mutex.release();
			esperaTerminar.acquire();
			mutex.acquire();

		}else if(car==1 && !asignado){
			ganador=posGanadorCara;
			asignado=true;

		}else if(cruz==1 && !asignado){
			ganador=posGanadorCruz;
			asignado=true;
		}

		if(numJugadores==0){
			if(car==1){
				System.out.println("Gana el jugador "+posGanadorCara);

			}else if(cruz==1){
				System.out.println("Gana el Jugador "+posGanadorCruz);

			}else{
				System.out.println("No hay ganador.");
			}

			turno.release();

			numJugadores=jugadores;

			//hacemos un reset de las variables si no hay ganador
			if(ganador==jugadores){
			car=0;
			cruz=0;
			ganador=jugadores;
			}
		}
		esperaTerminar.release();
		mutex.release();

		return ganador;
	}
}